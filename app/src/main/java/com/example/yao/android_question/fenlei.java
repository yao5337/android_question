package com.example.yao.android_question;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.yao.adapter.adapter_leibie;
import com.example.yao.dialog.MyDialog;
import com.example.yao.fregment.gv_f;
import com.example.yao.fregment.lv_f;
import com.example.yao.pojo.leibie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

@ContentView(value = R.layout.activity_fenlei)
public class fenlei extends AppCompatActivity {
    @ViewInject(value = R.id.toolbar)
    private Toolbar toolbar_c;
    @ViewInject(value = R.id.cehua)
    private DrawerLayout cehua;
    @ViewInject(value = R.id.tv_fenlei_c)
    private TextView fenlei_c;
    @ViewInject(value = R.id.tv_chazhao_c)
    private TextView chazhao_c;
    @ViewInject(value = R.id.gv)
    private GridView gv;
    @ViewInject(value = R.id.niname)
    private TextView niname;
    int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        toolbar_c.setTitle("分类练习");
        toolbar_c.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar_c);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, cehua, toolbar_c, R.string.open, R.string.close);
        drawerToggle.syncState();
        cehua.setDrawerListener(drawerToggle);
        Intent intent = getIntent();
        user_id = intent.getIntExtra("user_id", 0);
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        String nickname = intent.getStringExtra("nickname");
        niname.setText(nickname);
        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("userid",user_id);
        bundle.putString("url","http://115.29.136.118:8080/web-question/app/catalog?method=list");
        gv_f f = new gv_f();
        f.setArguments(bundle);
        transaction.replace(R.id.ll_c_f,f);
        transaction.commit();

    }

    String username;
    String password;
    FragmentManager manager;
    FragmentTransaction transaction;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent it = new Intent(this,sousuo.class);
        startActivity(it);
        overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
        return super.onOptionsItemSelected(item);
    }
    @Event(value = {R.id.tv_fenlei_c,R.id.tv_chazhao_c,R.id.tv_chengjiu_c,R.id.tv_shoucang_c,R.id.tv_shezhi_c,R.id.tv_tuichu_c},type = View.OnClickListener.class)
    private void onClick(View view){
        switch (view.getId()){
            case R.id.tv_fenlei_c:{
                toolbar_c.setTitle("分类练习");
                Bundle bundle = new Bundle();
                bundle.putInt("userid",user_id);
                bundle.putString("url","http://115.29.136.118:8080/web-question/app/catalog?method=list");
                gv_f f = new gv_f();
                f.setArguments(bundle);
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.ll_c_f,f);
                transaction1.commit();
            }
            break;

            case R.id.tv_chazhao_c:{

                Intent it = new Intent(this,sousuo.class);
                it.putExtra("userid",user_id);
                startActivity(it);
                overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
            }
            break;
            case R.id.tv_shoucang_c:{

                toolbar_c.setTitle("我的收藏");
                Bundle bundle = new Bundle();
                bundle.putInt("userid",user_id);
                lv_f f = new lv_f();
                f.setArguments(bundle);
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.ll_c_f,f);
                transaction1.commit();
            }
            break;

            case R.id.tv_chengjiu_c:{

                toolbar_c.setTitle("我的成就");
                Bundle bundle = new Bundle();
                bundle.putInt("userid",user_id);
                bundle.putString("url","http://115.29.136.118:8080/web-question/app/catalog?method=list");
                gv_f f = new gv_f();
                f.setArguments(bundle);
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.ll_c_f,f);
                transaction1.commit();
            }

            break;
            case R.id.tv_shezhi_c:{

                Intent it = new Intent(fenlei.this,set.class);
                it.putExtra("username",username);
                it.putExtra("password",password);
                startActivity(it);
                overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);

            }
            break;

            case R.id.tv_tuichu_c:{

                finish();

            }
            break;
        }

    }

}
