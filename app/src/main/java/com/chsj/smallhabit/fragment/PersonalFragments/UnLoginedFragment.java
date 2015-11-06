package com.chsj.smallhabit.fragment.PersonalFragments;


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
import com.chsj.smallhabit.utils.EventUtils;
/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/4.
 */
/**
 * 没有登陆的时候的碎片界面
 */
public class UnLoginedFragment extends Fragment implements View.OnClickListener{


    public UnLoginedFragment() {
        // Required empty public constructor
    }

    private View bg_view;


    //登录按钮
    private Button login;
    //注册按钮
    private Button regiest;
    //忘记密码
    private TextView forgetPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bg_view = inflater.inflate(R.layout.fragment_un_logined, container, false);
        login = (Button) bg_view.findViewById(R.id.btn_sign_in);
        regiest = (Button) bg_view.findViewById(R.id.btn_sign_up);
        forgetPass = (TextView) bg_view.findViewById(R.id.btn_sign_forget);

        EventUtils.setEvent(this, login, regiest, forgetPass);

        return bg_view;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_sign_in:
                intent = new Intent(getContext(), EntryActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sign_up:
                intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
                break;

        }
    }


}
