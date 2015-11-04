package com.chsj.smallhabit;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/4.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * 登陆界面
 */
public class EntryActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_in);
    }
}
