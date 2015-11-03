package com.chsj.smallhabit.bean;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 这是点赞的人的图片的数据
 */
public class PraisePhotosEntity {

    /**
     * UserId : 9885cbc16c076d9aa9388acce1bab738&1__7_0
     * NickName : null
     * Photo : Picture.svc/GetReadImg/635818384103830058_2_0.jpg
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
