package com.app.android.popularmovie.controller.Activity;

import android.widget.ListView;

import com.app.android.popularmovie.view.AdapterHeightControl;
import com.app.android.popularmovie.controller.Activity.adapter.dicussAdapter;

/**
 * Created by SYSTEM on 2016/6/17.
 */
public class control_Main_discussAdapter {

    public control_Main_discussAdapter() {
    }

    public void adapterAdd(dicussAdapter adapter , String text ,ListView parent)
    {
        adapter.add(text);
        if(adapter.getCount() <= 5)
            AdapterHeightControl.setListViewHeightBasedOnChildren(parent);
    }
}
