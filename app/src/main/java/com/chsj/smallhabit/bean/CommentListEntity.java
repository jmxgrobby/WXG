package com.chsj.smallhabit.bean;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

import java.io.Serializable;

/**
 * 评论数据类
 */
public class CommentListEntity implements Serializable{


    /**
     * CommentId : e19558ad-be68-4f2c-b749-0e28f46f8208__0_0
     * Msg : 顺便把签名也练练好。
     * Row : 1
     */

    private String CommentId;
    private String Msg;
    private int Row;
    private ReplySenderEntity replySenderEntity;
    private ReplySenderEntity replyReceiver;

    public void setCommentId(String CommentId) {
        this.CommentId = CommentId;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public void setRow(int Row) {
        this.Row = Row;
    }

    public String getCommentId() {
        return CommentId;
    }

    public String getMsg() {
        return Msg;
    }

    public int getRow() {
        return Row;
    }

    public ReplySenderEntity getReplySenderEntity() {
        return replySenderEntity;
    }

    public void setReplySenderEntity(ReplySenderEntity replySenderEntity) {
        this.replySenderEntity = replySenderEntity;
    }

    public ReplySenderEntity getReplyReceiver() {
        return replyReceiver;
    }

    public void setReplyReceiver(ReplySenderEntity replyReceiver) {
        this.replyReceiver = replyReceiver;
    }
}
