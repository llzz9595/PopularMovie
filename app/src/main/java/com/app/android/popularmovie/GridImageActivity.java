package com.app.android.popularmovie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.app.android.popularmovie.controller.Activity.adapter.ImageAdapter;
import com.app.android.popularmovie.controller.Activity.adapter.ItemAdapter;
import com.app.android.popularmovie.models.MovieFlavor;
import com.app.android.popularmovie.models.network.GetDataFromNet;
import com.app.android.popularmovie.models.network.NetBaseInfo;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/6/3.
 */
public class GridImageActivity extends AppCompatActivity {

    private ImageAdapter imageAdapter;
    private GridView gridView;

//    private final String baseUrl = "http://image.tmdb.org/t/p/";
//    private final String imageSize = "w185";
//    private final String ApplyUrl_0 = " http://api.themoviedb.org/3/movie/popular?api_key=d18b788342cd2d9811550d085b4ad15b";
//    private final String ApplyUrl_1 = "http://api.themoviedb.org/3/discover/movie?certification_country=US&certification=R&sort_by=vote_average&api_key=d18b788342cd2d9811550d085b4ad15b";
    private ItemAdapter movieAdapter;
    private String currenturl = " http://api.themoviedb.org/3/movie/popular?api_key=d18b788342cd2d9811550d085b4ad15b";
    private ArrayList<MovieFlavor> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movies = new ArrayList<MovieFlavor>();

        setContentView(R.layout.activity_gridview);

        gridView = (GridView) findViewById(R.id.grid_view);

        movieAdapter = new ItemAdapter(this, movies);
        gridView.setAdapter(movieAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //     Toast.makeText(GridImageActivity.this,"kkkkkk",Toast.LENGTH_SHORT);
                Intent intent = new Intent(GridImageActivity.this, MainActivity.class);
                Log.d(" ", "---------" + movies.get(position).getMovie_Date());
                intent.putExtra("movieItem", movies.get(position));
                startActivity(intent);
            }
        });
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("flavors", movies);
        super.onSaveInstanceState(outState);
    }

    //菜单选择事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView textView = (TextView) findViewById(R.id.title);
        switch (item.getItemId()) {
            case R.id.action_pop:
                currenturl = NetBaseInfo.ApplyUrl_0;
                GetMovieId g1 = new GetMovieId();
                g1.execute(currenturl);
                textView.setText("The POP MOVIES");
                return true;
            case R.id.action_per:
                currenturl = NetBaseInfo.ApplyUrl_1;
                GetMovieId g = new GetMovieId();
                g.execute(currenturl);
                textView.setText("The TOP MOVIES");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onStart() {
        super.onStart();
        GetMovieId g1 = new GetMovieId();
        g1.execute(currenturl);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class GetMovieId extends AsyncTask<String, String[], ArrayList<MovieFlavor>> {
//        ArrayList<MovieFlavor> resultStr;
//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;

        protected void onPostExecute(ArrayList<MovieFlavor> result) {
            //更新适配器
            if (result != null) {
                movies.clear();
                for (MovieFlavor id : result) {
                    movies.add(id);
                }

                movieAdapter = new ItemAdapter(GridImageActivity.this, movies);
//               imageAdapter = new ImageAdapter(GridImageActivity.this ,urls);
                gridView.invalidateViews();
            }


        }




        @Override
        protected ArrayList<MovieFlavor> doInBackground(String... params) {

          return   GetDataFromNet.GetDataByUrl(currenturl);

        }
    }

}
