package com.app.android.popularmovie.controller.Activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.android.popularmovie.models.MovieFlavor;
import com.app.android.popularmovie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by SYSTEM on 2016/6/8.
 */
public class ItemAdapter extends ArrayAdapter<MovieFlavor> {
    public ItemAdapter(Context context, List<MovieFlavor> movieFlavors) {
        super(context,0, movieFlavors);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        MovieFlavor movieFlavor = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item, parent, false);
        }
        ImageView posterView = (ImageView) convertView.findViewById(R.id.posterImage);

        Picasso.with(getContext()).load(movieFlavor.getMovie_Poster())
                .resize(400,550)
                .placeholder(R.drawable.image_palceholer).error(R.mipmap.ic_launcher).into(posterView);

        TextView txt_Name = (TextView) convertView.findViewById(R.id.txt_Name);
        txt_Name.setText(movieFlavor.getMovie_Name());

        return convertView;
    }
    }

