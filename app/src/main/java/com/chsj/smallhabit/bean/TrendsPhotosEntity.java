package com.chsj.smallhabit.bean;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

import java.io.Serializable;

/**
 * 这是动态的显示的大图片的网址
 */
public class TrendsPhotosEntity implements Serializable{

    /**
     * Original : Picture.svc/GetReadImg/635820988399647084_1_2.jpg
     * Thumbnail : Picture.svc/GetReadImg/th_635820988399647084_1_2.jpg
     */

    private String Original;
    private String Thumbnail;

    public void setOriginal(String Original) {
        this.Original = Original;
    }

    public void setThumbnail(String Thumbnail) {
        this.Thumbnail = Thumbnail;
    }

    public String getOriginal() {
        return Original;
    }

    public String getThumbnail() {
        return Thumbnail;
    }
}
