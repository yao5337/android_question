package com.example.yao.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.example.yao.android_question.question_activity;

import java.util.List;

/**
 * Created by 89551 on 2016-09-02.
 */
public class fragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList;
    public fragmentAdapter(FragmentManager fragment, List<Fragment> fragmentList) {
        super(fragment);
        this.fragmentList=fragmentList;
    }


    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
