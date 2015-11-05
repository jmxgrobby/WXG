package com.chsj.smallhabit;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chsj.smallhabit.adapter.DimensionUtil;
import com.chsj.smallhabit.adapter.PicPagerAdapter;
import com.chsj.smallhabit.adapter.RecommendAdapter;
import com.chsj.smallhabit.bean.HotRecommendHabit;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HabitsRecommendActivity extends FragmentActivity implements VolleyCallBack {

    private final String URL_RECOMMEND_ALBUM =
            "http://habit-api.appving.com/Service/Home.svc/RecommendAlbum";

    private static final String URL_RECOMNEND_CATEGORY =
            "http://habit-api.appving.com/Service/Home.svc/TopCategory";

    @Bind(R.id.recommend_back_img_view)
    ImageView recommendBackImgView;
    @Bind(R.id.recommend_auto_complete_text_view)
    AutoCompleteTextView recommendAutoCompleteTextView;
    @Bind(R.id.recommend_create_text_view)
    TextView recommendCreateTextView;
    @Bind(R.id.recommend_view_pager)
    ViewPager recommendViewPager;
    @Bind(R.id.recommend_list_view)
    FullListView recommendListView;
    @Bind(R.id.recommend_scroll_view)
    ScrollView recommendScrollView;

    //数据源
    private List<HotRecommendHabit> datas ;
    private List<HotRecommendHabit> pics ;

    //适配器
    private PicPagerAdapter picAdapter ;
    private RecommendAdapter adapter ;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) { //代表是广告的数据返回
                // TODO: 2015/11/5 适配器更新
                picAdapter.notifyDataSetChanged();
            } else if (msg.what == 2) {  //代表是列表的数据返回
                // TODO: 2015/11/5 适配器更新
                adapter.notifyDataSetChanged();
            }
            // TODO: 2015/11/5 取消等待动画
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_habits_recommend);
        ButterKnife.bind(this);

        ViewGroup.LayoutParams lp =
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        DimensionUtil.getSuitablePx(660, 310, getWindowManager())
                        // 660  * 310
                );
        ViewGroup.LayoutParams layoutParams = recommendScrollView.getLayoutParams();


        //初始化listView 和ViewPager
        datas = new ArrayList<>() ;
        pics = new ArrayList<>() ;
        // TODO: 2015/11/5 c初始化适配器

        picAdapter = new PicPagerAdapter(pics,getWindowManager()) ;
        recommendViewPager.setAdapter(picAdapter);

        adapter = new RecommendAdapter(datas,this) ;
        recommendListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 2015/11/5 开启等待动画
        JSONObject json = new JSONObject();
        try {
            json.put("ApiKey", "7c32efe3adba158b5a675da5ca288bfe");

            json.put(MyApplication.UserId, MyApplication.getUserKey());

            json.put("_elapsed", 0);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyApplication.doPost(URL_RECOMMEND_ALBUM, json, this);
        MyApplication.doPost(URL_RECOMNEND_CATEGORY, json, this);

    }

    @Override
    public void onVolleyResponse(final JSONObject jsonObject) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<HotRecommendHabit> list = null ;
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("Value");

                    if (jsonArray != null) {
                        list = new ArrayList<>() ;
                        int length = jsonArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jsObj = jsonArray.getJSONObject(i);
                            HotRecommendHabit habit = new HotRecommendHabit();
                            habit.parseJSON(jsObj);
                            // TODO: 2015/11/5 把habit 加入数据源
                            list.add(habit) ;
                        }
                        //指定是哪个Volley请求返回的数据
                        if (length == 3) {
                            pics.addAll(list) ;
                            handler.sendEmptyMessage(1);
                        } else {
                            datas.addAll(list) ;
                            handler.sendEmptyMessage(2);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
