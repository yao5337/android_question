package com.example.yao.fregment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yao.android_question.R;
import com.example.yao.android_question.login;

/**
 * Created by 89551 on 2016-09-06.
 */
public class three extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.three, null);
        TextView start = (TextView) view.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(),login.class);
                startActivity(it);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
            }
        });
        return view;
    }
}
