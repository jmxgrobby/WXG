package com.chsj.smallhabit.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.chsj.smallhabit.R;
import com.chsj.smallhabit.bean.*;
import com.chsj.smallhabit.utils.Configs;
import com.chsj.smallhabit.utils.DimensionUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/4.
 */

/**
 * 异步任务请求数据类，请求的是所有的动态数据，根据传参不同 请求不同的数据
 */
public class TrendsAdapter extends BaseAdapter {
    private List<TrendsEntity> list;
    private Context context;
    private View.OnClickListener onClickListener;

    public TrendsAdapter(List<TrendsEntity> list, Context context, View.OnClickListener onClickListener) {
        this.list = list;
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null) {
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        TrendsEntity ret = null;
        if (list != null) {
            ret = list.get(position);
        }
        return ret;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.discover_square_hot_listview_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.CommentCount = (TextView) convertView.findViewById(R.id.discover_square_hot_listview_item_comments);

            viewHolder.CommentCount.setOnClickListener(onClickListener);

            viewHolder.GenNickName = (TextView) convertView.findViewById(R.id.discover_square_hot_listview_item_nickname);
            viewHolder.GenPhoto = (ImageView) convertView.findViewById(R.id.discover_square_hot_listview_item_phone);
            viewHolder.GenPhoto.setOnClickListener(onClickListener);

            viewHolder.PraiseCount = (TextView) convertView.findViewById(R.id.discover_square_hot_listview_item_loves);

            viewHolder.PraiseCount.setOnClickListener(onClickListener);

            viewHolder.xg_layout = (LinearLayout) convertView.findViewById(R.id.discover_square_hot_listview_item_xg_layout);
            viewHolder.PraiseImages = (LinearLayout) convertView.findViewById(R.id.discover_square_hot_listview_item_zans_images);
            viewHolder.PraiseImages.setOnClickListener(onClickListener);
            viewHolder.CommentsLayout = (LinearLayout) convertView.findViewById(R.id.discover_square_hot_listview_item_comments_layout);

            viewHolder.all_layout = (RelativeLayout) convertView.findViewById(R.id.discover_square_hot_listview_item_bg_layout);
            viewHolder.all_layout.setOnClickListener(onClickListener);
            convertView.setTag(viewHolder);
        }


        viewHolder.all_layout.setTag("layout:"+position);
        viewHolder.CommentCount.setTag("CommentCount:" + position);
        viewHolder.GenPhoto.setTag("GenPhoto:" + position);
        viewHolder.PraiseCount.setTag("PraiseCount:" + position);

        TrendsEntity trendsEntity = list.get(position);
        int praiseCount = trendsEntity.getPraiseCount();
        if (praiseCount != 0) {
            viewHolder.PraiseCount.setText("" + praiseCount);
        } else {
            viewHolder.PraiseCount.setText("赞");
        }
        int commentCount = trendsEntity.getCommentCount();
        if (commentCount != 0) {
            viewHolder.CommentCount.setText("" + commentCount);
        } else {
            viewHolder.CommentCount.setText("评论");
        }
        String nickName = trendsEntity.getGenNickName();
        viewHolder.GenNickName.setText(nickName);

        //习惯布局
        viewHolder.xg_layout.removeAllViews();
        LinearLayout layout = (LinearLayout) LinearLayout.inflate(context, R.layout.test_xg_layout, null);
        TextView HabitName = (TextView) layout.findViewById(R.id.discover_square_hot_listview_item_xg);
        TextView Checks = (TextView) layout.findViewById(R.id.discover_square_hot_listview_item_sumtime);
        ImageView big_image = (ImageView) layout.findViewById(R.id.discover_square_hot_listview_item_big_image);
        //响应事件 点击跳转界面
        HabitName.setTag("HabitName:" + position);
        HabitName.setOnClickListener(onClickListener);
        Checks.setTag("HabitName:" + position);
        Checks.setOnClickListener(onClickListener);
        //习惯大图布局
        String big_image_url;
        List<TrendsPhotosEntity> photosEntities = trendsEntity.getTrendsPhotosEntities();
        if (photosEntities != null) {
            String original = photosEntities.get(0).getOriginal();
            big_image_url = getUrl(original);
            Picasso.with(context).load(big_image_url).into(big_image);
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
        viewHolder.xg_layout.addView(layout);

        String genPhoto = trendsEntity.getGenPhoto();
        String url;
        url = getUrl(genPhoto);
        Picasso.with(context).load(url).into(viewHolder.GenPhoto);

        //点赞的人的图标
        List<PraisePhotosEntity> praisePhotosEntities = trendsEntity.getPraisePhotosEntities();
        if (praisePhotosEntities != null && praisePhotosEntities.size() > 0) {
            viewHolder.PraiseImages.removeAllViews();
            viewHolder.PraiseImages.setTag("PraiseImages:" + position);
            ImageView imageView1 = new ImageView(context);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                    DimensionUtil.dpTopx(context, 20),
                    DimensionUtil.dpTopx(context, 20)
            );

            params1.setMargins(DimensionUtil.dpTopx(context, 5),
                    DimensionUtil.dpTopx(context, 2),
                    DimensionUtil.dpTopx(context, 5),
                    DimensionUtil.dpTopx(context, 2));
            imageView1.setLayoutParams(params1);
            imageView1.setImageResource(R.mipmap.praise_non);
            viewHolder.PraiseImages.addView(imageView1);
            for (int i = 0; i < praisePhotosEntities.size(); i++) {
                PraisePhotosEntity praisePhotosEntity = praisePhotosEntities.get(i);
                String photoUrl = praisePhotosEntity.getPhoto();
                if (photoUrl != null && photoUrl.trim().length() > 0) {
                    ImageView imageView = new ImageView(context);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            DimensionUtil.dpTopx(context, 20),
                            DimensionUtil.dpTopx(context, 20)
                    );

                    params.setMargins(DimensionUtil.dpTopx(context, 5),
                            DimensionUtil.dpTopx(context, 2),
                            DimensionUtil.dpTopx(context, 5),
                            DimensionUtil.dpTopx(context, 2));
                    imageView.setLayoutParams(params);
                    Picasso.with(context).load(getUrl(photoUrl)).into(imageView);
                    viewHolder.PraiseImages.addView(imageView);
                }
            }
            viewHolder.PraiseImages.setVisibility(View.VISIBLE);


        }

        //评论详情
        List<CommentListEntity> commentListEntities = trendsEntity.getCommentListEntities();
        if (commentListEntities != null && commentListEntities.size() > 0) {
            viewHolder.CommentsLayout.removeAllViews();
            for (int i = 0; i < commentListEntities.size(); i++) {
                TextView textView = getCommentTextView(commentListEntities, i);
                textView.setTag("CommentListEntity:" + position + ":" + i);
                textView.setOnClickListener(onClickListener);
                viewHolder.CommentsLayout.addView(textView);
            }
            viewHolder.CommentsLayout.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @NonNull
    private TextView getCommentTextView(List<CommentListEntity> commentListEntities, int i) {
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = DimensionUtil.dpTopx(context, 10);
        textView.setLayoutParams(params);
        CommentListEntity commentListEntity = commentListEntities.get(i);
        ReplySenderEntity replyReceiver = commentListEntity.getReplyReceiver();
        ReplySenderEntity replySenderEntity = commentListEntity.getReplySenderEntity();
        SpannableStringBuilder style;
        String str;
        if (replyReceiver != null) {
            String nickName1 = replySenderEntity.getNickName();
            Log.d("debug111", nickName1);
            String nickName2 = replyReceiver.getNickName();
            str = nickName1 + "回复" + nickName2 + ":" + commentListEntity.getMsg();
            style = new SpannableStringBuilder(str);
            //发布者
            style.setSpan(new ForegroundColorSpan(Color.rgb(0x00, 0x7a, 0xff)),
                    0, nickName1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //发布者回复的人
            style.setSpan(new ForegroundColorSpan(Color.rgb(0x00, 0x7a, 0xff)),
                    nickName1.length() + 2, nickName1.length() + 2 + nickName2.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            String nickName1 = replySenderEntity.getNickName();
            str = nickName1 + ":" + commentListEntity.getMsg();
            style = new SpannableStringBuilder(str);
            //发布者
            style.setSpan(new ForegroundColorSpan(Color.rgb(0x00, 0x7a, 0xff)),
                    0, nickName1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setText(style);
        textView.setTextSize(15);
        textView.setTextColor(Color.BLACK);
        return textView;
    }

    /**
     * 获取正确的网址
     *
     * @param genPhoto
     * @return
     */
    private String getUrl(String genPhoto) {
        String url;
        if (genPhoto.trim().length() > 0 && genPhoto != null) {
            if (genPhoto.startsWith("Picture")) {
                url = Configs.IMAGEHEAD + genPhoto;
            } else {
                url = genPhoto;
            }
        } else
            url = null;
        return url;
    }

    class ViewHolder {
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


        //整体布局，即整个item
        private RelativeLayout all_layout;
    }
}
