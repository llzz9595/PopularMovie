package com.app.android.popularmovie.Adpater;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.app.android.popularmovie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/6/3.
 */
public class ImageAdapter extends BaseAdapter {

    private Context context;
    //图片地址 获取多张图片
    private ArrayList<String> urls;

    public ImageAdapter(Context context, ArrayList<String> urls)
    {
        this.context = context;
        this.urls  = urls;
    }
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);

//        设置placeholder和 error image
        Picasso.with(context).load(urls.get(position)).placeholder(R.drawable.image_palceholer).error(R.mipmap.ic_launcher).into(imageView);

        //设置网格中显示imageView的属性
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(280, 280));

        return imageView;

    }
}