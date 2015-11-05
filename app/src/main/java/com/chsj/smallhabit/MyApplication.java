package com.chsj.smallhabit;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;

import org.json.JSONObject;

/**
 * Created by dongmeng on 2015/11/4.
 */
public class MyApplication extends Application {

    private static RequestQueue requestQueue ;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this) ;
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


}
