package com.chsj.smallhabit;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;

import com.chsj.smallhabit.utils.Configs;
import org.json.JSONObject;

/**
 * Created by dongmeng on 2015/11/4.
 */
public class MyApplication extends Application {

    private static RequestQueue requestQueue ;
    public static final String UserId = "UserId" ;
    private static boolean ISLOADING ;
    private static String USERID;

    public static boolean ISLOADING() {
        return ISLOADING;
    }

    public static void setISLOADING(boolean ISLOADING) {
        MyApplication.ISLOADING = ISLOADING;
    }

    public static String getUSERID() {
        return USERID;
    }

    public static void setUSERID(String USERID) {
        MyApplication.USERID = USERID;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this) ;
        SharedPreferences sharedPreferences = getSharedPreferences(Configs.SHARDPERFACE_NAME, MODE_PRIVATE);
        ISLOADING = sharedPreferences
                .getBoolean(Configs.ISLOADING, false);
        if(ISLOADING){
            USERID = sharedPreferences.getString(Configs.USETID,"");
        }else{
            USERID ="";
        }
    }


    public static void doPost(String url , JSONObject jsonObject , final VolleyCallBack callBack){

        JsonObjectRequest jsObject = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        callBack.onVolleyResponse(jsonObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if (volleyError .networkResponse!= null) {
                            Log.d("TAG","volley请求出错："+volleyError.networkResponse.toString()) ;
                        }
                    }
                }
        ) ;

        requestQueue.add(jsObject) ;

    }


    public static String getUserKey(){

        return "b0a001b2056ffb8434b873f16e52af8b&1__8_0" ;
    }



}
