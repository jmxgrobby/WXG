package com.chsj.smallhabit.fragment.squalefragment;

import android.support.v4.app.Fragment;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * Viewpager里面的fragment的父类，有一个获取title的抽象方法
 */
public abstract class BaseFragment extends Fragment {
    public abstract  String getFragmentTitle();
}
