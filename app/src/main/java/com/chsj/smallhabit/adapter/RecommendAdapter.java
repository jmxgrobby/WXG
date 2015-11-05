package com.chsj.smallhabit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chsj.smallhabit.R;
import com.chsj.smallhabit.bean.HotRecommendHabit;
import com.chsj.smallhabit.utils.Configs;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;

/**
 * Created by dongmeng on 2015/11/5.
 */
public class RecommendAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<HotRecommendHabit> datas;
    private Context context ;

    public RecommendAdapter(List<HotRecommendHabit> datas, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context ;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null ;

        HotRecommendHabit recommendHabit = datas.get(position) ;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_for_recommend_habit,parent,false) ;

            holder = new ViewHolder() ;

            holder.itemRecommendBigIcon = (ImageView) convertView.findViewById(R.id.item_recommend_big_icon);

            holder.itemRecommendHabitTitle = (TextView)convertView.findViewById(R.id.item_recommend_habit_title) ;

            holder.itemRecommendHabitDetail = (TextView)convertView.findViewById(R.id.item_recommend_habit_detail) ;

            holder.itemRecommendRightArrow = (ImageView) convertView.findViewById(R.id.item_recommend_right_arrow);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(Configs.IMAGEHEAD.concat(recommendHabit.getPicUrl())).into(holder.itemRecommendBigIcon);
        holder.itemRecommendHabitTitle.setText(recommendHabit.getName()) ;

        StringBuilder sb = new StringBuilder() ;

        List<String> topSubdivision = recommendHabit.getTopSubdivision();
        for (String string : topSubdivision) {
            sb.append(string+" ") ;
        }
        holder.itemRecommendHabitDetail.setText(sb.toString().trim()) ;

        return convertView;
    }

    private class ViewHolder {

        ImageView itemRecommendBigIcon;

        ImageView itemRecommendRightArrow;

        TextView itemRecommendHabitDetail;

        TextView itemRecommendHabitTitle;

    }
}
