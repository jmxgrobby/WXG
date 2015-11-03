package com.chsj.smallhabit.fragment.squalefragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chsj.smallhabit.R;
import com.chsj.smallhabit.bean.TrendsEntity;
import com.chsj.smallhabit.interfaces.AfterGetTrends;
import com.chsj.smallhabit.task.TrendsTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 这是广场跳转的Activity里面热门帖子的碎片
 */
public class HotSquareFragment extends BaseFragment implements AfterGetTrends {

    private View bg_layout;
    private PullToRefreshListView pullToRefreshListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bg_layout = inflater.inflate(R.layout.fragment_hot_square, container, false);

        //pullToRefreshListView 的初始化及设置
        pullToRefreshListView = (PullToRefreshListView) bg_layout.findViewById(R.id.fragment_hot_square_pulltorefreshview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        new TrendsTask(this).execute();
        return bg_layout;
    }

    @Override
    public String getFragmentTitle() {
        return "热门";
    }

    @Override
    public void sendTrends(List<TrendsEntity> list) {
        Log.d("debug111","回调");
    }
}
