package com.app.android.popularmovie.controller.Activity;

import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.app.android.popularmovie.R;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by SYSTEM on 2016/6/17.
 */
public class control_Main_Stars {

    public void submitAction(View view)
    {
        Log.e("", "Button OnClick");
        RatingBar star = (RatingBar)view.findViewById(R.id.rb_normal);
        Toast.makeText(view.getContext(), "提交成功,你的评分：" + star.getRating(), LENGTH_SHORT).show();
    }
}
