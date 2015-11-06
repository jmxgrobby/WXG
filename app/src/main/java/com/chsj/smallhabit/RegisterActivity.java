package com.chsj.smallhabit;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/4.
 */

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;
import com.chsj.smallhabit.utils.Configs;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 注册Activity
 */
public class RegisterActivity extends Activity implements View.OnClickListener, VolleyCallBack {
    //提交
    private TextView ok;
    //昵称
    private EditText nickName;
    //邮箱
    private EditText email;
    //密码
    private EditText passWord;

    private static  String DATAURL = "http://habit-api.appving.com/Service/MyAccount.svc/RegisterUser";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign_up);
        ok = (TextView) findViewById(R.id.main_sign_up_done);
        ok.setOnClickListener(this);

        nickName = (EditText) findViewById(R.id.main_sign_up_patname);
        email = (EditText) findViewById(R.id.main_sign_up_email);
        passWord = (EditText) findViewById(R.id.main_sign_up_pwd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_sign_up_done:
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("ApiKey", "7c32efe3adba158b5a675da5ca288bfe");
                    jsonObject.put("UserId", "");
                    jsonObject.put("DeviceNum","MI 2S_4.1.1_aries_Gude Version Name = 2.0.4_Gude Version Code = 20__channel = xiaomi");
                    jsonObject.put("NickName",nickName.getText().toString());
                    jsonObject.put("Gender","0");
                    jsonObject.put("Pwd",passWord.getText().toString());
                    jsonObject.put("Account",email.getText().toString());
                    jsonObject.put("_elapsed", "2");
                    jsonObject.put("Platform", 2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                MyApplication.doPost(DATAURL, jsonObject,this);
                break;
        }
    }

    @Override
    public void onVolleyResponse(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                int status = jsonObject.getInt("Status");
                if (status==1){
                    JSONObject value = jsonObject.getJSONObject("Value");
                    String string = value.getString("UserId");
                    SharedPreferences.Editor edit = getSharedPreferences(Configs.SHARDPERFACE_NAME, MODE_PRIVATE)
                            .edit();
                    edit.putBoolean(Configs.ISLOADING,true);
                    edit.putString(Configs.USETID, string).commit();

                    Toast.makeText(RegisterActivity.this, "注册账号成功", Toast.LENGTH_LONG).show();
                }else{
                    String exceptionMsg = jsonObject.getString("ExceptionMsg");
                    if (exceptionMsg != null) {
                        Toast.makeText(RegisterActivity.this, exceptionMsg, Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else
            Log.d("debug111","注册账号失败");
    }
}
