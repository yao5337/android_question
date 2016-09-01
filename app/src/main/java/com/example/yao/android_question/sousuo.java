package com.example.yao.android_question;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yao.adapter.adapter_sou;
import com.example.yao.dialog.MyDialog;
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

import java.util.ArrayList;
import java.util.List;

@ContentView(value = R.layout.activity_sousuo)

public class sousuo extends AppCompatActivity {

    @ViewInject(value = R.id.lv_sousuo)

    private ListView lv_sousuo;

    @ViewInject(value = R.id.ed_sousuo)

    private EditText ed_sousuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setTitle("题目查找");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Event(value = R.id.tv_sousuo,type = View.OnClickListener.class)

    private void onClick(View view){

        String timu = ed_sousuo.getText().toString();

        final MyDialog dialog = new MyDialog(this);

        dialog.show();

        RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/question?method=list");

        params.addBodyParameter("questionName",timu);

        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {

                Toast.makeText(sousuo.this, "。。。", Toast.LENGTH_SHORT).show();

                dialog.dismiss();

                try {
                    JSONArray content = result.getJSONArray("content");

                    Gson gson = new Gson();

                    List<question> list = gson.fromJson(content.toString(),new TypeToken<List<question>>(){}.getType());

                    for (question q:list
                         ) {

                        Log.i("sousuo",q.toString()+"=================");

                    }

                    adapter_sou adapter = new adapter_sou(sousuo.this,list);

                    lv_sousuo.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                dialog.dismiss();
                Toast.makeText(sousuo.this, "...", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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

        finish();
        overridePendingTransition(R.anim.fanhui_in,R.anim.fanhui_out);

    }
}