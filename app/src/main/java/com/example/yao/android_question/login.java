package com.example.yao.android_question;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yao.dialog.MyDialog;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(value = R.layout.activity_login)

public class login extends AppCompatActivity {

    @ViewInject(value = R.id.ed_l_u)

    private EditText ed_user;

    @ViewInject(value = R.id.ed_l_k)

    private EditText ed_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setTitle("登录");
    }

    @Event(value ={ R.id.btn_l,R.id.tv_wangji_l,R.id.tv_zhuce_l},type = View.OnClickListener.class)

    private void onClick(View view){

        switch (view.getId()){

            case R.id.btn_l:{

                String user = ed_user.getText().toString();
                String key = ed_key.getText().toString();

                final MyDialog dialog = new MyDialog(this);
                dialog.show();

                RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/login");
                params.addBodyParameter("username",user);
                params.addBodyParameter("password",key);

                x.http().post(params, new Callback.CommonCallback<JSONObject>() {
                    @Override
                    public void onSuccess(JSONObject result) {

                        dialog.dismiss();

                        try {
                            boolean success = result.getBoolean("success");

                            if (success==true){

                                JSONObject user1 = result.getJSONObject("user");
                                int user_id = user1.getInt("id");
                                String username = user1.getString("username");
                                String password = user1.getString("password");
                                String nickname = user1.getString("nickname");

                                Intent it = new Intent(login.this,fenlei.class);
                                it.putExtra("user_id",user_id);
                                it.putExtra("username",username);
                                it.putExtra("password",password);
                                it.putExtra("nickname",nickname);
                                startActivity(it);
                                finish();
                                overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);

                            }
                            else{

                                String reason = result.getString("reason");
                                Toast.makeText(login.this, reason, Toast.LENGTH_SHORT).show();

                            }

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
            break;

            case R.id.tv_zhuce_l:{

                Intent it = new Intent(login.this,zhuce.class);
                startActivity(it);

            }

            break;

        }
    }




}
