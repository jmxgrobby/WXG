package com.chsj.smallhabit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chsj.smallhabit.bean.HabbitDesEntity;
import com.chsj.smallhabit.bean.HabbitDessEntity;
import com.chsj.smallhabit.bean.HabbitInfoEntity;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;
import com.chsj.smallhabit.utils.GetRightUrlUtils;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;

import java.util.List;


/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/5.
 */

/**
 * 习惯详情
 */
public class HabbitInfoActivity extends Activity implements VolleyCallBack {

    private static final String DATAURL = "http://habit-api.appving.com/Service/Home.svc/GetHabitCircumstance";

    private WebView webView;
    //返回
    private ImageView bg_back;

    //标题
    private TextView title;

    //大图片
    private ImageView bigImage;

    //习惯名 其实跟标题一样
    private TextView habbitname;

    //参与人数
    private TextView joinNum;

    //相关文章
    private LinearLayout auticeLayout;


    //加入按钮
    private Button add;

    //习惯实体
    private HabbitInfoEntity habbitInfoEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_habbit_info);
        webView = (WebView) findViewById(R.id.activity_habbit_webview);
        bg_back = (ImageView) findViewById(R.id.habbit_info_back);
        title = (TextView) findViewById(R.id.habbit_info_title);
        bigImage = (ImageView) findViewById(R.id.habbit_info_image);
        habbitname = (TextView) findViewById(R.id.habbit_info_name);
        joinNum = (TextView) findViewById(R.id.habbit_info_num);
        add = (Button) findViewById(R.id.habbit_info_add);
        auticeLayout = (LinearLayout) findViewById(R.id.habbit_info_Artice);

        loadData();
    }

    //加载数据
    private void loadData() {
        String detailId = getIntent().getStringExtra("DetailId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ApiKey", "7c32efe3adba158b5a675da5ca288bfe");
            jsonObject.put("UserId", "");
            jsonObject.put("_elapsed", "0");
            jsonObject.put("CheckTime", "1446722573281");
            jsonObject.put("DetailId", detailId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MyApplication.doPost(DATAURL, jsonObject, this);
    }


    @Override
    public void onVolleyResponse(JSONObject jsonObject) {
        if (jsonObject != null) {
            habbitInfoEntity = new HabbitInfoEntity();
            habbitInfoEntity.parseJson(jsonObject);
            //姓名
            String name = habbitInfoEntity.getName();
            title.setText(name);
            habbitname.setText(name);
            //加入次数
            int joinCount = habbitInfoEntity.getJoinCount();
            joinNum.setText(joinCount + "人参与");
            //图片
            String picUrls = habbitInfoEntity.getPicUrls();
            String url = GetRightUrlUtils.getUrl(picUrls);
            if (url != null) {
                Picasso.with(this).load(url).into(bigImage);
            }
            //相关文章 以及介绍
            List<HabbitDesEntity> habbitDesEntities = habbitInfoEntity.getHabbitDesEntities();
            if (habbitDesEntities != null && habbitDesEntities.size() > 0) {
                for (int i = 0; i < habbitDesEntities.size(); i++) {
                    HabbitDesEntity habbitDesEntity = habbitDesEntities.get(i);
                    int key = habbitDesEntity.getKey();
                    Log.d("debug111", "key = " + key);
                    if (habbitDesEntity != null && key == 1) {
                        webView.loadData(habbitDesEntity.getValue(), "text/html;charset=UTF-8", "");
                    } else if (habbitDesEntity != null && key == 4) {//相关文章
                        auticeLayout.setVisibility(View.VISIBLE);
                        List<HabbitDessEntity> entities = habbitDesEntity.getHabbitDessEntities();
                        for (int j = 0; j < entities.size(); j++) {
                            LinearLayout inflate = (LinearLayout) LinearLayout.inflate(
                                    HabbitInfoActivity.this, R.layout.habbit_info_like_book, null);
                            TextView bookName = (TextView) inflate.findViewById(R.id.habbit_info_like_bookname);
                            bookName.setText(entities.get(j).getTitle());
                            auticeLayout.addView(inflate);
                        }
                    }
                }
            } else {
                Log.d("debug111", "请求的list数据为空");
            }

        }
    }
}
