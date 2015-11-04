package com.chsj.smallhabit.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * ProjectName: com.chsj.smallhabit.utils
 * Created by : ChSJ.Team
 * Email:  15001045515@163.com
 * user: 杨空明
 * on 2015-11-03.
 */

/**
 * 用来判断当前网络是否可用
 */
public final class IsNetworkAvailable {
    private Context context;

    public IsNetworkAvailable(Context context) {
        this.context = context;
    }

    public static boolean isAvailable(Context context){

        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
         if(manager==null){
             return false;
         }else{
             NetworkInfo []networkInfos=manager.getAllNetworkInfo();
             if(networkInfos!=null&&networkInfos.length>0){
                 for (int i = 0; i <networkInfos.length ; i++) {
                     if(networkInfos[i].getState()==NetworkInfo.State.CONNECTED){
                         return true;
                     }
                 }
             }
         }
        return false;
    }
}
