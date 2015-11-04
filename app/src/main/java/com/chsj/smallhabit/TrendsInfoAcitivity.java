package com.chsj.smallhabit;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chsj.smallhabit.adapter.TrendsAdapter;
import com.chsj.smallhabit.bean.CommentListEntity;
import com.chsj.smallhabit.bean.PraisePhotosEntity;
import com.chsj.smallhabit.bean.TrendsEntity;
import com.chsj.smallhabit.bean.TrendsPhotosEntity;
import com.chsj.smallhabit.utils.DimensionUtil;
import com.chsj.smallhabit.utils.EventUtils;
import com.chsj.smallhabit.utils.GetRightUrlUtils;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;
/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/4.
 */
/**
 * 动态详情展示界面
 */
public class TrendsInfoAcitivity extends Activity implements View.OnClickListener {
    //小图标
    private ImageView GenPhoto;
    // 昵称
    private TextView GenNickName;
    // 习惯的布局
    private LinearLayout xg_layout;
    //赞次数
    private TextView PraiseCount;
    //评论次数
    private TextView CommentCount;

    //点赞的人图标布局
    private LinearLayout PraiseImages;

    //评论布局
    private LinearLayout CommentsLayout;

    private TrendsEntity trendsEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_trends_info_acitivity);
         trendsEntity = (TrendsEntity) getIntent().getSerializableExtra("data");
        Log.d("debug111",trendsEntity.getGenNickName());
        CommentCount = (TextView) findViewById(R.id.trends_info_comments);
        GenNickName = (TextView) findViewById(R.id.trends_info_nickname);
        GenPhoto = (ImageView) findViewById(R.id.trends_info_phone);
        PraiseCount = (TextView) findViewById(R.id.trends_info_loves);
        xg_layout = (LinearLayout) findViewById(R.id.trends_info_xg_layout);
        PraiseImages = (LinearLayout) findViewById(R.id.trends_info_zans_images);
        CommentsLayout = (LinearLayout) findViewById(R.id.trends_info_comments_layout);
        initData();

        EventUtils.setEvent(this,CommentsLayout,PraiseImages,PraiseCount,CommentCount,GenPhoto);
    }

    private void initData() {
        //头像
        String url = GetRightUrlUtils.getUrl(trendsEntity.getGenPhoto());
        if (url != null) {
            Picasso.with(this).load(url).into(GenPhoto);
        }
        //昵称
        GenNickName.setText(trendsEntity.getGenNickName());
        
        //习惯
        //习惯布局
        LinearLayout layout = (LinearLayout) LinearLayout.inflate(this, R.layout.test_xg_layout, null);
        TextView HabitName = (TextView) layout.findViewById(R.id.discover_square_hot_listview_item_xg);
        TextView Checks = (TextView) layout.findViewById(R.id.discover_square_hot_listview_item_sumtime);
        ImageView big_image = (ImageView) layout.findViewById(R.id.discover_square_hot_listview_item_big_image);
        //响应事件 点击跳转界面

        //习惯大图布局
        String big_image_url;
        List<TrendsPhotosEntity> photosEntities = trendsEntity.getTrendsPhotosEntities();
        if (photosEntities != null) {
            String original = photosEntities.get(0).getOriginal();
            big_image_url = GetRightUrlUtils.getUrl(original);
            Picasso.with(this).load(big_image_url).into(big_image);
        }
        //习惯其他数据布局
        String userMsg = trendsEntity.getUserMsg();
        if (userMsg != null && userMsg.length() > 0 && !userMsg.equals("null")) {
            TextView msg = (TextView) layout.findViewById(R.id.discover_square_hot_listview_item_msg);
            msg.setVisibility(View.VISIBLE);
            msg.setTextSize(15);
            msg.setText("\"" + userMsg + "\"");
        }

        HabitName.setText(trendsEntity.getHabitName());
        Checks.setText(trendsEntity.getChecks() + "天");
        xg_layout.addView(layout);

        
        
        
        //赞次数
        int praiseCount = trendsEntity.getPraiseCount();
        if (praiseCount != 0) {
            PraiseCount.setText("" + praiseCount);
        } else {
            PraiseCount.setText("赞");
        }
        //评论次数
        int commentCount = trendsEntity.getCommentCount();
        if (commentCount != 0) {
            CommentCount.setText("" + commentCount);
        } else {
            CommentCount.setText("评论");
        }
        //赞的人图标
        List<PraisePhotosEntity> praisePhotosEntities = trendsEntity.getPraisePhotosEntities();
        if (praisePhotosEntities != null && praisePhotosEntities.size() > 0) {
            PraiseImages.removeAllViews();
            ImageView imageView1 = new ImageView(this);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                    DimensionUtil.dpTopx(this, 20),
                    DimensionUtil.dpTopx(this, 20)
            );

            params1.setMargins(DimensionUtil.dpTopx(this, 5),
                    DimensionUtil.dpTopx(this, 2),
                    DimensionUtil.dpTopx(this, 5),
                    DimensionUtil.dpTopx(this, 2));
            imageView1.setLayoutParams(params1);
            imageView1.setImageResource(R.mipmap.praise_non);
            PraiseImages.addView(imageView1);
            for (int i = 0; i < praisePhotosEntities.size(); i++) {
                PraisePhotosEntity praisePhotosEntity = praisePhotosEntities.get(i);
                String photoUrl = praisePhotosEntity.getPhoto();
                if (photoUrl != null && photoUrl.trim().length() > 0) {
                    ImageView imageView = new ImageView(this);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            DimensionUtil.dpTopx(this, 20),
                            DimensionUtil.dpTopx(this, 20)
                    );

                    params.setMargins(DimensionUtil.dpTopx(this, 5),
                            DimensionUtil.dpTopx(this, 2),
                            DimensionUtil.dpTopx(this, 5),
                            DimensionUtil.dpTopx(this, 2));
                    imageView.setLayoutParams(params);
                    Picasso.with(this).load(GetRightUrlUtils.getUrl(photoUrl)).into(imageView);
                    PraiseImages.addView(imageView);
                }
            }
            PraiseImages.setVisibility(View.VISIBLE);
        }
        
        
        //评论
        List<CommentListEntity> commentListEntities = trendsEntity.getCommentListEntities();
        if (commentListEntities != null && commentListEntities.size() > 0) {
            CommentsLayout.removeAllViews();
            for (int i = 0; i < commentListEntities.size(); i++) {
                TextView textView = TrendsAdapter.getCommentTextView(commentListEntities, i,this);
                textView.setTag("CommentListEntity:"  + i);
                CommentsLayout.addView(textView);
            }
            CommentsLayout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
