package com.app.android.popularmovie;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.android.popularmovie.controller.Activity.adapter.dicussAdapter;
import com.app.android.popularmovie.controller.Activity.control_Main_discussAdapter;
import com.app.android.popularmovie.models.MovieFlavor;
import com.app.android.popularmovie.models.User;
import com.app.android.popularmovie.view.AdapterHeightControl;
import com.app.android.popularmovie.view.MyScrollView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends Activity {

    private MyScrollView sv;
    private ArrayList<User> dicussList;
    private dicussAdapter adapter;
    private EditText editText;
    private ListView discuss;

    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//        Button submit = (Button) findViewById(R.id.sub_btn);
        discuss = (ListView) findViewById(R.id.Discuss);
       sv = (MyScrollView) findViewById(R.id.myScrollView);
      sv.setListView(discuss);

        sv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("", "onTouch down");

                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("", "onTouch up");
                        break;
                    default:
                        Log.e("", "onTouch default");
                        break;
                }



                return false;
            }

        });

        Button submit = (Button) findViewById(R.id.sub_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e("", "Button OnClick");
//                RatingBar star = (RatingBar) findViewById(R.id.rb_normal);
//                Toast.makeText(MainActivity.this, "提交成功,你的评分：" + star.getRating(), LENGTH_SHORT).show();

            }
        });
//        ImageView imageView = (ImageView) findViewById(R.id.full_image);

        //获取传递过来的imageUrl，并将image显示出来，注意设置placeholer
        Intent intent = getIntent();
        MovieFlavor myMovie = intent.getExtras().getParcelable("movieItem");

        ImageView poster = (ImageView) findViewById(R.id.Item_Image);
        Picasso.with(MainActivity.this).load(myMovie.getMovie_Poster()).placeholder(R.drawable.image_palceholer)
                .resize(500, 700)
                .into(poster);

        TextView title = (TextView) findViewById(R.id.Item_Title);
        title.setText(myMovie.getMovie_Name());

        TextView date = (TextView) findViewById(R.id.Item_Date);
        date.setText("上映日期: " + myMovie.getMovie_Date());

        TextView score = (TextView) findViewById(R.id.Item_Score);
        score.setText("评分: " + myMovie.getMovie_Score());

        TextView overView = (TextView) findViewById(R.id.Item_Overview);
        overView.setText("概要: " + myMovie.getMovie_Synopsis());




        //为防止layout界面上的EditText在进入页面时就弹出输入法,隐藏软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dicussList = new ArrayList<User>();
        adapter = new dicussAdapter(dicussList, MainActivity.this);

        discuss.setAdapter(adapter);
        AdapterHeightControl.setListViewHeightBasedOnChildren(discuss);
        editText = (EditText) findViewById(R.id.group_discuss);

////       discuss.setAdapter(discussAdapter);
        Button send = (Button) findViewById(R.id.group_discuss_submit);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();
                if(!text.equals("") ) {
                    control_Main_discussAdapter cmd = new control_Main_discussAdapter();
                    cmd.adapterAdd(adapter, text, discuss);

                    editText.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    //
                    Toast.makeText(MainActivity.this, "发送成功！", LENGTH_SHORT).show();
                }
                else{
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    //
                    Toast.makeText(MainActivity.this, "发送失败！评论不能为空", LENGTH_SHORT).show();
                }
            }
        });



    }

    //dp转换为像素
    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }



}
