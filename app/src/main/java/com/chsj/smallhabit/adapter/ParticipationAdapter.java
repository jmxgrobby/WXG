package com.chsj.smallhabit.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chsj.smallhabit.R;
import com.chsj.smallhabit.bean.PersonalHabit;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by dongmeng on 2015/11/4.
 */
public class ParticipationAdapter extends BaseAdapter {

    private List<PersonalHabit> datas ;

    private LayoutInflater inflater ;

    public ParticipationAdapter(List<PersonalHabit> datas, Context context) {
        this.datas = datas;
        this.inflater = LayoutInflater.from(context) ;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
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
        PersonalHabit pb = datas.get(position) ;
        ViewHolder holder = null ;

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_for_list_partcaption,parent,false) ;

            holder = new ViewHolder() ;

            holder.textView = (TextView) convertView.findViewById(R.id.partcaption_habit_name);

            for (int i = 1; i < 8; i++) {
                holder.imageViews[i-1] = (ImageView) findView(convertView,"partcaption_day_0"+i);
            }

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(pb.getName());

        List<Integer> days = pb.getDays();

        for (int i = 0; i < 7; i++) {
            Drawable drawable = null ;
            if (i<=pb.getWeekPlanCompleted()-1){
                drawable = convertView.getResources().getDrawable(R.drawable.circle_blue);
            }else {
                drawable = convertView.getResources().getDrawable(R.drawable.circle_white);
            }
            holder.imageViews[i].setBackgroundDrawable(drawable);
        }

        return convertView;
    }

    private static class ViewHolder{
        TextView textView ;
        ImageView[] imageViews = new ImageView[7] ;
    }


    /**
     * 利用类的反射，来获取指定 R.id.xxxx 代表的View
     *
     * @param container
     * @param fieldName
     * @return
     */
    public  View findView(View container, String fieldName) {
        View ret = null;

        if (container != null && fieldName != null) {
            Class<R.id> idClass = R.id.class;
            try {
                Field field = idClass.getDeclaredField(fieldName);

                int id = field.getInt(idClass);

                // 通过静态常量，获取int值，来查找 View
                ret = container.findViewById(id);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        return ret;
    }
}
