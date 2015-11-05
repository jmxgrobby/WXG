package com.chsj.smallhabit.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chsj.smallhabit.HabitsRecommendActivity;
import com.chsj.smallhabit.MyApplication;
import com.chsj.smallhabit.R;
import com.chsj.smallhabit.adapter.ParticipationAdapter;
import com.chsj.smallhabit.bean.PersonalHabit;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */
/**
 *签到碎片，主界面第一tab页
 */
public class ParticipationFragment extends Fragment implements VolleyCallBack ,View.OnClickListener{

    //本页面要请求的网址
    public static final String URL_PERSONAL_HABIT =
            "http://habit-api.appving.com/Service/Actives.svc/WeekCheckStatus" ;

    public static final int MESSAGE_RFRESH = 1 ;

    private View view;

    //适配器
    private ParticipationAdapter adapter ;

    //数据源
    private List<PersonalHabit> datas ;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_RFRESH :
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    } ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_partcaption, container, false);

//        碎片头部控件
        TextView edit_tv = (TextView) view.findViewById(R.id.partcaption_edit);
        TextView all_tv = (TextView) view.findViewById(R.id.partcaption_all);

        TextView add_habit_tv = (TextView) view.findViewById(R.id.partcaption_add_habit);
        add_habit_tv.setOnClickListener(this);
//      listview
        ListView listView =
                (ListView) view.findViewById(R.id.partcaption_list_view);
//      listview 的 headview
        View headView = inflater.inflate(R.layout.head_view_for__partcaption,null,false) ;
        ImageView before_tv = (ImageView) headView.findViewById(R.id.partcaption_history);
        ImageView next_tv = (ImageView) headView.findViewById(R.id.partcaption_next);

        TextView month_tv = (TextView)headView.findViewById(R.id.partcaption_month) ;
        TextView day_tv = (TextView)headView.findViewById(R.id.partcaption_day) ;
        TextView week_tv = (TextView)headView.findViewById(R.id.partcaption_week) ;

        datas = new ArrayList<>() ;
        adapter = new ParticipationAdapter(datas,getActivity()) ;
        listView.addHeaderView(headView);
        listView.setAdapter(adapter);
        listView.setItemsCanFocus(true);
        return view;
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

        MyApplication.doPost(URL_PERSONAL_HABIT, json, this);


    }

    @Override
    public void onVolleyResponse(final JSONObject jsonObject) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO: 2015/11/4 解析数据

                try {
                    JSONObject valueJson = jsonObject.getJSONObject("Value") ;
                    JSONArray list = valueJson.getJSONArray("Habits") ;
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject jsonObj  = list.getJSONObject(i);
                        PersonalHabit pb = PersonalHabit.parse(jsonObj) ;
                        datas.add(pb) ;
                    }

                    handler.sendEmptyMessage(MESSAGE_RFRESH) ;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(),"点击了碎片",Toast.LENGTH_SHORT).show();
        switch (v.getId()) {
            case R.id.partcaption_add_habit:
                Intent intent = new Intent(getActivity(),HabitsRecommendActivity.class) ;
                startActivity(intent);
                break;
        }
    }
}
