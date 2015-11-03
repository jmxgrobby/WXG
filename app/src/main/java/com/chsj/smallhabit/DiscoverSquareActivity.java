package com.chsj.smallhabit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import com.chsj.smallhabit.adapter.SquareActivityPageAdapter;
import com.chsj.smallhabit.fragment.squalefragment.BaseFragment;
import com.chsj.smallhabit.fragment.squalefragment.FriendSquareFragment;
import com.chsj.smallhabit.fragment.squalefragment.HotSquareFragment;
import com.chsj.smallhabit.fragment.squalefragment.NewSquareFragment;

import java.util.LinkedList;
import java.util.List;
/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

/**
 * 这是从发现碎片点击广场进入的Activity，主要显示一些动态信息
 */
public class DiscoverSquareActivity extends FragmentActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    //头部返回按钮
    private ImageView back;

    //导航条
    private TabLayout tabLayout;

    //ViewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_discover_square);

        back = (ImageView) findViewById(R.id.activity_discover_square_back);
        back.setOnClickListener(this);
        tabLayout = (TabLayout) findViewById(R.id.activity_discover_square_tablayout);
        viewPager = (ViewPager) findViewById(R.id.activity_discover_square_viewpager);

        // TODO 为viewPager准备数据源
        List<BaseFragment> list = new LinkedList<>();
        list.add(new HotSquareFragment());
        list.add(new FriendSquareFragment());
        list.add(new NewSquareFragment());

        // TODO 查询构造方法的第一个参数是否有错误？
        SquareActivityPageAdapter adapter = new SquareActivityPageAdapter(
                getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);


        // TODO 构建TabLayout
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);



    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_discover_square_back:
                finish();
                break;
        }
    }
}
