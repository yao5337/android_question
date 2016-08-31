package com.example.yao.android_question;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(value = R.layout.activity_zhuce)

public class zhuce extends AppCompatActivity {

    @ViewInject(value = R.id.ed_z_u)

    private EditText ed_u;

    @ViewInject(value = R.id.ed_z_k)

    private EditText ed_k;

    @ViewInject(value = R.id.ed_z_n)

    private  EditText ed_n;

    @ViewInject(value = R.id.ed_z_p)

    private EditText ed_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setTitle("注册账号");
    }

    @Event(value = R.id.btn_z,type = View.OnClickListener.class)

    private void onClick(View view){


        String user = ed_u.getText().toString();
        String key = ed_k.getText().toString();
        String nicheng = ed_n.getText().toString();
        String phone = ed_p.getText().toString();

        RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/registe");

        params.addBodyParameter("username",user);
        params.addBodyParameter("password",key);
        params.addBodyParameter("nickname",nicheng);
        params.addBodyParameter("telephone",phone);
        
        x.http().post(params, new Callback.CommonCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {

                boolean success = false;
                try {
                    success = result.getBoolean("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (success){

                    Toast.makeText(zhuce.this, "success", Toast.LENGTH_SHORT).show();
                    finish();
                }else{

                    try {
                        String reason = result.getString("reason");
                        Toast.makeText(zhuce.this, reason, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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

}
