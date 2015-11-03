package com.chsj.smallhabit.fragment.squalefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chsj.smallhabit.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 这是广场跳转的Activity里面热门帖子的碎片
 */
public class HotSquareFragment extends BaseFragment{

    private View bg_layout;
    private PullToRefreshListView pullToRefreshListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bg_layout = inflater.inflate(R.layout.fragment_hot_square, container, false);

        //pullToRefreshListView 的初始化

        return bg_layout;
    }

    @Override
    public String getFragmentTitle() {
        return "热门";
    }
}
