package com.chsj.smallhabit.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.Toast;
import com.chsj.smallhabit.DiscoverSquareActivity;
import com.chsj.smallhabit.R;
import com.chsj.smallhabit.utils.Configs;
import com.chsj.smallhabit.utils.EventUtils;
/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 发现碎片。主界面第二tab页
 */
public class DiscoverFragment extends Fragment implements View.OnClickListener {
    private View bg_view;

    private LinearLayout square_layout;
    private LinearLayout addFriend_layout;
    private LinearLayout msg_layout;

    private boolean isLoading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bg_view = inflater.inflate(R.layout.fragment_discover, container, false);
        //初始化三个线性布局，为其添加响应事件
        square_layout = (LinearLayout) bg_view.findViewById(R.id.fragment_discover_square);
        addFriend_layout = (LinearLayout) bg_view.findViewById(R.id.fragment_discover_add_friend);
        msg_layout = (LinearLayout) bg_view.findViewById(R.id.fragment_discover_msg);
        EventUtils.setEvent(this, msg_layout, square_layout, addFriend_layout);

        //从共享参数获取信息，是否已经登陆了
        isLoading = getContext().getSharedPreferences(Configs.SHARDPERFACE_NAME,
                Context.MODE_PRIVATE).getBoolean(Configs.ISLOADING,false);
        return bg_view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //广场
            case R.id.fragment_discover_square:
                Intent intent = new Intent(getContext(), DiscoverSquareActivity.class);
                startActivity(intent);
                break;
            //加好友
            case R.id.fragment_discover_add_friend:
                Toast.makeText(getContext(), "点击了加好友", Toast.LENGTH_SHORT).show();
                break;
            //消息
            case R.id.fragment_discover_msg:
                if(isLoading){
                    Toast.makeText(getContext(), "已经登陆", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "跳转登陆页面", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}
