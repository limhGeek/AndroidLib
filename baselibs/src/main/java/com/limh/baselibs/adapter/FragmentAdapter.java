package com.limh.baselibs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author limh
 * @function 通用Fragment适配器
 * @date 2018/11/21 11:04
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return null != fragments ? fragments.get(position) : null;
    }

    @Override
    public int getCount() {
        return null != fragments ? fragments.size() : 0;
    }
}
