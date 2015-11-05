package com.chsj.smallhabit.adapter;

/**
 * ProjectName: com.chsj.smallhabit.adapter
 * Created by : ChSJ.Team
 * Email:  15001045515@163.com
 * user: 杨空明
 * on 2015-11-05.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chsj.smallhabit.R;
import com.chsj.smallhabit.bean.FriendEntity;
import com.chsj.smallhabit.interfaceses.AddFriendCallBack;
import com.chsj.smallhabit.utils.GetRightUrlUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 加好友的adadpter
 */
public class AddFriendAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<FriendEntity> list;
    private AddFriendCallBack callBack;

    public AddFriendAdapter(AddFriendCallBack callBack, Context context, List<FriendEntity> list) {
        this.callBack = callBack;
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        int count=0;
        if(list!=null){
            count=list.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView!=null){
            view=convertView;
        }else{
            view= LayoutInflater.from(context).inflate(R.layout.activity_dicover_friend_items,null);
            ViewHolder holder=new ViewHolder();
            holder.friendName= (TextView) view.findViewById(R.id.add_friend_name);
            holder.friendPhoto= (ImageView) view.findViewById(R.id.add_friend_photo);
            holder.friendAttention= (ImageView) view.findViewById(R.id.add_friend_guanzhu);
            view.setTag(holder);
        }
        ViewHolder holder= (ViewHolder) view.getTag();
        holder.friendName.setText(list.get(position).getNickName());
        String photoUrl=list.get(position).getPhoto();
        holder.friendAttention.setImageResource(R.mipmap.guanzhu);
        holder.friendAttention.setTag(position);
        holder.friendAttention.setOnClickListener(this);
        Log.d("wenxin", "75" + photoUrl);
        String uri = GetRightUrlUtils.getUrl(photoUrl);
        Log.d("wenxin","77"+uri);
        Picasso.with(context).load(uri).into(holder.friendPhoto);
        return view;
    }

    @Override
    public void onClick(View v) {
        callBack.click(v);
    }

    class ViewHolder{
        TextView friendName;
        ImageView friendPhoto,friendAttention;
    }
}
