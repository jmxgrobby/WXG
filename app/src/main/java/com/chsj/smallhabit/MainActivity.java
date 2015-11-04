package com.chsj.smallhabit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.Toast;
import com.chsj.smallhabit.fragment.DiscoverFragment;
import com.chsj.smallhabit.fragment.MoreFragment;
import com.chsj.smallhabit.fragment.ParticipationFragment;
import com.chsj.smallhabit.fragment.PersonalFragment;
import com.chsj.smallhabit.utils.Configs;
import com.chsj.smallhabit.utils.EventUtils;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/2.
 */
/**
 * 主界面，即fragment的选择界面，默认选择第一个fragment，即签到
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private Fragment fragments[];
    private FragmentManager manager;
    private FragmentTransaction transaction;

    private long lastClick;

    // 签到按钮
    private ImageView qd;
    // 发现按钮
    private ImageView discover;
    // 我的按钮
    private ImageView personal;
    // 更多按钮
    private ImageView more;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        qd = (ImageView) findViewById(R.id.activity_main_tab_qd);
        discover = (ImageView) findViewById(R.id.activity_main_tab_discover);
        personal = (ImageView) findViewById(R.id.activity_main_tab_personal);
        more = (ImageView) findViewById(R.id.activity_main_tab_more);

        EventUtils.setEvent(this, qd, discover, personal, more);

        // 碎片的初始化
        manager = getSupportFragmentManager();
        fragments = new Fragment[4];
        transaction = manager.beginTransaction();
        if(savedInstanceState==null){
            fragments[0] = new ParticipationFragment();
            fragments[1] = new DiscoverFragment();
            fragments[2] = new PersonalFragment();
            fragments[3] = new MoreFragment();
            for (int i = 0; i <fragments.length; i++) {
               transaction.add(R.id.activity_main_fragment,fragments[i],"fragment"+i);
                transaction.hide(fragments[i]);
            }
            //默认显示第一tab页，
            SharedPreferences sp=getSharedPreferences(Configs.SHARDPERFACE_NAME,MODE_PRIVATE);
            boolean flags=sp.getBoolean(Configs.WECHATBACK,true);
            if (flags==true){
                transaction.show(fragments[2]);
            }else{
                transaction.show(fragments[0]);
            }
            transaction.commit();
            Log.d("debug111", "saveInstanceState空");
        }else{
            Log.d("debug111","saveInstanceState不为空");
            for (int i = 0; i < fragments.length; i++) {
                fragments[i] = manager.findFragmentByTag("fragment"+i);
                manager.beginTransaction().hide(fragments[i]).commit();
            }
            manager.beginTransaction().show(fragments[0]).commit();
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentClick = System.currentTimeMillis();
            if (currentClick - lastClick < 3000) {
                finish();
            } else {
                lastClick = currentClick;
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    //碎片选择事件
    @Override
    public void onClick(View v) {
        hideAll();//先使所有背景颜色变灰
        int index =0;
        transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.activity_main_tab_qd:
                qd.setImageResource(R.mipmap.bottombar_participation_press);
                index = 0;
                break;
            case R.id.activity_main_tab_discover:
                discover.setImageResource(R.mipmap.bottombar_discover_press);
                index =1;
                break;
            case R.id.activity_main_tab_personal:
                personal.setImageResource(R.mipmap.bottombar_personal_press);
                index = 2;
                break;
            case R.id.activity_main_tab_more:
                more.setImageResource(R.mipmap.bottombar_more_press);
                index =3;
                break;
        }
        for (int i = 0; i < fragments.length; i++) {
            if(i==index){
                transaction.show(fragments[i]);
            }else{
                transaction.hide(fragments[i]);
            }
        }
        transaction.commit();
    }

    //图片更换，先使所有的设为灰色
    private void hideAll() {
        qd.setImageResource(R.mipmap.bottombar_participation_none);
        discover.setImageResource(R.mipmap.bottombar_discover_none);
        personal.setImageResource(R.mipmap.bottombar_personal_none);
        more.setImageResource(R.mipmap.bottombar_more_none);
    }
}
