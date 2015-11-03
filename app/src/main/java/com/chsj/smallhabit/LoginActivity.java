package com.chsj.smallhabit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import com.chsj.smallhabit.utils.Configs;
/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/2.
 */
/**
 * 入口：即一个程序点击快捷方式进入的第一个界面
 */
public class LoginActivity extends Activity {
    private RelativeLayout bg;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        bg = (RelativeLayout) findViewById(R.id.activity_login_bg);

        //做一个渐变的动画，让其时常为线程睡眠的时常，这样子刚好可以做到动画播完，跳转下一个页面
        Animation animation = new AlphaAnimation(1f,0.5f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        bg.startAnimation(animation);

        new Thread(runnable).start();

    }

    /**
     * Acivity切换线程，两秒后从入口跳转到对应的Activity
     */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                //获取登陆的配置信息，此处获取是否为第一次登陆，默认为true
                // TODO 可以把用到的字符串封装到一个工具类中，避免之后多次调用写错参数
                sharedPreferences = getSharedPreferences(Configs.SHARDPERFACE_NAME, MODE_PRIVATE);
                boolean isFirst = sharedPreferences.getBoolean(Configs.ISFIRST, true);
                Intent intent;
                if(isFirst){//如果是第一次登陆，那么跳转viewpaer界面，并修改共享参数的数值
                    intent = new Intent(LoginActivity.this,FirstViewPager.class);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putBoolean(Configs.ISFIRST,false);
                    edit.commit();
                }else{//不是第一次登陆，直接跳转主页面
                    intent = new Intent(LoginActivity.this,MainActivity.class);
                }
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

}
