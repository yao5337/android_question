package com.example.yao.fregment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.yao.android_question.R;
import com.example.yao.android_question.question_activity;
import com.example.yao.dialog.MyDialog;
import com.example.yao.pojo.question;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;

/**
 * Created by 89551 on 2016-09-02.
 */
public class jian_f extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fregment_j, null);
        final ScrollView sv = (ScrollView) view.findViewById(R.id.sv);
        final EditText ed_w = (EditText) view.findViewById(R.id.ed_w);
        Button btn_w = (Button) view.findViewById(R.id.btn_w);
        final TextView tv_w = (TextView) view.findViewById(R.id.answer_w);
        final TextView content = (TextView) view.findViewById(R.id.content_q);
        final TextView answer = (TextView) view.findViewById(R.id.answer);

        Bundle arguments = getArguments();
        final question i = (question) arguments.getSerializable("i");
        content.setText(i.getContent());
        btn_w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String my_a = ed_w.getText().toString();
                tv_w.setText(my_a);
                answer.setText(i.getAnswer());
                sv.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

}
