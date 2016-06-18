package com.app.android.popularmovie.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.android.popularmovie.controller.Activity.adapter.dicussAdapter;

/**
 * Created by SYSTEM on 2016/6/17.
 */
public class AdapterHeightControl {

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        dicussAdapter listAdapter = (dicussAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
//      if(listAdapter.getCount() <= 10) {
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);

        listView.setLayoutParams(params);

    }

}
