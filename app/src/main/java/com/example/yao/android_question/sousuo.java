package com.example.yao.android_question;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setTitle("题目查找");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        userid = intent.getIntExtra("userid", 0);
        foot = getLayoutInflater().inflate(R.layout.foot, null);
    }

    View foot;
    @Event(value = R.id.tv_sousuo,type = View.OnClickListener.class)

    private void onClick(View view){

        String timu = ed_sousuo.getText().toString();

        final MyDialog dialog = new MyDialog(this);

        dialog.show();

        RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/question?method=list");

        params.addBodyParameter("questionName",timu);

        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(final JSONObject result) {
                dialog.dismiss();
                try {

                    JSONArray content = result.getJSONArray("content");

                    Gson gson = new Gson();
                    final List<question> list =gson.fromJson(content.toString(),new TypeToken<List<question>>(){}.getType());
                    adapter_sou adapter = new adapter_sou(sousuo.this,list);
                    lv_sousuo.addFooterView(foot);
                    lv_sousuo.setAdapter(adapter);
                    lv_sousuo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            if (i<list.size()){
                                Intent it = new Intent(sousuo.this, question_activity.class);
                                it.putExtra("all",list.size());
                                it.putExtra("a",i);
                                it.putExtra("i",list.get(i));
                                it.putExtra("userid",userid);
                                startActivity(it);
                                overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                            }else {
                                Toast.makeText(sousuo.this, "已加载全部", Toast.LENGTH_SHORT).show();
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
