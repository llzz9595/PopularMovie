package com.app.android.popularmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.app.android.popularmovie.Adpater.ImageAdapter;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/6/3.
 */
public class GridImageActivity extends AppCompatActivity {

    private ImageAdapter imageAdapter;
    private GridView gridView;
    private ArrayList<String>  urls;
    private final String baseUrl=": http://image.tmdb.org/t/p/";
    private final String imageSize = "w185";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        gridView = (GridView) findViewById(R.id.grid_view);

        //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
//        urls.add("http://i.imgur.com/DvpvklR.png");

        //通过tmd api 读取电影图
        urls.add("");
        imageAdapter = new ImageAdapter(this,urls);
        gridView.setAdapter(imageAdapter);

       /* gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(GridImageActivity.this, MainActivity.class);

                intent.putExtra("imageUrl", "http://i.imgur.com/DvpvklR.png");
                startActivity(intent);
            }
        });*/
    }


}
