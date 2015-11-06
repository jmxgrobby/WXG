package com.chsj.smallhabit;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/10/22
 * Email: vhly@163.com
 */


import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用于描述 可以通过 JSON/XML , Cursor 来解析的数据
 */
public interface Parsable {

    /**
     * 实体类解析JSON，更新内部的数据
     * @param json
     * @throws JSONException
     */
    void parseJSON(JSONObject json) throws JSONException;

}
