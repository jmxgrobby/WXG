package com.chsj.smallhabit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chsj.smallhabit.R;


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
public class PersonalFragment extends Fragment {


    public PersonalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_personal, container, false);
        return view;
    }


}
