package com.example.yao.android_question;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.yao.adapter.fragmentAdapter;
import com.example.yao.fregment.one;
import com.example.yao.fregment.three;
import com.example.yao.fregment.two;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(value = R.layout.activity_guide)
public class guide extends AppCompatActivity {

    @ViewInject(value = R.id.vp_o)
    private ViewPager vp;
    @ViewInject(value = R.id.image_one)
    private ImageView one;
    @ViewInject(value = R.id.image_two)
    private ImageView two;
    @ViewInject(value = R.id.image_three)
    private ImageView three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new one());
        list.add(new two());
        list.add(new three());
        vp.setAdapter(new fragmentAdapter(getSupportFragmentManager(),list));
        vp.setCurrentItem(0);
        setPostion(0);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPostion(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    public void setPostion(int postion){
        one.setSelected(postion==0);
        two.setSelected(postion==1);
        three.setSelected(postion==2);
    }
}
