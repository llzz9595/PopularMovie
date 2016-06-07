package com.app.android.popularmovie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.app.android.popularmovie.Adpater.ImageAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by SYSTEM on 2016/6/3.
 */
public class GridImageActivity extends AppCompatActivity {

    private ImageAdapter imageAdapter;
    private GridView gridView;
    private ArrayList<String>  urls;
    private final String baseUrl="http://image.tmdb.org/t/p/";
    private final String imageSize = "w185";
    private final String ApplyUrl =" http://api.themoviedb.org/3/movie/popular?api_key=d18b788342cd2d9811550d085b4ad15b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);

        gridView = (GridView) findViewById(R.id.grid_view);

        urls = new ArrayList<String>();
       urls.add(baseUrl+""+imageSize+"_/s7OVVDszWUw79clca0durAIa6mw.jpg");

//        GetMovieId g1 = new GetMovieId();
//        g1.execute();
         imageAdapter = new ImageAdapter(this,urls);
         gridView.setAdapter(imageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           //     Toast.makeText(GridImageActivity.this,"kkkkkk",Toast.LENGTH_SHORT);
                Intent intent = new Intent(GridImageActivity.this, MainActivity.class);

                intent.putExtra("imageUrl", urls.get(position));
                startActivity(intent);
            }
        });
    }

    public void onStart()
    {
        super.onStart();
        GetMovieId g1 = new GetMovieId();
        g1.execute();

    }

       private class GetMovieId extends AsyncTask<String ,String[], String[]>
       {
           String[] resultStr;
           HttpURLConnection urlConnection = null;
           BufferedReader reader = null;
           protected void onPostExecute(String[] result) {
               //更新适配器
               if(result != null)
               {

                   Log.d("","size"+urls.size());
                   urls.clear();
                   for( String id : result){
                   String url = baseUrl+""+imageSize+"/"+id;
                       Log.d("","result----------"+url);
                       urls.add(url);

                   }
               imageAdapter = new ImageAdapter(GridImageActivity.this ,urls);
               gridView.invalidateViews();
               }


           }


           private String[] getMovieDataFromJson(String movies ,int num ) throws JSONException {
               String[] result = new String[num];
               JSONObject movieJson = new JSONObject(movies);
               JSONArray idList = movieJson.getJSONArray("results");

               for(int i = 0; i< num; i++ )
               {
                   JSONObject movieObject = idList.getJSONObject(i);
                   String  idPath= movieObject.getString("poster_path");
                   result[i] = idPath;

               }


               return result;
           }


           @Override
           protected String[] doInBackground(String... params) {

               String moviesJsonStr = null;

               try {

                   URL url = new URL(ApplyUrl);
                   urlConnection = (HttpURLConnection) url.openConnection();
                   urlConnection.setRequestMethod("GET");
                   //网络请求不能再主线程
                   urlConnection.connect();

                   InputStream inputstream = urlConnection.getInputStream();
                   StringBuffer sb = new StringBuffer();

                   if(inputstream == null)
                   {
                       return null;
                   }
                   reader = new BufferedReader(new InputStreamReader(inputstream));
                   String line ="";
                   while( (line = reader.readLine())!= null)
                   {
                       sb.append(line+"\n");
                   }
                   if(sb.length() == 0) return null;
                    moviesJsonStr = sb.toString();
                   Log.d("","Movies id ------------"+moviesJsonStr);

               } catch (Exception e) {
                   e.printStackTrace();
               }
               finally {
                   urlConnection.disconnect();
               }
               try {
                   resultStr = getMovieDataFromJson(moviesJsonStr,9);
                   for(String s : resultStr)
                       Log.d("","ID_____________"+s);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               return resultStr;
           }
       }

}
