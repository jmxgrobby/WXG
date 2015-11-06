package com.chsj.smallhabit.adapter;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/10/22
 * Email: vhly@163.com
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.chsj.smallhabit.bean.HotRecommendHabit;
import com.chsj.smallhabit.utils.Configs;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 轮播海报的ViewPager Adapter
 */
public class PicPagerAdapter extends PagerAdapter {

    private List<HotRecommendHabit> picData;
    private WindowManager windowManager ;
    private View.OnClickListener clickListener ;
    public PicPagerAdapter(List<HotRecommendHabit> picData , WindowManager manager , View.OnClickListener clickListener) {
        this.picData = picData;
        this.windowManager = manager ;
        this.clickListener = clickListener ;
    }

    @Override
    public int getCount() {
        int ret = 0;


        if (picData != null) {
            if(!picData.isEmpty()){
                ret = Integer.MAX_VALUE; // 使用整形最大值，来描述一个假的循环
            }
        }

        return ret;
    }

    /**
     * 判断 view 是否是通过/或者由 Object 创建出来的；
     * 通常 对于 只返回View的PagerAdapter，那么可以简写成 view == object
     *
     * @param view ViewPager 内部管理的一个View
     * @param object 由 instantiateItem() 返回的对象
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 根据指定的位置，创建一个对象，这个对象可以就是View，也可以是管理View的对象
     * 例如 Fragment。
     * 最终在方法返回之前，一定要将实际的View，添加到 container，
     * 并且，永远不要 super
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();

        // TODO 因为 getCount 返回了整形最大值 实际的数据个数是有限的
        // 利用 position % 数据个数，从而影射成 实际数据的索引

        int index = position % picData.size();

        // TODO 根据 index 获取点击的位置；以及数据


        ImageView ret = new ImageView(context);

//        ret.setImageResource(R.mipmap.ic_launcher);

        Picasso.with(context).load(Configs.IMAGEHEAD+picData.get(index).getPicUrl()).into(ret);

        ViewGroup.LayoutParams lp =
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        DimensionUtil.getSuitablePx(640,320,windowManager)
                        );

        ret.setLayoutParams(lp);

        // TODO: 2015/11/6 给滚动图片设置点击的监听事件
        ret.setTag(position);
        ret.setOnClickListener(clickListener);

        container.addView(ret);

        return ret;
    }

    /**
     * 销毁对象，从容器删除视图
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
