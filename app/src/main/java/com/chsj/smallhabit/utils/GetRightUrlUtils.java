package com.chsj.smallhabit.utils;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/4.
 */
/**
 * 获取正确的网址
 *
 *
 *
 */

public class GetRightUrlUtils {

    public  static String getUrl(String genPhoto) {
        String url;
        if (genPhoto.trim().length() > 0 && genPhoto != null) {
            if (genPhoto.startsWith("Picture")) {
                url = Configs.IMAGEHEAD + genPhoto;
            } else {
                url = genPhoto;
            }
        } else
            url = null;
        return url;
    }
}
