package com.example.yao.android_question;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.yao.dialog.MyDialog;
import com.example.yao.fregment.jian_f;
import com.example.yao.fregment.xuan_f;
import com.example.yao.pojo.question;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.List;

@ContentView(value = R.layout.activity_question)

public class question_activity extends AppCompatActivity {

    public static int userid;
    public static question i;

    int all;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Intent intent = getIntent();
        all = intent.getIntExtra("all", 0);
        i = (question) intent.getSerializableExtra("i");
        userid = intent.getIntExtra("userid", 0);
        a=intent.getIntExtra("a",0);
        setTitle("第"+a+"/"+all+"道题");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        get("http://115.29.136.118:8080/web-question/app/question?method=findone");


    }



    @Event(value = {R.id.shang,R.id.xia,R.id.shou},type = View.OnClickListener.class)

    private void onClick(View view){

        switch (view.getId()){

            case R.id.shang:{

                a--;

                if(a>0&&a<=all){

                    setTitle("第"+a+"/"+all+"道题");
                    get("http://115.29.136.118:8080/web-question/app/question?method=prev");

                }else{

                    Toast.makeText(question_activity.this, "已经是最后一题", Toast.LENGTH_SHORT).show();

                }

            }
            break;

            case R.id.xia:{

                a++;
                if(a>0&&a<=all){

                    setTitle("第"+a+"/"+all+"道题");
                    get("http://115.29.136.118:8080/web-question/app/question?method=next");

                }else{

                    Toast.makeText(question_activity.this, "已经是最后一题", Toast.LENGTH_SHORT).show();

                }


            }
            break;

            case R.id.shou:{

                final MyDialog dialog = new MyDialog(this);
                dialog.show();
                RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/mng/store?method=add");
                params.addBodyParameter("id",i.getId()+"");
                params.addBodyParameter("user_id",userid+"");
                
                x.http().post(params, new Callback.CommonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject result) {

                        try {
                            boolean success = result.getBoolean("success");
                            if (success){
                                Toast.makeText(question_activity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                            }else {

                                Toast.makeText(question_activity.this, result.getString("reason"), Toast.LENGTH_SHORT).show();

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

        }

    }

    public void get(String url){

        final MyDialog dialog = new MyDialog(this);
        dialog.show();
        RequestParams params = new RequestParams(url);
        params.addBodyParameter("id",i.getId()+"");
        params.addBodyParameter("user_id",userid+"");
        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                dialog.dismiss();

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Bundle bundle = new Bundle();

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
                    bundle.putSerializable("i",i);
                    xuan_f fragment = new xuan_f();
                    fragment.setArguments(bundle);
                    transaction.replace(R.id.rl_f,fragment);
                    transaction.commit();

                }else{

                    Log.i("question_activity",i.toString()+"===============");
                    bundle.putSerializable("i",i);
                    jian_f fragment =new jian_f();
                    fragment.setArguments(bundle);
                    transaction.replace(R.id.rl_f,fragment);
                    transaction.commit();
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
