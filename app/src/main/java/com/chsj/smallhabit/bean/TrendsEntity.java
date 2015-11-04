package com.chsj.smallhabit.bean;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/3.
 */

import java.util.List;

/**
 * 发现碎片点击广场进入的动态界面显示的数据实体类
 */
public class TrendsEntity {


    /**
     * DetailId : b4bea183-5453-4443-8dca-a77e0cc6ca268
     * Distance : 0
     * RemindFans : null
     * GenId : 0efec201-8d6d-4365-b918-f5324b89e397__0_0
     * HabitName : 坚持每天画画
     * Checks : 134
     * Photos : [{"Original":"Picture.svc/GetReadImg/635820988399647084_1_2.jpg","Thumbnail":"Picture.svc/GetReadImg/th_635820988399647084_1_2.jpg"}]
     * UserMsg :
     * Position : null
     * CommentList : [{"ReplySender":{"UserId":"2&3925043885","NickName":"Susan","Photo":"Picture.svc/GetReadImg/635809760947568609_1_0.jpg"},"ReplyReceiver":{"UserId":"1&DC781353FB42B2261892DEF77543DD17","NickName":"梦中人瑞","Photo":"Picture.svc/GetReadImg/635729012156831255_2_0.jpg"},"CommentId":"e19558ad-be68-4f2c-b749-0e28f46f8208__0_0","Msg":"顺便把签名也练练好。","Row":1},{"ReplySender":{"UserId":"2&3925043885","NickName":"Susan","Photo":"Picture.svc/GetReadImg/635809760947568609_1_0.jpg"},"ReplyReceiver":{"UserId":"1&DC781353FB42B2261892DEF77543DD17","NickName":"梦中人瑞","Photo":"Picture.svc/GetReadImg/635729012156831255_2_0.jpg"},"CommentId":"f8d25824-8268-4451-b5dc-3f26b45fac2c__0_0","Msg":"怎么办 我现在好紧张。让我再练练手吧。","Row":2},{"ReplySender":{"UserId":"2&3925043885","NickName":"Susan","Photo":"Picture.svc/GetReadImg/635809760947568609_1_0.jpg"},"ReplyReceiver":{"UserId":"c7fef402-4b13-47ef-8166-b78485bc5e8311","NickName":"娜娜和","Photo":"Picture.svc/GetReadImg/98db0543-5fd8-4c0b-aad0-a490828a915756.jpg"},"CommentId":"5e859ad5-a38b-4974-b131-6907b3ea19d8__0_0","Msg":"怎么办 我现在好紧张。","Row":3},{"ReplySender":{"UserId":"1&DC781353FB42B2261892DEF77543DD17","NickName":"梦中人瑞","Photo":"Picture.svc/GetReadImg/635729012156831255_2_0.jpg"},"ReplyReceiver":null,"CommentId":"303f51c2-cb17-452a-b17f-a0c04776c67e__0_0","Msg":"今天画的我好喜欢。什么时候给我带签名的？","Row":4},{"ReplySender":{"UserId":"c7fef402-4b13-47ef-8166-b78485bc5e8311","NickName":"娜娜和","Photo":"Picture.svc/GetReadImg/98db0543-5fd8-4c0b-aad0-a490828a915756.jpg"},"ReplyReceiver":null,"CommentId":"32eb1ffa-4c98-4acc-a2c7-a68492fe48d4__0_0","Msg":"怎么办 现在好喜欢你画的画","Row":5}]
     * GenUser : 2&3925043885
     * GenNickName : Susan
     * GenPhoto : Picture.svc/GetReadImg/635809760947568609_1_0.jpg
     * IsPraise : 0
     * CheckTime : 1446501884557
     * PraiseCount : 17
     * CommentCount : 5
     * PraisePhotos : [{"UserId":"9885cbc16c076d9aa9388acce1bab738&1__7_0","NickName":null,"Photo":"Picture.svc/GetReadImg/635818384103830058_2_0.jpg"},{"UserId":"45e76c3c3544f8a580e503e83755bd3c&2__2_0","NickName":null,"Photo":"http://tp2.sinaimg.cn/5580605417/180/5735900961/1"},{"UserId":"2&3295503955","NickName":null,"Photo":"Picture.svc/GetReadImg/635760906784776808_1_0.jpg"},{"UserId":"a7a45082723092d563da3dcb948ab050&3__7_0","NickName":null,"Photo":"http://wx.qlogo.cn/mmopen/V5ATsbBCAyH3jvIujBa0n4T8Lev18g1SrBAlue8VBYYzplmdAXKyylia2Y9aZh1js0PFXHy9mu5U4afQkDhFMXqlWqXAYKOYo/0"},{"UserId":"30d3582f1c5c7c4d0155f7de51301305&2__1_0","NickName":null,"Photo":"http://tp3.sinaimg.cn/3976187058/180/5737260265/0"},{"UserId":"4447033f8b5148ce2c61877a1ad74d35&3__2_0","NickName":null,"Photo":"http://wx.qlogo.cn/mmopen/kF6GYXI8efg4INRpW9pxvpzEvl8nXOWv6NZIOia3zXszmnuPgrub1taBcbjgtAtN47uTZDPdMctagKef24F0XeQ4hGPde9pYW/0"},{"UserId":"2c603b15a6611eeecb887b33e9e16a6f&3__0_0","NickName":null,"Photo":"Picture.svc/GetReadImg/635812337359544453_2_0.jpg"},{"UserId":"9431c5aa-7331-487d-8991-fc4aac2f8eaa__7_0","NickName":null,"Photo":"Picture.svc/GetReadImg/635817142569400993_1_0.jpg"}]
     * Row : 0
     * Address :
     * Relation : 0
     * GenGender : 1
     * CreateTime : 1446501884917
     */



    private String DetailId;
    private int Distance;
    private Object RemindFans;
    //动态id用来做下一页的判断的
    private String GenId;
    //习惯名
    private String HabitName;
    //连续时间
    private int Checks;
    //用户名
    private String UserMsg;
    //用户号
    private String GenUser;
    //用户昵称
    private String GenNickName;
    //用户小图标
    private String GenPhoto;
    private int IsPraise;
    private long CheckTime;
    //赞次数
    private int PraiseCount;
    //评论次数
    private int CommentCount;
    //中间大图
    private List<TrendsPhotosEntity> trendsPhotosEntities;

    public String getDetailId() {
        return DetailId;
    }

    public void setDetailId(String detailId) {
        DetailId = detailId;
    }

    public int getDistance() {
        return Distance;
    }

    public void setDistance(int distance) {
        Distance = distance;
    }

    public Object getRemindFans() {
        return RemindFans;
    }

    public void setRemindFans(Object remindFans) {
        RemindFans = remindFans;
    }

    public String getGenId() {
        return GenId;
    }

    public void setGenId(String genId) {
        GenId = genId;
    }

    public String getHabitName() {
        return HabitName;
    }

    public void setHabitName(String habitName) {
        HabitName = habitName;
    }

    public int getChecks() {
        return Checks;
    }

    public void setChecks(int checks) {
        Checks = checks;
    }

    public String getUserMsg() {
        return UserMsg;
    }

    public void setUserMsg(String userMsg) {
        UserMsg = userMsg;
    }

    public String getGenUser() {
        return GenUser;
    }

    public void setGenUser(String genUser) {
        GenUser = genUser;
    }

    public String getGenNickName() {
        return GenNickName;
    }

    public void setGenNickName(String genNickName) {
        GenNickName = genNickName;
    }

    public String getGenPhoto() {
        return GenPhoto;
    }

    public void setGenPhoto(String genPhoto) {
        GenPhoto = genPhoto;
    }

    public int getIsPraise() {
        return IsPraise;
    }

    public void setIsPraise(int isPraise) {
        IsPraise = isPraise;
    }

    public long getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(long checkTime) {
        CheckTime = checkTime;
    }

    public int getPraiseCount() {
        return PraiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        PraiseCount = praiseCount;
    }

    public int getCommentCount() {
        return CommentCount;
    }

    public void setCommentCount(int commentCount) {
        CommentCount = commentCount;
    }

    public List<TrendsPhotosEntity> getTrendsPhotosEntities() {
        return trendsPhotosEntities;
    }

    public void setTrendsPhotosEntities(List<TrendsPhotosEntity> trendsPhotosEntities) {
        this.trendsPhotosEntities = trendsPhotosEntities;
    }

    public List<CommentListEntity> getCommentListEntities() {
        return commentListEntities;
    }

    public void setCommentListEntities(List<CommentListEntity> commentListEntities) {
        this.commentListEntities = commentListEntities;
    }

    public List<PraisePhotosEntity> getPraisePhotosEntities() {
        return praisePhotosEntities;
    }

    public void setPraisePhotosEntities(List<PraisePhotosEntity> praisePhotosEntities) {
        this.praisePhotosEntities = praisePhotosEntities;
    }

    //评论列表
    private List<CommentListEntity> commentListEntities;

    //点赞（？评论）人图标
    private List<PraisePhotosEntity>  praisePhotosEntities;

}
