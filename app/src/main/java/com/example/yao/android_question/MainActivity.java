package com.example.yao.android_question;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


@ContentView(value = R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewInject(value = R.id.ll_w)

    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        ll.startAnimation(AnimationUtils.loadAnimation(this,R.anim.welcome));

        handler.sendEmptyMessageDelayed(0,2000);
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

            }

        }

    };

}
