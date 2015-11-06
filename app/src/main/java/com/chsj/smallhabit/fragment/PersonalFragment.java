package com.chsj.smallhabit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chsj.smallhabit.MyApplication;
import com.chsj.smallhabit.R;
import com.chsj.smallhabit.fragment.PersonalFragments.LoginedFragment;
import com.chsj.smallhabit.fragment.PersonalFragments.UnLoginedFragment;


/**
 * ProjectName: com.chsj.smallhabit.utils
 * Created by : ChSJ.Team
 * Email:  15001045515@163.com
 * user: 杨空明
 * on 2015-11-03.
 */

/**
 * 我的界面碎片，主页面第三tab页
 */
public class PersonalFragment extends Fragment{

    private View bg_view;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager manager;

    public PersonalFragment() {
        // Required empty public constructor
    }

    private Fragment fragmengs[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bg_view = inflater.inflate(R.layout.activity_sign, container, false);
        fragmengs = new Fragment[2];
        manager = getChildFragmentManager();
        fragmentTransaction = manager.beginTransaction();
        if(savedInstanceState==null){
            fragmengs[0]  = new UnLoginedFragment();
            fragmengs[1] = new LoginedFragment();
            for (int i = 0; i < fragmengs.length; i++) {
                fragmentTransaction.add(R.id.fragment_personal,fragmengs[i],"personal"+i);
            }
            fragmentTransaction.commit();
        }else{
            for (int i = 0; i < fragmengs.length; i++) {
                fragmengs[i] = manager.findFragmentByTag("personal"+i);
            }
        }
        return bg_view;
    }

    private void setFragment(FragmentTransaction fragmentTransaction) {
        for (int i = 0; i < fragmengs.length; i++) {
            fragmentTransaction.hide(fragmengs[i]);
        }
        if (MyApplication.ISLOADING()){
            Log.d("debug111","登入成功");
            fragmentTransaction.show(fragmengs[1]);
        }else{
            fragmentTransaction.show(fragmengs[0]);
            Log.d("debug111", "登入失败");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("debug11","进入OnResume");
        fragmentTransaction = manager.beginTransaction();
        setFragment(fragmentTransaction);
        fragmentTransaction.commit();
    }


}
