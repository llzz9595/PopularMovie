package com.app.android.popularmovie.controller.Activity.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.android.popularmovie.models.User;
import com.app.android.popularmovie.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SYSTEM on 2016/6/12.
 */
public class dicussAdapter extends BaseAdapter {

    private ArrayList<User> dicussList;
    private Context mContext;
   private int count ;
    public dicussAdapter(ArrayList<User> discussList, Context context) {
       super();
        this.dicussList = discussList;
        mContext = context;
        count = discussList.size();
    }

    @Override
    public int getCount() {

        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if(convertView == null)
        {
            holder = new ViewHolder();
          convertView = LayoutInflater.from(mContext).inflate(
                                 R.layout.layout_discuss, parent,false);
            holder.time= (TextView) convertView.findViewById(R.id.group_discuss_time);
            holder.info = (TextView) convertView.findViewById(R.id.group_discuss_read);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();
      if(dicussList.size() >0 ) {


          holder.time.setText(dicussList.get(position).getTime());
          holder.info.setText(dicussList.get(position).getWords());
          Log.e("", "ssssssssssssss" + dicussList.get(position));
      }
        else
      {
          holder.time.setText("暂无数据");
          holder.info.setText("暂无数据");
      }
        return convertView;
    }
    static class ViewHolder
    {
        public TextView time;
        public TextView info;
    }


    public void add(String text)
    {

        if( dicussList == null)
            dicussList = new ArrayList<User>();
        ArrayList<User> tempList = new ArrayList<User>();
        for(User s: dicussList)
           tempList.add(s);
        dicussList.clear();
        dicussList.add(new User((new Date()).toString(), text));

        for(User s : tempList)
           dicussList.add(s);
        count++;
        Log.e("","-----------------------"+getCount());
        this.notifyDataSetChanged();

    }

}
