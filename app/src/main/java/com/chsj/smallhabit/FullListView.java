package com.chsj.smallhabit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by dongmeng on 2015/10/23.
 */

/**
 * 自动扩充尺寸的Listview ,适用于ScrollView + ListView
 */
public class FullListView extends ListView {
    public FullListView(Context context) {
        super(context);
    }

    public FullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(10000,//控件可以显示的最大高度
                MeasureSpec.AT_MOST) ;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
