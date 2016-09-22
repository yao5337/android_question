package com.example.yao.android_question;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yao.adapter.adapter_leibie;
import com.example.yao.dialog.MyDialog;
import com.example.yao.fregment.chengjiu_f;
import com.example.yao.fregment.gv_f;
import com.example.yao.fregment.lv_f;
import com.example.yao.pojo.leibie;
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

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

@ContentView(value = R.layout.activity_fenlei)
public class fenlei extends AppCompatActivity {
    @ViewInject(value = R.id.toolbar)
    private Toolbar toolbar_c;
    @ViewInject(value = R.id.cehua)
    private DrawerLayout cehua;
    @ViewInject(value = R.id.gv)
    private GridView gv;
    @ViewInject(value = R.id.niname)
    private TextView niname;
    int user_id;
    @ViewInject(value = R.id.cehua)
    private DrawerLayout ce;
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
        br = new broad();
    }
    public static broad br;
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

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @ViewInject(value = R.id.tv_fenlei_c)
    private TextView fenlei;
    @ViewInject(value = R.id.tv_chazhao_c)
    private TextView chazhao;
    @ViewInject(value = R.id.tv_chengjiu_c)
    private TextView chengjiu;
    @ViewInject(value = R.id.tv_shoucang_c)
    private TextView shoucang;
    @Event(value = {R.id.tv_fenlei_c,R.id.relative,R.id.tv_chazhao_c,R.id.tv_chengjiu_c,R.id.tv_shoucang_c,R.id.tv_shezhi_c,R.id.tv_tuichu_c,R.id.linear,R.id.touxiang_t},type = View.OnClickListener.class)
    private void onClick(View view){
        setSelect(view);
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
                ce.closeDrawers();
            }
            break;

            case R.id.tv_chazhao_c:{

                Intent it = new Intent(this,sousuo.class);
                it.putExtra("userid",user_id);
                startActivity(it);
                overridePendingTransition(R.anim.welcome_in,R.anim.welcome_out);
                ce.closeDrawers();
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
                ce.closeDrawers();
            }
            break;

            case R.id.tv_chengjiu_c:{

                toolbar_c.setTitle("我的成就");
//                Bundle bundle = new Bundle();
//                bundle.putInt("userid",user_id);
//                bundle.putString("url","http://115.29.136.118:8080/web-question/app/catalog?method=list");
                chengjiu_f chengjiu = new chengjiu_f();
//                f.setArguments(bundle);
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.ll_c_f,chengjiu);
                transaction1.commit();
                ce.closeDrawers();
            }

            break;
            case R.id.tv_shezhi_c:{
                ce.closeDrawers();
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

            case R.id.linear:{

            }
            break;

            case R.id.touxiang_t:{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("上传头像");
                String[] fangshi={"本地图片","拍照上传"};
                builder.setItems(fangshi, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:{
                                getImageFromAlbum();
                            }
                            break;
                            case 1:{
                                getImageFromCamera();
                            }
                            break;
                        }
                    }
                });
                builder.show();
            }
            break;
            case R.id.relative:{

            }
            break;
        }

    }
    @ViewInject(value = R.id.touxiang_t)
    private ImageView touxiang;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode ==0) {
            final Uri uri = data.getData();
            Log.i("fenlei",uri+"");
            RequestParams params = new RequestParams("http://115.29.136.118:8080/web-question/app/upload");
            params.addBodyParameter("user_id",user_id+"");
            params.addBodyParameter("picurl",uri+"");
            x.http().post(params, new Callback.CommonCallback<JSONObject>() {
                @Override
                public void onSuccess(JSONObject result) {
                    try {
                        final boolean success = result.getBoolean("success");
                        if (success){
                            Toast.makeText(fenlei.this, "1111", Toast.LENGTH_SHORT).show();
                            touxiang.setImageURI(uri);
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
            touxiang.setImageURI(uri);
        }else if (requestCode == 1 ) {
            Uri uri = data.getData();
            if(uri == null){
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap photo = (Bitmap) bundle.get("data"); //get bitmap
                    touxiang.setImageBitmap(photo);
//                    String spath="data/data/"+getPackageName()+"/android_question_picture";
//                    saveImage(photo, spath);
                } else {
                    Toast.makeText(getApplicationContext(), "...", Toast.LENGTH_LONG).show();
                    return;
                }
            }else{

            }

        }
    }

    public static void saveImage(Bitmap photo, String spath) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(spath, false));
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSelect(View view){
        fenlei.setSelected(view.getId()==R.id.tv_fenlei_c);
        chazhao.setSelected(view.getId()==R.id.tv_chazhao_c);
        chengjiu.setSelected(view.getId()==R.id.tv_chengjiu_c);
        shoucang.setSelected(view.getId()==R.id.tv_shoucang_c);
    }

    public class broad extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            fenlei.this.finish();
        }
    }

    protected void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, 0);
    }

    protected void getImageFromCamera() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(getImageByCamera, 1);
        }
        else {
            Toast.makeText(getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

}
