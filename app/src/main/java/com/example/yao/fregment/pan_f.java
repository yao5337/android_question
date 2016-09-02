package com.example.yao.fregment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yao.android_question.R;
import com.example.yao.android_question.question_activity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 89551 on 2016-09-02.
 */
public class pan_f extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fregment_p, null);

        TextView content_p= (TextView) view.findViewById(R.id.content_p);

        CheckBox checkBox1= (CheckBox) view.findViewById(R.id.cb_1);

        CheckBox checkBox2= (CheckBox) view.findViewById(R.id.cb_2);

        CheckBox checkBox3= (CheckBox) view.findViewById(R.id.cb_3);

        CheckBox checkBox4= (CheckBox) view.findViewById(R.id.cb_4);

        String options = question_activity.q.getOptions();

        JsonArray array = new JsonArray();

        array.add(options);

        List<CheckBox> list = new ArrayList<CheckBox>();

        list.add(checkBox1);

        list.add(checkBox2);

        list.add(checkBox3);

        list.add(checkBox4);

        for (int i = 0 ; i<array.size();i++){

            try {
                JSONObject object= new JSONObject(array.get(i).toString());

                String title = object.getString("title");
                boolean checked = object.getBoolean("checked");

                list.get(i).setText(title);

                if (checked){

                    list.get(i).setChecked(true);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        
        return  view;

    }


}
