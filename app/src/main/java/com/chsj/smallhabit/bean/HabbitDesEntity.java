package com.chsj.smallhabit.bean;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/5.
 */

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 推荐文章
 */
public class HabbitDesEntity {

    private int key;
    private String value;
    private List<HabbitDessEntity> habbitDessEntities;

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public List<HabbitDessEntity> getHabbitDessEntities() {
        return habbitDessEntities;
    }

    public void setHabbitDessEntities(List<HabbitDessEntity> habbitDessEntities) {
        this.habbitDessEntities = habbitDessEntities;
    }

    public void parseJson(JSONObject jsonObject) {
        try {
            key = jsonObject.getInt("key");
            String values = jsonObject.getString("value");
            if (values.startsWith("[")){
                habbitDessEntities = new LinkedList<>();
                JSONArray jsonObject1 = new JSONArray(values);
                for (int i = 0; i < jsonObject1.length(); i++) {
                    JSONObject jsonObject2 = jsonObject1.getJSONObject(i);
                    HabbitDessEntity habbitDessEntity = new HabbitDessEntity();
                    habbitDessEntity.setUrl(jsonObject2.getString("Url"));
                    habbitDessEntity.setTitle(jsonObject2.getString("Title"));
                    habbitDessEntities.add(habbitDessEntity);
                }
            }else{
                value = values;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
