package com.chsj.smallhabit.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.chsj.smallhabit.EntryActivity;
import com.chsj.smallhabit.R;
import com.chsj.smallhabit.RegisterActivity;
import com.chsj.smallhabit.utils.Configs;
import com.chsj.smallhabit.utils.EventUtils;


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

    private View bg_view;
    private boolean isLoding;

    public PersonalFragment() {
        // Required empty public constructor
    }
    //登录按钮
    private Button   login;
    //注册按钮
    private Button  regiest;
    //忘记密码
    private TextView forgetPass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        isLoding = getContext().getSharedPreferences(Configs.SHARDPERFACE_NAME, Context.MODE_PRIVATE)
                .getBoolean(Configs.ISLOADING, false);
        if (isLoding) {
            bg_view = inflater.inflate(R.layout.fragment_personal, container, false);

        } else {
            bg_view = inflater.inflate(R.layout.activity_sign, container, false);
            login = (Button) bg_view.findViewById(R.id.btn_sign_in);
            regiest = (Button) bg_view.findViewById(R.id.btn_sign_up);
            forgetPass = (TextView) bg_view.findViewById(R.id.btn_sign_forget);

            EventUtils.setEvent(this,login,regiest,forgetPass);
        }

        return bg_view;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_sign_in:
                intent  = new Intent(getContext(), EntryActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sign_up:
                intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }
}
