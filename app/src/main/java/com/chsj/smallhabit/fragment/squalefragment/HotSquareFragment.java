package com.chsj.smallhabit.fragment.squalefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.chsj.smallhabit.EntryActivity;
import com.chsj.smallhabit.HabbitInfoActivity;
import com.chsj.smallhabit.R;
import com.chsj.smallhabit.TrendsInfoAcitivity;
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
 * 这是广场跳转的Activity里面热门帖子的碎片
 */
public class HotSquareFragment extends BaseFragment implements AfterGetTrends, View.OnClickListener ,PullToRefreshBase.OnRefreshListener {

    private View bg_layout;
    private PullToRefreshListView pullToRefreshListView;

    //最后一条显示的数据的id，用来做下拉刷新
    private String lastId;
    public HotSquareFragment() {
    }

    private List<TrendsEntity> list;
    private TrendsAdapter adapter;

    private boolean isLoading;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bg_layout = inflater.inflate(R.layout.fragment_square_item, container, false);

        isLoading = getContext().getSharedPreferences(Configs.SHARDPERFACE_NAME, Context.MODE_PRIVATE)
                .getBoolean(Configs.ISLOADING,false);

        //pullToRefreshListView 的初始化及设置
        pullToRefreshListView = (PullToRefreshListView) bg_layout.findViewById(R.id.fragment_hot_square_pulltorefreshview);
        list = new LinkedList<>();
        adapter = new TrendsAdapter(list, getContext(), this);
        //下拉刷新控件处理
        pullToRefreshListView.setAdapter(adapter);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        pullToRefreshListView.setOnRefreshListener(this);

        //异步任务请求获取UI数据
        new TrendsTask(this).execute(Configs.HOTTRENDS,lastId);
        return  bg_layout;
    }



    @Override
    public String getFragmentTitle() {
        return "热门";
    }

    @Override
    public void sendTrends(List<TrendsEntity> list) {
        if (list != null) {
            this.list.addAll(list);
            lastId = list.get(list.size()-1).getGenId();
            adapter.notifyDataSetChanged();
            //数据更新完成之后，调整上啦加载完成加载
            pullToRefreshListView.onRefreshComplete();
        }
    }

    /**
     * 响应事件，处理listView的item的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag != null && tag instanceof String) {
            String s = (String) tag;
            String[] split = s.split(":");
            Intent intent;
            // TODO 跳转用户界面Activity
            int location = Integer.parseInt(split[1]);
            if (split[0].equals("GenPhoto")) {
                Toast.makeText(getContext(),
                        "点击了" + list.get(location).getGenNickName(),
                        Toast.LENGTH_SHORT).show();
            } else if(split[0].equals("HabitName")) { // TODO 跳转习惯介绍界面Activity
                intent = new Intent(getContext(), HabbitInfoActivity.class);
                intent.putExtra("DetailId",list.get(location).getDetailId());
                startActivity(intent);
            }else if(split[0].equals("PraiseImages")){ // TODO 跳转赞我的人界面Activity
                Toast.makeText(getContext(),
                        "点击了"+ list.get(location).getGenNickName()+"点赞人图标",
                        Toast.LENGTH_SHORT).show();
            }else if(split[0].equals("CommentCount")){ // TODO 跳转评论界面Activity或者登录界面
                if(!isLoading){
                    intent  = new Intent(getContext(), EntryActivity.class);
                    startActivity(intent);
                }else{

                }
            }else if(split[0].equals("PraiseCount")){ // TODO 跳转赞界面Activity或者登录界面
                if(!isLoading){
                    intent  = new Intent(getContext(), EntryActivity.class);
                    startActivity(intent);
                }else{

                }
            }else if(split[0].equals("CommentListEntity")){ // TODO 跳转评论详情界面Activity或者登录界面
                if(!isLoading){
                    intent  = new Intent(getContext(), EntryActivity.class);
                    startActivity(intent);
                }else{

                }

            } else{ // TODO 跳转详情界面Activity
                intent = new Intent(getContext(), TrendsInfoAcitivity.class);
                intent.putExtra("data",list.get(location));
                startActivity(intent);
            }
        }
    }

    @Override
    public void onRefresh(PullToRefreshBase refreshView) {
        new TrendsTask(this).execute(Configs.HOTTRENDS,lastId);
    }
}
