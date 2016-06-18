package com.app.android.popularmovie.models.network;

import android.util.Log;

import com.app.android.popularmovie.models.MovieFlavor;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/6/17.
 */
public class GetDataFromNet {

    public static  ArrayList<MovieFlavor> resultStr;
    public static HttpURLConnection urlConnection = null;
    public static BufferedReader reader = null;

    public static ArrayList<MovieFlavor> GetDataByUrl(String currenturl)
    {

        String moviesJsonStr = null;
        try {

            URL url = new URL(currenturl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            //网络请求不能再主线程
            urlConnection.connect();

            InputStream inputstream = urlConnection.getInputStream();
            StringBuffer sb = new StringBuffer();

            if (inputstream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputstream));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            if (sb.length() == 0) return null;
            moviesJsonStr = sb.toString();
            Log.d("", "Movies id ------------" + moviesJsonStr);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        try {
            resultStr = JsonWork. getMovieDataFromJson(moviesJsonStr, 9);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultStr;
    }
}
