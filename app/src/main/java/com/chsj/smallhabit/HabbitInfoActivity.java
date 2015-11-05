package com.chsj.smallhabit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;
import com.chsj.smallhabit.parser.ParseHabbitInfo;
import org.json.JSONObject;

/**
 * 习惯详情
 */
public class HabbitInfoActivity extends Activity implements VolleyCallBack {

    private static final String DATAURL = "http://habit-api.appving.com/Service/Home.svc/GetHabitCircumstance";

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_habbit_info);
        webView = (WebView) findViewById(R.id.activity_habbit_webview);
        String detailId = getIntent().getStringExtra("DetailId");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ApiKey", "7c32efe3adba158b5a675da5ca288bfe");
            jsonObject.put("UserId", "");
            jsonObject.put("_elapsed", "0");
            jsonObject.put("CheckTime","1446722573281");
            jsonObject.put("DetailId",detailId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MyApplication.doPost(DATAURL, jsonObject,this);
    }


    @Override
    public void onVolleyResponse(JSONObject jsonObject) {
        String habbitInfo = ParseHabbitInfo.getHabbitInfo(jsonObject);
        if (habbitInfo != null) {
            Log.d("debug111",habbitInfo);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setDefaultTextEncodingName("UTF-8");
            webView.loadData(habbitInfo, "text/html;charset=UTF-8", "");
        }else{
            Log.d("debug111","空返回");
        }
    }
}
