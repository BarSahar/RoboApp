package com.example.yair.roboapp.classes;

import android.app.Activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.graphics.Bitmap;

import com.example.yair.roboapp.R;

import java.util.List;

/**
 * Created by Bar on 22-Apr-17.
 */

public class ImageList extends ArrayAdapter<Bitmap> {

    private static class ViewHolder{
        ImageView imageView;
    }

    //private final Activity context;
    private final Context context;
    //private final Bitmap[] images;
    //private final List<Bitmap> images;

    public ImageList(Context context, List<Bitmap> images) {
        //super(context, 0,R.layout.list_single);
        super(context.getApplicationContext(), 0,images);
        this.context = context;
        //this.images = images;
    }

    @Override
    public View getView(int position , View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.list_single,parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageListItem);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder)convertView.getTag();

        Bitmap imgItem = getItem(position);
        if(imgItem!=null){
            holder.imageView.setImageBitmap(imgItem);
        }
        return convertView;
    }

    /*public View getView(int position , View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        imageView.setImageBitmap(images.get(position));
        return rowView;
    }*/
}
