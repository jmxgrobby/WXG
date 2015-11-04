package com.chsj.smallhabit.fragment.squalefragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.chsj.smallhabit.R;
import com.chsj.smallhabit.adapter.TrendsAdapter;
import com.chsj.smallhabit.bean.TrendsEntity;
import com.chsj.smallhabit.interfaceses.AfterGetTrends;
import com.chsj.smallhabit.task.TrendsTask;
import com.chsj.smallhabit.utils.Configs;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 这是广场Activity 的最新动态的界面
 */
public class NewSquareFragment extends BaseFragment implements AfterGetTrends, View.OnClickListener ,PullToRefreshBase.OnRefreshListener{
    private View bg_layout;
    private PullToRefreshListView pullToRefreshListView;


    private String lastId;
    public NewSquareFragment(){}

    private List<TrendsEntity> list;
    private TrendsAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bg_layout = inflater.inflate(R.layout.fragment_square_item, container, false);

        //pullToRefreshListView 的初始化及设置
        pullToRefreshListView = (PullToRefreshListView) bg_layout.findViewById(R.id.fragment_hot_square_pulltorefreshview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        list = new LinkedList<>();
        adapter = new TrendsAdapter(list,getContext(),this);

        pullToRefreshListView.setAdapter(adapter);
        pullToRefreshListView.setOnRefreshListener(this);
        new TrendsTask(this).execute(Configs.NEWTRENDS,lastId);
        return bg_layout;
    }

    @Override
    public String getFragmentTitle() {
        return "最新";
    }

    @Override
    public void sendTrends(List<TrendsEntity> list) {
        if (list != null) {
            this.list.addAll(list);
            adapter.notifyDataSetChanged();
            pullToRefreshListView.onRefreshComplete();
        }
    }

    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag != null && tag instanceof String) {
            String s = (String) tag;
            String[] split = s.split(":");
            // TODO 跳转用户界面Activity
            if (split[0].equals("GenPhoto")) {
                Toast.makeText(getContext(),
                        "点击了" + list.get(Integer.parseInt(split[1])).getGenNickName(),
                        Toast.LENGTH_SHORT).show();
            } else if(split[0].equals("HabitName")) { // TODO 跳转习惯介绍界面Activity
                Toast.makeText(getContext(),
                        "点击了"+ list.get(Integer.parseInt(split[1])).getHabitName(),
                        Toast.LENGTH_SHORT).show();
            }else if(split[0].equals("PraiseImages")){ // TODO 跳转赞我的人界面Activity
                Toast.makeText(getContext(),
                        "点击了"+ list.get(Integer.parseInt(split[1])).getGenNickName()+"点赞人图标",
                        Toast.LENGTH_SHORT).show();
            }else if(split[0].equals("CommentCount")){ // TODO 跳转评论界面Activity或者登录界面
                Toast.makeText(getContext(),
                        "点击了"+ list.get(Integer.parseInt(split[1])).getGenNickName()+"评论次数",
                        Toast.LENGTH_SHORT).show();
            }else if(split[0].equals("PraiseCount")){ // TODO 跳转赞界面Activity或者登录界面
                Toast.makeText(getContext(),
                        "点击了"+ list.get(Integer.parseInt(split[1])).getGenNickName()+"赞次数",
                        Toast.LENGTH_SHORT).show();
            }else if(split[0].equals("CommentListEntity")){ // TODO 跳转评论详情界面Activity或者登录界面
                Toast.makeText(getContext(),
                        "点击了"+ list.get(Integer.parseInt(split[1])).getGenNickName()+"评论详情",
                        Toast.LENGTH_SHORT).show();
            } else{ // TODO 跳转详情界面Activity
                Toast.makeText(getContext(), "不知名的点击", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        new TrendsTask(this).execute(Configs.HOTTRENDS,lastId);
    }
}
