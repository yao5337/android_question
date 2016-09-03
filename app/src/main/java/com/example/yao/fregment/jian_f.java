package com.example.yao.fregment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        final TextView content = (TextView) view.findViewById(R.id.content_q);

        final TextView answer = (TextView) view.findViewById(R.id.answer);

        Bundle arguments = getArguments();

        question i = (question) arguments.getSerializable("i");

        content.setText(i.getContent());

        answer.setText(i.getAnswer());

        return view;
    }

}
