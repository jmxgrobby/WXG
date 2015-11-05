package com.chsj.smallhabit.fragment;


import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chsj.smallhabit.R;
import com.chsj.smallhabit.utils.Configs;
import com.chsj.smallhabit.utils.EventUtils;
import com.chsj.smallhabit.utils.IsNetworkAvailable;
import com.chsj.smallhabit.utils.MoreSettingsActivity;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * ProjectName: com.chsj.smallhabit.utils
 * Created by : ChSJ.Team
 * Email:  15001045515@163.com
 * user: 杨空明
 * on 2015-11-03.
 */

/**
 * 更多碎片界面，主界面第四tab页
 */
public class MoreFragment extends Fragment implements  View.OnClickListener{


    public MoreFragment() {
        // Required empty public constructor
    }


    private TextView txt_setup;
    private RadioButton btnShare,btnWechat,btnWeibo;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_more, container, false);
        txt_setup= (TextView) view.findViewById(R.id.txt_setup);
        btnShare= (RadioButton) view.findViewById(R.id.fragement_more_share);
        btnWechat= (RadioButton) view.findViewById(R.id.fragment_more_wechat);
        btnWeibo= (RadioButton) view.findViewById(R.id.fragement_more_weibo);
        //为更多页面添加事件
        EventUtils.setEvent(this, txt_setup, btnShare, btnWechat, btnWeibo);
        return view;
    }


    @Override
    public void onClick(View v) {
        int code=v.getId();
        switch(code){
            case R.id.txt_setup:
                //设置
                Intent intent=new Intent(getActivity(), MoreSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.fragement_more_share:
                //分享
                showShare();
                break;
            case R.id.fragment_more_wechat:
                AlertDialog.Builder dialog=new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom));
                dialog.setTitle("复制微信号：wehabits成功！跳转到微信关注我们吧！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            //点击确定登陆到微信界面
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sp=getContext()
                                        .getSharedPreferences(Configs.SHARDPERFACE_NAME, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sp.edit();
                                editor.putInt(Configs.WECHATBACK,3);
                                editor.commit();
                                Intent intent=new Intent();
                                ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
                                intent.setAction(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setComponent(cmp);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消",null)
                        .create().show();

                //微信
                break;
            case R.id.fragement_more_weibo:
                //微博
                boolean isNetWork= IsNetworkAvailable.isAvailable(getActivity());
                Log.d("debug", "" + isNetWork);
                Toast.makeText(getActivity(), "" + isNetWork, Toast.LENGTH_LONG).show();
                break;
        }
    }


    private void showShare() {
        ShareSDK.initSDK(getActivity());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivity());
    }
}
