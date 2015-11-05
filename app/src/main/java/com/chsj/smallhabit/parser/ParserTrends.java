package com.chsj.smallhabit.parser;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

import com.chsj.smallhabit.bean.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 解析动态的类
 */
public class ParserTrends {
    public static List<TrendsEntity> getTrendsList(String jsonString){
        List<TrendsEntity> list = null;
        if (jsonString != null) {
            list = new LinkedList<>();
            TrendsEntity trendsEntity;
            ArrayList<TrendsPhotosEntity> trendsPhotosEntities;
            ArrayList<CommentListEntity> commentListEntities;
            ArrayList<PraisePhotosEntity>  praisePhotosEntities;
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.optJSONArray("Value");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        //解析最外层属性
                        JSONObject jso = jsonArray.getJSONObject(i);
                        trendsEntity = new TrendsEntity();
                        trendsEntity.setChecks(jso.getInt("Checks"));
                        trendsEntity.setCheckTime(jso.getInt("CheckTime"));
                        trendsEntity.setHabitName(jso.getString("HabitName"));
                        trendsEntity.setDetailId(jso.getString("DetailId"));
                        trendsEntity.setGenNickName(jso.getString("GenNickName"));
                        trendsEntity.setGenPhoto(jso.getString("GenPhoto"));
                        trendsEntity.setPraiseCount(jso.getInt("PraiseCount"));
                        trendsEntity.setCommentCount(jso.getInt("CommentCount"));
                        trendsEntity.setUserMsg(jso.optString("UserMsg"));
                        trendsEntity.setGenId(jso.getString("GenId"));
                        JSONArray photos = jso.optJSONArray("Photos");

                        //解析Photos
                        if (photos != null&&photos.length()>0) {
                            trendsPhotosEntities = new ArrayList<>();
                            TrendsPhotosEntity trendsPhotosEntity;
                            for (int j = 0; j < photos.length(); j++) {
                                JSONObject jsonObject1 = photos.getJSONObject(j);
                                trendsPhotosEntity  = new TrendsPhotosEntity();
                                trendsPhotosEntity.setOriginal(jsonObject1.getString("Original"));
                                trendsPhotosEntities.add(trendsPhotosEntity);
                            }
                            trendsEntity.setTrendsPhotosEntities(trendsPhotosEntities);
                        }
                        //解析评论列表
                        JSONArray commentList = jso.optJSONArray("CommentList");
                        if (commentList != null&&commentList.length()>0) {
                            commentListEntities = new ArrayList<>();
                            CommentListEntity commentListEntity;
                            ReplySenderEntity replySenderEntity;
                            ReplySenderEntity replyReceiver;
                            for(int k=0;k<commentList.length();k++){
                                commentListEntity = new CommentListEntity();
                                JSONObject jsonObject1 = commentList.getJSONObject(k);


                                commentListEntity.setCommentId(jsonObject1.getString("CommentId"));
                                commentListEntity.setMsg(jsonObject1.getString("Msg"));
                                commentListEntity.setRow(jsonObject1.getInt("Row"));

                                replySenderEntity= new ReplySenderEntity() ;
                                replyReceiver= new ReplySenderEntity() ;

                                JSONObject replySender = jsonObject1.getJSONObject("ReplySender");
                                replySenderEntity.setNickName(replySender.getString("NickName"));
                                replySenderEntity.setPhoto(replySender.getString("Photo"));
                                replySenderEntity.setUserId(replySender.getString("UserId"));

                                JSONObject replyReceiver1 = jsonObject1.optJSONObject("ReplyReceiver");
                                if (replyReceiver1 != null) {
                                    replyReceiver.setNickName(replyReceiver1.getString("NickName"));
                                    replyReceiver.setPhoto(replyReceiver1.getString("Photo"));
                                    replyReceiver.setUserId(replyReceiver1.getString("UserId"));
                                    commentListEntity.setReplyReceiver(replyReceiver);
                                }

                                commentListEntity.setReplySenderEntity(replySenderEntity);
                                commentListEntities.add(commentListEntity);
                            }
                            trendsEntity.setCommentListEntities(commentListEntities);
                        }

                        //解析点赞人的图标
                        JSONArray praisePhotos = jso.optJSONArray("PraisePhotos");
                        if (praisePhotos != null && praisePhotos.length()>0) {
                            praisePhotosEntities = new ArrayList<>();
                            PraisePhotosEntity praisePhotosEntity;
                            for (int i1 = 0; i1 < praisePhotos.length(); i1++) {
                                JSONObject jsonObject1 = praisePhotos.getJSONObject(i1);
                                praisePhotosEntity = new PraisePhotosEntity();
                                praisePhotosEntity.setUserId(jsonObject1.getString("UserId"));
                                praisePhotosEntity.setPhoto(jsonObject1.getString("Photo"));
                                praisePhotosEntity.setNickName(jsonObject1.getString("NickName"));
                                praisePhotosEntities.add(praisePhotosEntity);
                            }
                            trendsEntity.setPraisePhotosEntities(praisePhotosEntities);
                        }


                        list.add(trendsEntity);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        return list;
    }

}
