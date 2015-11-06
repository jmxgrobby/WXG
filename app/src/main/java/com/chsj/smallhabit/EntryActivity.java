package com.chsj.smallhabit;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/4.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;
import com.chsj.smallhabit.utils.Configs;
import com.chsj.smallhabit.utils.EventUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 登陆界面
 */
public class EntryActivity extends Activity implements View.OnClickListener, VolleyCallBack {
    //邮箱用户名
    private EditText email;
    //密码
    private EditText passWord;
    //登录
    private Button login;
    //注册
    private Button regiest;
    //返回
    private ImageView back;

    private static final String DATAURL ="http://habit-api.appving.com/Service/MyAccount.svc/Login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_in);
        email = (EditText) findViewById(R.id.main_sign_in_email);
        passWord = (EditText) findViewById(R.id.main_sign_in_pwd);
        login = (Button) findViewById(R.id.main_sign_in_signin);
        regiest = (Button) findViewById(R.id.main_sign_in_signup);
        back = (ImageView) findViewById(R.id.main_sign_in_back);

        EventUtils.setEvent(this,login,regiest,back);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录
            case R.id.main_sign_in_signin:
                JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("ApiKey", "7c32efe3adba158b5a675da5ca288bfe");
                    jsonObject.put("UserId", "");
                    jsonObject.put("DeviceNum","MI 2S_4.1.1_aries_Gude Version Name = 2.0.4_Gude Version Code = 20__channel = xiaomi");
                    jsonObject.put("Pwd",passWord.getText().toString());
                    jsonObject.put("Account",email.getText().toString());
                    jsonObject.put("_elapsed", "1");
                    jsonObject.put("Platform", 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                MyApplication.doPost(DATAURL,jsonObject,this);
                break;
            //注册
            case R.id.main_sign_in_signup:
                Intent intent  = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
            //返回
            case R.id.main_sign_in_back:
                finish();
                break;
        }
    }

    @Override
    public void onVolleyResponse(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                int status = jsonObject.getInt("Status");
                if (status==1){//登录成功
                    JSONObject value = jsonObject.getJSONObject("Value");
                    String string = value.getString("UserId");
                    SharedPreferences.Editor edit = getSharedPreferences(Configs.SHARDPERFACE_NAME, MODE_PRIVATE)
                            .edit();
                    edit.putBoolean(Configs.ISLOADING, true);
                    edit.putString(Configs.USETID, string).commit();
                    MyApplication.setISLOADING(true);
                    MyApplication.setUSERID(string);
                    Toast.makeText(EntryActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    String exceptionMsg = jsonObject.getString("ExceptionMsg");
                    if (exceptionMsg != null) {
                        Toast.makeText(EntryActivity.this, exceptionMsg, Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
