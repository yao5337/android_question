package com.example.yao.android_question;

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

        final MyDialog dialog = new MyDialog(this);

        dialog.show();

        Intent intent = getIntent();

        user_id = intent.getIntExtra("user_id", 0);
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String nickname = intent.getStringExtra("nickname");

        niname.setText(nickname);

        RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/catalog?method=list");

        x.http().get(params, new Callback.CommonCallback<JSONArray>() {
            @Override
            public void onSuccess(JSONArray result) {

                dialog.dismiss();

                Gson gson=new Gson();

                final List<leibie> list = gson.fromJson(result.toString(),new TypeToken<List<leibie>>(){}.getType());

                final adapter_leibie adapter = new adapter_leibie(fenlei.this,list);

                gv.setAdapter(adapter);

                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent it = new Intent(fenlei.this,question_list.class);
                        it.putExtra("leixing",list.get(i));
                        it.putExtra("userid",user_id);
                        startActivity(it);
                        overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                    }
                });

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


                Intent it = new Intent(this,fenlei.class);
                startActivity(it);
                finish();

            }

            break;

            case R.id.tv_chazhao_c:{

                Intent it = new Intent(this,sousuo.class);
                it.putExtra("userid",user_id);
                startActivity(it);
                overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);

            }

            break;


        }

    }

}
