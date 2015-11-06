package com.chsj.smallhabit.utils;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 这是一个工具类，包含共享参数的信息：如共享参数名，存储字段的key值等
 */
public class Configs {
    //共享参数名
    public static final String SHARDPERFACE_NAME = "sharedPreferences";
    //判断是否是第一次登陆存储的信息
    public static final String ISFIRST = "isFirst?";
    //判断是否已经登录的信息
    public static final String ISLOADING = "isLoading?";
    //动态页面请求图片的网址头部（如网址以Picture开头需添加）
    public static final String IMAGEHEAD = "http://habit-file.appving.com/";
   //判断点击官方微信是否按返回键
    public static final String WECHATBACK="isweChatBack";
    // 广场请求数据的是热门
    public static final String HOTTRENDS = "2";
    // 广场请求数据的是好友
    public static final String FRIENDTRENDS = "1";
    // 广场请求数据的是最新
    public static final String NEWTRENDS = "0";

    public static final String USETID = "user_id";
}
