package com.app.android.popularmovie.models.network;

import com.app.android.popularmovie.models.MovieFlavor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/6/17.
 */
public class JsonWork {

    public static ArrayList<MovieFlavor> getMovieDataFromJson(String movies ,int num  ) throws JSONException {

        ArrayList<MovieFlavor> movieFlavors = new ArrayList<MovieFlavor>();
        String[] result = new String[num];
        JSONObject movieJson = new JSONObject(movies);
        JSONArray idList = movieJson.getJSONArray("results");

        for(int i = 0; i< num; i++ )
        {
            JSONObject movieObject = idList.getJSONObject(i);
            String  idPath= movieObject.getString("poster_path");
            String  movieName = movieObject.getString("original_title");
            String movieDate = movieObject.getString("release_date");
            String movieOverview = movieObject.getString("overview");
            String url = NetBaseInfo.baseUrl+""+NetBaseInfo.imageSize+"/"+idPath;
            String movieScore = movieObject.getString("vote_average");
            movieFlavors.add(new MovieFlavor(movieName,movieDate,movieOverview,url,movieScore));

            result[i] = idPath;

        }


        return movieFlavors;
    }
}
