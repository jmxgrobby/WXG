package com.chsj.smallhabit.parser;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/5.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 习惯详情数据解析
 */
public class ParseHabbitInfo {
    public static String getHabbitInfo(JSONObject jsonObject) {
        String ret = null;
        try {
            JSONObject value = jsonObject.getJSONObject("Value");
            JSONArray des = value.optJSONArray("Des");
            if (des != null) {

                JSONObject object = des.getJSONObject(0);
                ret = object.optString("value");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
