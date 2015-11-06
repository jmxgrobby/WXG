package com.chsj.smallhabit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.chsj.smallhabit.utils.Configs;
import com.chsj.smallhabit.utils.EventUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MoreSettingsActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.activity_more_settings_back)
    ImageView back;
    @Bind(R.id.activity_more_settings_person_info)
    LinearLayout PersonInfo;
    @Bind(R.id.activity_more_settings_user_back)
    LinearLayout UserBack;
    @Bind(R.id.activity_more_settings_clear)
    LinearLayout Clear;
    @Bind(R.id.activity_more_settings_help)
    LinearLayout Help;
    @Bind(R.id.activity_more_settings_tell_friend)
    LinearLayout TellFriend;
    @Bind(R.id.activity_more_settings_check)
    LinearLayout Check;
    @Bind(R.id.activity_more_settings_about)
    LinearLayout About;
    @Bind(R.id.activity_more_settings_habbit)
    LinearLayout Habbit;
    @Bind(R.id.activity_more_settings_open_music)
    LinearLayout OpenMusic;
    @Bind(R.id.activity_more_settings_open_ts)
    LinearLayout OpenTs;
    @Bind(R.id.activity_more_settings_open_habbit)
    LinearLayout OpenHabbit;
    @Bind(R.id.activity_more_settings_hand_password)
    LinearLayout HandPassword;
    @Bind(R.id.activity_more_settings_unlogin)
    Button Unlogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_settings);
        ButterKnife.bind(this);

        EventUtils.setEvent(this, back, PersonInfo, UserBack, Check, Help, TellFriend,
                Check, About, Habbit, OpenMusic, OpenTs, OpenHabbit,
                HandPassword,Unlogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.activity_more_settings_back:
                finish();
                break;
            //个人资料
            case R.id.activity_more_settings_person_info:
                Toast.makeText(MoreSettingsActivity.this, "个人资料", Toast.LENGTH_SHORT).show();
                break;
            //用户反馈
            case R.id.activity_more_settings_user_back:
                long test = 1446762135250l;
                Date date = new Date(test);
                Log.d("debug1111"," "+
                               new  SimpleDateFormat("dd:hh").format(date)
                );
                break;
            //清除缓存
            case R.id.activity_more_settings_clear:
                break;
            //帮助中心
            case R.id.activity_more_settings_help:
                break;
            //告诉小伙伴们
            case R.id.activity_more_settings_tell_friend:
                break;
            //检查更新
            case R.id.activity_more_settings_check:
                break;
            //关于
            case R.id.activity_more_settings_about:
                break;
            //习惯介绍
            case R.id.activity_more_settings_habbit:
                break;
            //开启提示音
            case R.id.activity_more_settings_open_music:
                break;
            //开启推送
            case R.id.activity_more_settings_open_ts:
                break;
            //开启习惯介绍
            case R.id.activity_more_settings_open_habbit:
                break;
            //手势密码锁定
            case R.id.activity_more_settings_hand_password:
                break;
            //退出账号
            case R.id.activity_more_settings_unlogin:
                new AlertDialog.Builder(this)
                        .setTitle("提示")
                        .setMessage("您确定退出该帐号？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }
                        )
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               if(MyApplication.ISLOADING()){
                                   SharedPreferences sharedPreferences = getSharedPreferences(Configs.SHARDPERFACE_NAME, MODE_PRIVATE);
                                   SharedPreferences.Editor edit = sharedPreferences.edit();
                                   edit.putBoolean(Configs.ISLOADING, false);
                                   edit.putString(Configs.USETID, "");
                                   edit.commit();
                                   MyApplication.setUSERID("");
                                   MyApplication.setISLOADING(false);


                               }else{
                                   Toast.makeText(MoreSettingsActivity.this, "您当前没有登录", Toast.LENGTH_SHORT).show();
                               }
                                finish();
                            }
                        }).show();

                break;

        }
    }
}
