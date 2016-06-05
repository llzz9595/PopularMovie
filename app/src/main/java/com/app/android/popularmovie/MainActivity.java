package com.app.android.popularmovie;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.full_image);

        //获取传递过来的imageUrl，并将image显示出来，注意设置placeholer
     //   Intent intent = getIntent();
       // String imageUrl = intent.getExtras().getString("imageUrl");
//        Picasso.with(MainActivity.this).load(R.drawable.image_palceholer).into(imageView);
       // Picasso.with(MainActivity.this).load().placeholder(R.drawable.image_palceholer).error(R.mipmap.ic_launcher).into(imageView);

        Picasso.with(MainActivity.this).load("http://i.imgur.com/DvpvklR.png")
                .placeholder(R.drawable.image_palceholer)
                .resize(20,20)
                .error(R.mipmap.ic_launcher)
               .into(imageView);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.listener(new Picasso.Listener()
        {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                exception.printStackTrace();
            }
        });
        builder.build().load("http://i.imgur.com/DvpvklR.png").into(imageView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
