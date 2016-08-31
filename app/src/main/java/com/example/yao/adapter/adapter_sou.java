package com.example.yao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yao.pojo.question;

import org.json.JSONObject;
import org.xutils.common.Callback;

import java.util.List;

/**
 * Created by 89551 on 2016-08-31.
 */
public class adapter_sou extends BaseAdapter{

    private List<question> list;

    LayoutInflater inflater;

    public adapter_sou(Context context, List<question> list) {

        inflater = LayoutInflater.from(context);

        this.list=list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        viewHolder holder = null;

        if (view==null){

//            inflater.inflate(R.)

        }

        return view;
    }

    public class viewHolder{

        TextView tv_q;

        TextView tv_y;

        TextView tv_t;

    }

}
