package com.example.yao.android_question;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yao.adapter.adapter_sou;
import com.example.yao.dialog.MyDialog;
import com.example.yao.pojo.leibie;
import com.example.yao.pojo.question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ContentView(value = R.layout.activity_question_list)

public class question_list extends AppCompatActivity {

    @ViewInject(value = R.id.lv_l)
    private ListView lv_l;
    @ViewInject(value = R.id.sr_ql)
    private SwipeRefreshLayout sr;

     List<question> list;
    adapter_sou adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        Intent intent = getIntent();

        final leibie leixing= (leibie) intent.getSerializableExtra("leixing");

        final int userid = intent.getIntExtra("userid", 0);
        setTitle(leixing.getName());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final MyDialog dialog = new MyDialog(this);
        dialog.show();
        RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/question?method=list");
        params.addBodyParameter("catalogId",leixing.getId()+"");
        final View view = getLayoutInflater().inflate(R.layout.foot, null);
        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                dialog.dismiss();
                try {
                    JSONArray content = result.getJSONArray("content");
                    Gson gson = new Gson();
                    list =gson.fromJson(content.toString(),new TypeToken<List<question>>(){}.getType());
                    adapter = new adapter_sou(question_list.this,list);
                    lv_l.addFooterView(view);
                    lv_l.setAdapter(adapter);
                    lv_l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i<list.size()){
                                Intent it = new Intent(question_list.this,question_activity.class);
                                it.putExtra("all",list.size());
                                it.putExtra("i",list.get(i));
                                it.putExtra("userid",userid);
                                it.putExtra("a",i+1);
                                startActivity(it);
                                overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                            }else{
                                Toast.makeText(question_list.this, "已加载全部", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
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

        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dialog.show();
                RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/question?method=list");
                params.addBodyParameter("catalogId",leixing.getId()+"");
                x.http().post(params, new Callback.CommonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        dialog.dismiss();
                        try {
                            JSONArray content = result.getJSONArray("content");
                            Gson gson = new Gson();
                            list.clear();
                            list =gson.fromJson(content.toString(),new TypeToken<List<question>>(){}.getType());
                            adapter = new adapter_sou(question_list.this,list);
                            sr.setRefreshing(false);
                            lv_l.setAdapter(adapter);
                            lv_l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    if (i<list.size()){
                                        Intent it = new Intent(question_list.this,question_activity.class);
                                        it.putExtra("all",list.size());
                                        it.putExtra("i",list.get(i));
                                        it.putExtra("userid",userid);
                                        it.putExtra("a",i+1);
                                        startActivity(it);
                                        overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                                    }else{
                                        Toast.makeText(question_list.this, "已加载全部", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
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
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:{

                finish();
                overridePendingTransition(R.anim.fanhui_in,R.anim.fanhui_out);
                return true;
            }


            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fanhui_in,R.anim.fanhui_out);
    }


}
