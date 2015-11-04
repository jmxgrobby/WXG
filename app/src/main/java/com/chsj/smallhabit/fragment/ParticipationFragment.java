package com.chsj.smallhabit.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chsj.smallhabit.MyApplication;
import com.chsj.smallhabit.R;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */
/**
 *签到碎片，主界面第一tab页
 */
public class ParticipationFragment extends Fragment implements VolleyCallBack{

    //本页面要请求的网址
    public static final String URL_PERSONAL_HABIT =
            "http://habit-api.appving.com/Service/Actives.svc/WeekCheckStatus" ;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_partcaption,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        JSONObject json = new JSONObject() ;
        try {
            json.put("ApiKey","7c32efe3adba158b5a675da5ca288bfe") ;

            json.put("UserId", "b0a001b2056ffb8434b873f16e52af8b&1__8_0") ;

            json.put("_elapsed", 0) ;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyApplication.doPost(URL_PERSONAL_HABIT, json , this);

    }

    @Override
    public void onVolleyResponse(final JSONObject jsonObject) {
        // TODO: 2015/11/4 解析数据
        Log.d("TAG",jsonObject.toString()) ;
    }
}
