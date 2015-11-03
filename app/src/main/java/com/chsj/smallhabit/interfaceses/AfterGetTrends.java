package com.chsj.smallhabit.interfaceses;

import com.chsj.smallhabit.bean.TrendsEntity;

import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 这是一个回调接口，当数据请求完毕的时候，调用这个接口通知主界面更新UI
 */
public interface AfterGetTrends {
    void sendTrends(List<TrendsEntity> list);
}
