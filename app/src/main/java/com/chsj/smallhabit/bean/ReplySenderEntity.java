package com.chsj.smallhabit.bean;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

import java.io.Serializable;

/**
 * 评论者信息
 */
public class ReplySenderEntity implements Serializable{

    /**
     * UserId : 2&3925043885
     * NickName : Susan
     * Photo : Picture.svc/GetReadImg/635809760947568609_1_0.jpg
     */

    private String UserId;
    private String NickName;
    private String Photo;

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getUserId() {
        return UserId;
    }

    public String getNickName() {
        return NickName;
    }

    public String getPhoto() {
        return Photo;
    }
}
