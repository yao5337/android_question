package com.example.yao.android_question;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yao.adapter.fragmentAdapter;
import com.example.yao.dialog.MyDialog;
import com.example.yao.fregment.jian_f;
import com.example.yao.fregment.pan_f;
import com.example.yao.pojo.question;
import com.google.gson.Gson;

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

    int userid;

    @ViewInject(value = R.id.vp)

    private ViewPager vp;
    question i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        Intent intent = getIntent();
        int all = intent.getIntExtra("all", 0);
        i = (question) intent.getSerializableExtra("i");
        userid = intent.getIntExtra("userid", 0);

        setTitle("第"+i+"/"+all+"道题");

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

    public static question q=null;
    public question get(String url){

        final MyDialog dialog = new MyDialog(this);
        dialog.show();

        RequestParams params = new RequestParams(url);
        params.addBodyParameter("id",i.getTypeid()+"");
        params.addBodyParameter("user_id",userid+"");
        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                dialog.dismiss();
                Gson gson = new Gson();
                q=gson.fromJson(result.toString(),question.class);

                if (q.getTypeid()==1||q.getTypeid()==2){

                    vp.setCurrentItem(1);

                }else{
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


        return q;

    }

}
