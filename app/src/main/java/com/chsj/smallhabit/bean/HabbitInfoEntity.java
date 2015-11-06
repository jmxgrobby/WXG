package com.chsj.smallhabit.bean;

/**
 * Created
 * Author: jmxgrobby
 * Email: jmxgrobby@163.com
 * Date:  2015/11/5.
 */

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * 习惯数据结构
 */
public class HabbitInfoEntity {

    /**
     * PersonalPlan : 0
     * PersonalPlanCompleted : 0
     * UserMsgPhotos : null
     * WeekPlan : 0
     * HabitStatus : 2
     * SameMonthChecks : null
     * Id : 3cbf8344-2a73-4a2b-a1eb-4a003cff565724
     * Name : 感恩
     * JoinCount : 36933
     * NowTicks : 1446722264013
     * IsRemind : 0
     * GenId : null
     * UserMsg : null
     * Amount : 0
     * ContinuousCheck : 0
     * Privacy : 0
     */

    private int PersonalPlan;
    private int PersonalPlanCompleted;
    private String UserMsgPhotos;
    private int WeekPlan;
    private int HabitStatus;
    private String SameMonthChecks;
    private String Id;
    private String Name;
    private int JoinCount;
    private long NowTicks;
    private int IsRemind;
    private String GenId;
    private String UserMsg;
    private int Amount;
    private int ContinuousCheck;
    private int Privacy;
    private String PicUrls;
    private List<HabbitDesEntity> habbitDesEntities;

    public List<HabbitDesEntity> getHabbitDesEntities() {
        return habbitDesEntities;
    }

    public void setHabbitDesEntities(List<HabbitDesEntity> habbitDesEntities) {
        this.habbitDesEntities = habbitDesEntities;
    }

    public String getPicUrls() {
        return PicUrls;
    }

    public void setPicUrls(String picUrls) {
        PicUrls = picUrls;
    }

    public void setPersonalPlan(int PersonalPlan) {
        this.PersonalPlan = PersonalPlan;
    }

    public void setPersonalPlanCompleted(int PersonalPlanCompleted) {
        this.PersonalPlanCompleted = PersonalPlanCompleted;
    }

    public void setUserMsgPhotos(String UserMsgPhotos) {
        this.UserMsgPhotos = UserMsgPhotos;
    }

    public void setWeekPlan(int WeekPlan) {
        this.WeekPlan = WeekPlan;
    }

    public void setHabitStatus(int HabitStatus) {
        this.HabitStatus = HabitStatus;
    }

    public void setSameMonthChecks(String SameMonthChecks) {
        this.SameMonthChecks = SameMonthChecks;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setJoinCount(int JoinCount) {
        this.JoinCount = JoinCount;
    }

    public void setNowTicks(long NowTicks) {
        this.NowTicks = NowTicks;
    }

    public void setIsRemind(int IsRemind) {
        this.IsRemind = IsRemind;
    }

    public void setGenId(String GenId) {
        this.GenId = GenId;
    }

    public void setUserMsg(String UserMsg) {
        this.UserMsg = UserMsg;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public void setContinuousCheck(int ContinuousCheck) {
        this.ContinuousCheck = ContinuousCheck;
    }

    public void setPrivacy(int Privacy) {
        this.Privacy = Privacy;
    }

    public int getPersonalPlan() {
        return PersonalPlan;
    }

    public int getPersonalPlanCompleted() {
        return PersonalPlanCompleted;
    }

    public String getUserMsgPhotos() {
        return UserMsgPhotos;
    }

    public int getWeekPlan() {
        return WeekPlan;
    }

    public int getHabitStatus() {
        return HabitStatus;
    }

    public String getSameMonthChecks() {
        return SameMonthChecks;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getJoinCount() {
        return JoinCount;
    }

    public long getNowTicks() {
        return NowTicks;
    }

    public int getIsRemind() {
        return IsRemind;
    }

    public String getGenId() {
        return GenId;
    }

    public String getUserMsg() {
        return UserMsg;
    }

    public int getAmount() {
        return Amount;
    }

    public int getContinuousCheck() {
        return ContinuousCheck;
    }

    public int getPrivacy() {
        return Privacy;
    }


    public void parseJson(JSONObject jsonObject) {
        try {
            Log.d("TAG",jsonObject.toString()) ;
            JSONObject object = jsonObject.getJSONObject("Value");
            if (object != null) {
                JSONArray picUrls = object.optJSONArray("PicUrls");
                if (picUrls != null) {
                    PicUrls = picUrls.getString(0);
                }
                JoinCount = object.getInt("JoinCount");
                Id = object.getString("Id");
                Name = object.getString("Name");
                JSONArray jsonArray = object.optJSONArray("Des");
                if (jsonArray != null) {
                    habbitDesEntities = new LinkedList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        HabbitDesEntity habbitDesEntity = new HabbitDesEntity();
                        habbitDesEntity.parseJson(jsonObject1);
                        if (habbitDesEntities.size() == 0) {
                            habbitDesEntities.add(habbitDesEntity);
                        }
                        for (int j = 0; j < habbitDesEntities.size(); j++) {
                            if (habbitDesEntities.get(j).getKey() == habbitDesEntity.getKey()) {
                                break;
                            } else if(j==habbitDesEntities.size()-1){
                                habbitDesEntities.add(habbitDesEntity);
                            }
                        }
                    }


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
