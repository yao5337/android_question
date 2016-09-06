package com.example.yao.android_question;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yao.sharedPreferences.share;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import static com.example.yao.android_question.fenlei.*;

@ContentView(value = R.layout.activity_set)

public class set extends AppCompatActivity {

    @ViewInject(value = R.id.kaiqi_set)
    private TextView kaiqi;
    @ViewInject(value = R.id.tupian_set)
    private TextView tupian;
    @ViewInject(value = R.id.cb_denglu_set)
    private CheckBox cb_denglu;
    @ViewInject(value = R.id.cb_tupian_set)
    private CheckBox cb_tupian;
    String username;
    String pass;

    public static share share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setTitle("设置");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        username=intent.getStringExtra("username");
        pass=intent.getStringExtra("password");
        share=new share(this,"a",MODE_APPEND);
        String name = share.getName();
        String pass = share.getPass();
        boolean b = share.getB();
        boolean d=share.getd();
        if (b){
            cb_tupian.setChecked(true);
            tupian.setText("在3G/4G和WIFI下都显示图片");
        }
        if (d){
            kaiqi.setText("已开启");
            cb_denglu.setChecked(true);
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("a");
        registerReceiver(fenlei.br,filter);

    }
    @Event(value = {R.id.nicheng_set,R.id.cb_denglu_set,R.id.cb_tupian_set,R.id.tuichu_set,R.id.huancun_set,R.id.women_set},type = View.OnClickListener.class)
    private void  onClick(View view){
        switch (view.getId()){

            case R.id.nicheng_set:{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view1 = LayoutInflater.from(this).inflate(R.layout.ed_view, null);
                builder.setView(view1);
                builder.setTitle("昵称设置");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
            break;
            case R.id.cb_denglu_set:{
                if (cb_denglu.isChecked()){
                    kaiqi.setText("已开启");
                    share.setPreferences(true,username,pass,false);
                }else {
                    kaiqi.setText("未开启");
                    share.setPreferences(false,username,pass,false);
                }
            }
            break;
            case R.id.cb_tupian_set:{
                if (cb_tupian.isChecked()){
                    if (cb_denglu.isChecked()){
                        tupian.setText("在3G/4G和WIFI下都显示图片");
                        share.setPreferences(true,username,pass,true);
                    }
                    else{
                        tupian.setText("在3G/4G和WIFI下都显示图片");
                        share.setPreferences(false,"","",true);
                    }
                }else {
                    if (cb_denglu.isChecked()){
                        tupian.setText("仅通过WIFI");
                        share.setPreferences(true,username,pass,false);
                    }
                    else{
                        tupian.setText("仅通过WIFI");
                        share.setPreferences(false,"","",false);
                    }
                }
            }
            break;

            case R.id.tuichu_set:{
                RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/logout");
                x.http().post(params, new Callback.CommonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        try {
                            boolean success = result.getBoolean("success");
                            if (success){
                                Intent intent = new Intent("a");
                                sendBroadcast(intent);
                                boolean b = share.getB();
                                share.setPreferences(false,"","",b);
                                Intent it = new Intent(set.this,login.class);
                                startActivity(it);
                                finish();
                                overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });

            }
            break;
            case R.id.huancun_set:{
                Toast.makeText(set.this, "执行清除缓存操作", Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.women_set:{
                Toast.makeText(set.this, "启动“关于我们”的界面...", Toast.LENGTH_SHORT).show();
            }
            break;

        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(fenlei.br);
    }
}
