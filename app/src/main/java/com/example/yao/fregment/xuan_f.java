package com.example.yao.fregment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yao.android_question.R;
import com.example.yao.android_question.question_activity;
import com.example.yao.pojo.question;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 89551 on 2016-09-02.
 */
public class xuan_f extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fregment_p, null);
        TextView get = (TextView) view.findViewById(R.id.get_answer);
        final LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll_a);
        TextView content_p= (TextView) view.findViewById(R.id.content_p);
        CheckBox checkBox1= (CheckBox) view.findViewById(R.id.cb_1);
        CheckBox checkBox2= (CheckBox) view.findViewById(R.id.cb_2);
        CheckBox checkBox3= (CheckBox) view.findViewById(R.id.cb_3);
        CheckBox checkBox4= (CheckBox) view.findViewById(R.id.cb_4);
        CheckBox checkBox1_w= (CheckBox) view.findViewById(R.id.cb_1_w);
        CheckBox checkBox2_w= (CheckBox) view.findViewById(R.id.cb_2_w);
        CheckBox checkBox3_w= (CheckBox) view.findViewById(R.id.cb_3_w);
        CheckBox checkBox4_w= (CheckBox) view.findViewById(R.id.cb_4_w);
        Bundle arguments = getArguments();
        question i = (question) arguments.getSerializable("i");
        content_p.setText(i.getContent());
        String options = i.getOptions();
        JSONArray array = null;
        if (options!=null){
            try {
                array = new JSONArray(options);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final List<CheckBox> list = new ArrayList<CheckBox>();
            list.add(checkBox1);
            list.add(checkBox2);
            list.add(checkBox3);
            list.add(checkBox4);
            final JSONArray finalArray = array;
            List<CheckBox> list_w=new ArrayList<CheckBox>();
            list_w.add(checkBox1_w);
            list_w.add(checkBox2_w);
            list_w.add(checkBox3_w);
            list_w.add(checkBox4_w);
            for (int b = 0;b<finalArray.length();b++){
                JSONObject object= null;
                try {
                    object = new JSONObject(finalArray.get(b).toString());
                    String title = object.getString("title");
                    list_w.get(b).setText(title);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            get.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ll.setVisibility(View.VISIBLE);
                    for (int a = 0; a< finalArray.length(); a++){
                        try {
                            JSONObject object= new JSONObject(finalArray.get(a).toString());
                            String title = object.getString("title");
                            boolean checked = object.getBoolean("checked");
                            list.get(a).setText(title);
                            if (checked){
                                list.get(a).setChecked(true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        return  view;
    }
}
