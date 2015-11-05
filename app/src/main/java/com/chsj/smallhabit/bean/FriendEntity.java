package com.chsj.smallhabit.bean;

/**
 * ProjectName: com.chsj.smallhabit.bean
 * Created by : ChSJ.Team
 * Email:  15001045515@163.com
 * user: 杨空明
 * on 2015-11-05.
 */


import org.json.JSONException;
import org.json.JSONObject;

/**
 * 加好友的实体类
 */
public class FriendEntity {
    /**
     "UserId": "1&A2D00413930B235D91777D6A54E105F8",
     "NickName": "Miss Y",
     "Gender": 1,
     "Photo": "http:\/\/qzapp.qlogo.cn\/qzapp\/101087467\/A2D00413930B235D91777D6A54E105F8\/100",
     "Relation": 0
     */
    private String  UserId;
    private String NickName;
    private int Gender;
    private String Photo;
    private int Relation;

    public FriendEntity() {
    }

    public FriendEntity(String userId, int relation, String photo, int gender, String nickName) {
        UserId = userId;
        Relation = relation;
        Photo = photo;
        Gender = gender;
        NickName = nickName;
    }
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public int getRelation() {
        return Relation;
    }

    public void setRelation(int relation) {
        Relation = relation;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }



}
