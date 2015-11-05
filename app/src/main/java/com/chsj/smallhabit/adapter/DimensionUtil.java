package com.chsj.smallhabit.adapter;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/10/22
 * Email: vhly@163.com
 */

import android.content.Context;
import android.content.res.Resources;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * 用于进行尺寸计算的
 */
public final class DimensionUtil {

    private DimensionUtil(){

    }

    /**
     * 根据当前手机的屏幕密度，进行 dp 到 px 单位的换算
     * @param context 获取屏幕信息的
     * @param dp 待转换的内容
     * @return px
     */
    public static int dp2px(Context context, int dp){
        int ret = 0;
        Resources res = context.getResources();
        // 获取屏幕的测量信息
        DisplayMetrics metrics = res.getDisplayMetrics();


        // px = dp * (dpi / 160)
        ret = (int)(dp * metrics.density);

        return ret;
    }

    //以宽度为基准的机型适配方法，返回值为高度
    public static int getSuitablePx(int w , int h, WindowManager manager){
        DisplayMetrics displayMetrics = new DisplayMetrics() ;
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels ;
        int height = displayMetrics.heightPixels ;
        Log.d("TAG","width:"+width+"height:"+height) ;
        return (int)(width*1.0/w*h) ;
    }

}
