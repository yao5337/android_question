package com.example.yao.android_question;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.yao.adapter.fragmentAdapter;
import com.example.yao.dialog.MyDialog;
import com.example.yao.fregment.jian_f;
import com.example.yao.fregment.pan_f;
import com.example.yao.pojo.question;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ContentView(value = R.layout.activity_question)

public class question_activity extends AppCompatActivity {

    public static int userid;

    @ViewInject(value = R.id.vp)

    private ViewPager vp;
    public static question i;

    int all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        Log.i("question_activity","+++++++++++++++");
        Intent intent = getIntent();
        all = intent.getIntExtra("all", 0);
        i = (question) intent.getSerializableExtra("i");
        userid = intent.getIntExtra("userid", 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        fragmentList.add(new jian_f());
        fragmentList.add(new pan_f());
        vp.setAdapter(new fragmentAdapter(getSupportFragmentManager(),fragmentList));

        get("http://115.29.136.118:8080/web-question/app/question?method=findone");


    }

    @Event(value = {R.id.shang,R.id.xia,R.id.shou},type = View.OnClickListener.class)

    private void onClick(View view){

        switch (view.getId()){

            case R.id.shang:{

                get("http://115.29.136.118:8080/web-question/app/question?method=prev");

            }
            break;

            case R.id.xia:{

                get("http://115.29.136.118:8080/web-question/app/question?method=next");

            }
            break;

        }

    }

    public void get(String url){

        setTitle("第"+i.getId()+"/"+all+"道题");
        final MyDialog dialog = new MyDialog(this);
        dialog.show();

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("id",i.getId()+"");
        params.addBodyParameter("user_id",userid+"");
        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                dialog.dismiss();

                i=new question();

                try {
                    i.setContent(result.getString("content"));
                    i.setId(result.getInt("id"));
                    i.setAnswer(result.getString("answer"));
                    i.setCataid(result.getInt("cataid"));
                    i.setTime(result.getLong("pubTime"));
                    i.setTypeid(result.getInt("typeid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (i.getTypeid()==1||i.getTypeid()==2){

                    try {
                        i.setOptions(result.getString("options"));
                        Log.i("question_activity",i.toString()+"===============");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    vp.setCurrentItem(1);

                }else{

                    Log.i("question_activity",i.toString()+"===============");
                    vp.setCurrentItem(0);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                dialog.dismiss();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

}
