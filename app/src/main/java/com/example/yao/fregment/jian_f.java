package com.example.yao.fregment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yao.android_question.R;
import com.example.yao.android_question.question_activity;

/**
 * Created by 89551 on 2016-09-02.
 */
public class jian_f extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fregment_j, null);

        TextView content = (TextView) view.findViewById(R.id.content_q);

        TextView answer = (TextView) view.findViewById(R.id.answer);

        content.setText(question_activity.q.getContent());

        answer.setText(question_activity.q.getAnswer());

        return view;
    }

}
