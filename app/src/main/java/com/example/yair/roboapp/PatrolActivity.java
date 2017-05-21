package com.example.yair.roboapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class PatrolActivity extends AppCompatActivity {
    public DrawView drawView;
    public LinearLayout myBox;
    public String strMap;
    public String serverIp;
    public int[][] matrixMap;

    public int pointsFalg=0;
    public clickPoint[] clickpoints;
    public int rows;
    public int cols;

    public int Xratio;
    public int Yratio;

    public static ViewGroup myLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patrol);
        serverIp = LoginActivity.ip;

        String url = "http://" + serverIp + ":8080/Map";
        getMap(url);

        findViewById(R.id.mapbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://" + serverIp + ":8080/Map";
                drawMap();
            }
        });

        findViewById(R.id.clearbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<pointsFalg;i++)
                {
                    int x= clickpoints[i].x;
                    int y= clickpoints[i].y;
                    matrixMap[x][y]=2;
                }

                pointsFalg=0;
                drawMap();
            }
        });

        findViewById(R.id.patrolBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://" + serverIp + ":8080/startPatrol";
                RequestQueue queue=Volley.newRequestQueue(PatrolActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(PatrolActivity.this, "Patrol Running!", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PatrolActivity.this, "Error!" + error, Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(stringRequest);
            }
        });

        findViewById(R.id.patrolBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://" + serverIp + ":8080/startDemo";
                RequestQueue queue=Volley.newRequestQueue(PatrolActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(PatrolActivity.this, "Patrol Running!", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PatrolActivity.this, "Error!" + error, Toast.LENGTH_LONG).show();
                    }
                });
                queue.add(stringRequest);
            }
        });

        findViewById(R.id.savebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPoints=LoginActivity.ip+":8080/savpoints?";
                for (int i=0;i<pointsFalg;i++)
                {
                    int x= clickpoints[i].x;
                    int y= clickpoints[i].y;
                    newPoints+=":"+Integer.toString(x)+','+Integer.toString(y)+'~';
                }

                get("http://"+newPoints);
            }
        });

        findViewById(R.id.my).setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int clickX=(int)(event.getX());
                int clickY=(int)(event.getY());
                myBox = (LinearLayout) findViewById(R.id.my);
                int width = myBox.getWidth();
                int height = myBox.getHeight();

                int xCell=(int)(((PatrolActivity.this.rows*clickX)/width)+0.5);
                int yCell=(int)(((PatrolActivity.this.cols*clickY)/height)+0.5);

                xCell=xCell%rows;
                yCell=yCell%cols;
                clickpoints[pointsFalg]=new clickPoint(xCell,yCell);
                pointsFalg++;

                matrixMap[xCell][yCell]=0;
                drawMap();

                return true;
            }
        });

    }

    public void parseMap() {

        int rows = Integer.parseInt(strMap.substring(0, 2));
        int cols = Integer.parseInt(strMap.substring(3, 5));
        this.rows=rows;
        this.cols=cols;
        int value;
        matrixMap = new int[rows][cols];
        clickpoints=new clickPoint[rows*cols];
        String strMatrix;

        strMatrix = strMap.substring(strMap.indexOf("?") + 1, strMap.length() - 1)+":";
        String rowStr;

        for (int i = 0; i < rows; i++) {
            rowStr=strMatrix.substring(0,strMatrix.indexOf(":"));
            strMatrix=strMatrix.substring(strMatrix.indexOf(":")+1,strMatrix.length());
            for (int j = 0; j < cols; j++) {
                value = Integer.parseInt(rowStr.substring(j,j+1));
                matrixMap[i][j] = value;
            }
        }


        String url = "http://" + serverIp + ":8080/getpoints";

        RequestQueue queue=Volley.newRequestQueue(PatrolActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String points = response.toString();
                        while (points.contains(":"))
                        {
                            points=points.substring(points.indexOf(":")+1,points.length());
                            int x=Integer.parseInt(points.substring(0,points.indexOf(",")));
                            int y=Integer.parseInt(points.substring(points.indexOf(",")+1,points.indexOf("~")));
                            matrixMap[x][y]=0;
                        }
                        drawMap();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PatrolActivity.this, "Error!" + error, Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);



    }

    public void getMap(String url) {
        RequestQueue queue=Volley.newRequestQueue(PatrolActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("False")){
                            Toast.makeText(PatrolActivity.this, "No map to display!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            strMap = response.toString();
                            parseMap(); //also draws map after parse
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PatrolActivity.this, "Error!" + error, Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);

    }

    public void get(String url) {
        RequestQueue queue=Volley.newRequestQueue(PatrolActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(PatrolActivity.this, "New Patrol Successfully Saved." , Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PatrolActivity.this, "Error!", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);

    }

    public void drawMap(){
        myBox = (LinearLayout) findViewById(R.id.my);
        drawView = new DrawView(PatrolActivity.this, myBox,matrixMap,rows,cols);
        drawView.setBackgroundColor(Color.WHITE);
        myLayout = (ViewGroup) findViewById(R.id.my);

        myLayout.removeAllViews();
        myLayout.refreshDrawableState();

        myLayout.addView(drawView);



    }
}


class DrawView extends View {
    Paint paint = new Paint();
    public LinearLayout myBox;
    public int x;
    public int y;
    public int width;
    public int height;

    public int Xratio;
    public int Yratio;

    public int[][]map;
    public int rows;
    public int cols;

    public DrawView(Context context, LinearLayout box,int[][] matrix,int rowss,int colss) {
        super(context);
        paint.setColor(Color.BLACK);
        myBox = box;
        x = (int) (myBox.getX()/2);
        y = (int) (myBox.getY()/2);
        width = myBox.getWidth();
        height = myBox.getHeight();
        map=matrix;
        rows=rowss;
        cols=colss;

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int value;

        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++)
            {

                value=map[i][j];

                if (value==0){
                    paint.setColor(Color.RED);
                }
                if(value==1)
                    paint.setColor(Color.BLACK);
                if(value==2)
                    paint.setColor(Color.WHITE);


                float Xratio=width/rows;
                float Yratio=height/cols;

                float Left=(float)(x+i*Xratio);
                float Right=(float)(10+x+i*Xratio);

                float Top=(float)(y+j*Yratio);
                float Buttom=(float)(10+y+j*Yratio);

                canvas.drawOval((float)(x+i*Xratio),(float)(y+j*Yratio),(float)(10+x+i*Xratio),(float)(10+y+j*Yratio),paint);

                int rightNeighbor=j+1;
                int buttomNeighbor=i+1;

                if(value==1){
                    if(rightNeighbor<cols)
                    {
                        if (map[i][rightNeighbor]==1) {
                            paint.setColor(Color.BLACK);
                            for(int k=0;k<11;k++)
                            {
                                canvas.drawOval((float)(x+i*Xratio),(float)(y+j*Yratio),(float)(10+x+i*Xratio),(float)(k*8+y+j*Yratio),paint);
                            }

                        }
                    }
                    if(buttomNeighbor<rows)
                    {
                        if(map[buttomNeighbor][j]==1)
                        {

                            paint.setColor(Color.BLACK);
                            for(int k=0;k<11;k++)
                            {
                                canvas.drawOval((float)(x+i*Xratio),(float)(y+j*Yratio),(float)(k*15+x+i*Xratio),(float)(10+y+j*Yratio),paint);
                            }
                        }
                    }

                }

            }}

    }
}


class clickPoint{

    public int x;
    public int y;

    public clickPoint(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

}



