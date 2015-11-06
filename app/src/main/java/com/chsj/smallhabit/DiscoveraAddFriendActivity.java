package com.chsj.smallhabit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.chsj.smallhabit.adapter.AddFriendAdapter;
import com.chsj.smallhabit.bean.FriendEntity;
import com.chsj.smallhabit.interfaceses.AddFriendCallBack;
import com.chsj.smallhabit.interfaceses.VolleyCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiscoveraAddFriendActivity extends Activity
        implements VolleyCallBack, View.OnClickListener,
        AdapterView.OnItemClickListener,
        AddFriendCallBack{

    public static final String  ADDFRIEND="http://habit-api.appving.com/Service/Zone.svc/StarUsers";
    private List<FriendEntity> friendEntityList;
    private FriendEntity friendEntity;
    private AddFriendAdapter adapter;
    private ListView listItem;
    //UserId的值
    private String UserId="";
    private TextView chaged;
    private ImageView imgBack;
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovera_add_friend);
        listItem= (ListView) findViewById(R.id.discover_add_friend_item);
        chaged= (TextView) findViewById(R.id.add_friend_changed);
        imgBack= (ImageView) findViewById(R.id.add_friend_back);
        friendEntityList=new ArrayList<>();
        friendEntity=new FriendEntity();
        chaged.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        listItem.setOnItemClickListener(this);
        FriendChanged(UserId);
    }

    private void FriendChanged(String UserId) {
        JSONObject obj=new JSONObject();
        try {
            obj.put("ApiKey", "7c32efe3adba158b5a675da5ca288bfe");
            obj.put("UserId", "");
            obj.put("_elapsed", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyApplication.doPost(ADDFRIEND, obj, this);

    }


    @Override
    public void onVolleyResponse(JSONObject jsonObject) {
        if(jsonObject!=null){
            try {

                String status=jsonObject.getString("Status");
                JSONArray array=jsonObject.getJSONArray("Value");
                for (int i = 0; i <array.length(); i++) {
                    friendEntity=new FriendEntity();
                    JSONObject objitem=array.getJSONObject(i);
                    friendEntity.setUserId(objitem.getString("UserId"));
                    friendEntity.setNickName(objitem.getString("NickName"));
                    friendEntity.setGender(objitem.optInt("Gender", 0));
                    friendEntity.setPhoto(objitem.getString("Photo"));
                    friendEntity.setRelation(objitem.optInt("Relation", 0));
                    friendEntityList.add(friendEntity);
                }
                adapter=new AddFriendAdapter(this,getApplicationContext(),friendEntityList);
                listItem.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View v) {
        if(v!=null) {
            int code=v.getId();
            switch(code){
                //换一批
                case R.id.add_friend_changed:
                    for (int i = 0; i < friendEntityList.size(); i++) {
                        friendEntity = friendEntityList.get(i);
                        FriendChanged(friendEntity.getUserId());
                        friendEntityList=new ArrayList<>();
                        adapter = new AddFriendAdapter(this, getApplicationContext(),friendEntityList);
                        listItem.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    break;
                //返回
                        case R.id.add_friend_back:
                            finish();
                            break;

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      //// TODO: 2015-11-05 点击item事件处理
        Toast.makeText(getApplicationContext(),"item"+position,Toast.LENGTH_LONG).show();
    }

    @Override
    public void click(View v) {
       if(v!=null){
           if(!MyApplication.ISLOADING()){
               Intent intent=new Intent(getApplicationContext(),EntryActivity.class);
               startActivity(intent);
           }else {

           }
       }
    }
}
