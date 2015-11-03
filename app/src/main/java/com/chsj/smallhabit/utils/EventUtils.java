package com.chsj.smallhabit.utils;

import android.view.View;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 这是一个设置点击事件的方法，可同时给多个控件设置点击事件
 */
public class EventUtils {
    public static void setEvent(View.OnClickListener clickListener,View... views){
        if(clickListener!=null&&views!=null){
            for (int i = 0; i < views.length; i++) {
                views[i].setOnClickListener(clickListener);
            }
        }
    }
}
