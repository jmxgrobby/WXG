package com.chsj.smallhabit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.chsj.smallhabit.fragment.squalefragment.BaseFragment;

import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */
public class SquareActivityPageAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;

    public SquareActivityPageAdapter(android.support.v4.app.FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(fragments!=null)
            ret = fragments.size();
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String ret = null;
        BaseFragment fragment = (BaseFragment) fragments.get(position);
        notifyDataSetChanged();
        ret = fragment.getFragmentTitle();
        return  ret;
    }
}
