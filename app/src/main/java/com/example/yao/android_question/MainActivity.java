package com.example.yao.android_question;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yao.sharedPreferences.share;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(value = R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewInject(value = R.id.ll_w)
    private LinearLayout ll;

    String name;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        share share = new share(this,"a",MODE_PRIVATE);
        name = share.getName();
        pass = share.getPass();
        boolean d = share.getd();
        boolean time = share.getTime();
        if (time){
            ll.startAnimation(AnimationUtils.loadAnimation(this,R.anim.welcome));
            share.setTime(false);
           handler.sendEmptyMessageDelayed(3,2000);
        }
        else {
            if (d){
                ll.startAnimation(AnimationUtils.loadAnimation(this,R.anim.welcome));
                handler.sendEmptyMessageDelayed(1,2000);
            }else {
                ll.startAnimation(AnimationUtils.loadAnimation(this,R.anim.welcome));
                handler.sendEmptyMessageDelayed(0,2000);
            }
        }
    }

    public Handler handler = new Handler(){

        public void handleMessage(Message msg){
            switch (msg.what){
                case  0:{
                    Intent it = new Intent(MainActivity.this,login.class);
                    startActivity(it);
                    finish();
                    overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                }
                break;
                case 1:{

                    RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/login");
                    params.addBodyParameter("username",name);
                    params.addBodyParameter("password",pass);
                    x.http().post(params, new org.xutils.common.Callback.CommonCallback<JSONObject>() {
                        @Override
                        public void onSuccess(JSONObject result) {
                            try {
                                boolean success = result.getBoolean("success");

                                if (success==true){
                                    JSONObject user1 = result.getJSONObject("user");
                                    int user_id = user1.getInt("id");
                                    String username = user1.getString("username");
                                    String password = user1.getString("password");
                                    String nickname = user1.getString("nickname");
                                    Intent it = new Intent(MainActivity.this,fenlei.class);
                                    it.putExtra("user_id",user_id);
                                    it.putExtra("username",username);
                                    it.putExtra("password",password);
                                    it.putExtra("nickname",nickname);
                                    startActivity(it);
                                    finish();
                                    overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                                }
                                else{
                                    handler.sendEmptyMessageDelayed(0,2000);
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

                case 3:{
                    Intent intent = new Intent(MainActivity.this,guide.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                }
                break;

            }
        }

    };

}
