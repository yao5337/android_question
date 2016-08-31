package com.example.yao.android_question;

import android.app.Activity;
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
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

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
                startActivity(it);

            }

            break;


        }

    }

}
