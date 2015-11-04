package com.chsj.smallhabit.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

/**
 * Created by dongmeng on 2015/11/3.
 */

public class PersonalHabit {

    /**
     * Amount : 1
     * ContinuousCheck : 0
     * Days : [0,0,0,0,0,1,0]
     * Icon :
     * Id : 2e5af39f-5321-4326-a458-0e4416381201__0_0
     * IsCollabra : 0
     * IsRemind : false
     * JoinCount : 1
     * MonthReports : [0,0,0,0,1]
     * Name : 做一个深刻的人
     * PersonalPlan : 0
     * PersonalPlanCompleted : 0
     * Privacy : 0
     * WeekPlan : 7
     * WeekPlanCompleted : 0
     * WeekReports : [0,0,0,0,1]
     */
    private String Id ;
    private String Name ;
    private int Amount;
    private int ContinuousCheck;
    private String Icon;
    private int IsCollabra;
    private boolean IsRemind;
    private int JoinCount;
    private int PersonalPlan;
    private int PersonalPlanCompleted;
    private int Privacy;
    private int WeekPlan;
    private int WeekPlanCompleted;
    private List<Integer> Days;
    private List<Integer> MonthReports;
    private List<Integer> WeekReports;


    private static Gson gson = new Gson() ;

    private PersonalHabit() {
    }

    public static PersonalHabit parse(JSONObject jsonObject) throws JSONException {
        PersonalHabit pHabit = null ;

        if (jsonObject != null) {
            pHabit = gson.fromJson(jsonObject.toString(), PersonalHabit.class) ;
        }

        return pHabit ;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public void setContinuousCheck(int ContinuousCheck) {
        this.ContinuousCheck = ContinuousCheck;
    }

    public void setIcon(String Icon) {
        this.Icon = Icon;
    }

    public void setIsCollabra(int IsCollabra) {
        this.IsCollabra = IsCollabra;
    }

    public void setIsRemind(boolean IsRemind) {
        this.IsRemind = IsRemind;
    }

    public void setJoinCount(int JoinCount) {
        this.JoinCount = JoinCount;
    }

    public void setPersonalPlan(int PersonalPlan) {
        this.PersonalPlan = PersonalPlan;
    }

    public void setPersonalPlanCompleted(int PersonalPlanCompleted) {
        this.PersonalPlanCompleted = PersonalPlanCompleted;
    }

    public void setPrivacy(int Privacy) {
        this.Privacy = Privacy;
    }

    public void setWeekPlan(int WeekPlan) {
        this.WeekPlan = WeekPlan;
    }

    public void setWeekPlanCompleted(int WeekPlanCompleted) {
        this.WeekPlanCompleted = WeekPlanCompleted;
    }

    public void setDays(List<Integer> Days) {
        this.Days = Days;
    }

    public void setMonthReports(List<Integer> MonthReports) {
        this.MonthReports = MonthReports;
    }

    public void setWeekReports(List<Integer> WeekReports) {
        this.WeekReports = WeekReports;
    }

    public int getAmount() {
        return Amount;
    }

    public int getContinuousCheck() {
        return ContinuousCheck;
    }

    public String getIcon() {
        return Icon;
    }

    public int getIsCollabra() {
        return IsCollabra;
    }

    public boolean isIsRemind() {
        return IsRemind;
    }

    public int getJoinCount() {
        return JoinCount;
    }

    public int getPersonalPlan() {
        return PersonalPlan;
    }

    public int getPersonalPlanCompleted() {
        return PersonalPlanCompleted;
    }

    public int getPrivacy() {
        return Privacy;
    }

    public int getWeekPlan() {
        return WeekPlan;
    }

    public int getWeekPlanCompleted() {
        return WeekPlanCompleted;
    }

    public List<Integer> getDays() {
        return Days;
    }

    public List<Integer> getMonthReports() {
        return MonthReports;
    }

    public List<Integer> getWeekReports() {
        return WeekReports;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isRemind() {
        return IsRemind;
    }
}
