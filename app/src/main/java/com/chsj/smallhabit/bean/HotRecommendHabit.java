package com.chsj.smallhabit.bean;

import com.chsj.smallhabit.Parsable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongmeng on 2015/11/5.
 */
public class HotRecommendHabit implements Parsable{


    /**
     * Id : 04439d78-d473-48e5-86df-0176db59094c24
     * Name : 最受欢迎榜单
     * PicUrl : Picture.svc/GetReadImg/635785425649160826_2_3.png
     * TopSubdivision : ["喝水","睡前刷牙","每天11点之前睡觉"]
     */

    private String Id;
    private String Name;
    private String PicUrl;
    private List<String> TopSubdivision;

    @Override
    public void parseJSON(JSONObject json) throws JSONException {
        Id = json.getString("Id") ;
        Name = json.getString("Name") ;
        PicUrl = json.getString("PicUrl") ;

        TopSubdivision = new ArrayList<>() ;
        JSONArray arr = json.optJSONArray("TopSubdivision");
        if (arr != null) {
            int len = arr.length() ;
            for (int i = 0; i < len; i++) {
                String s = arr.getString(i) ;
                TopSubdivision.add(s) ;
            }
        }

    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setPicUrl(String PicUrl) {
        this.PicUrl = PicUrl;
    }

    public void setTopSubdivision(List<String> TopSubdivision) {
        this.TopSubdivision = TopSubdivision;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public List<String> getTopSubdivision() {
        return TopSubdivision;
    }
}
