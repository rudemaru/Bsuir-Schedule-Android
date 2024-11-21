package com.example.bsuir_schedule_app.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class WeekSchedule {
    @SerializedName("Понедельник")
    private ArrayList<LessonInfo> monday;

    @SerializedName("Вторник")
    private ArrayList<LessonInfo> tuesday;

    @SerializedName("Среда")
    private ArrayList<LessonInfo> wednesday;

    @SerializedName("Четверг")
    private ArrayList<LessonInfo> thursday;

    @SerializedName("Пятница")
    private ArrayList<LessonInfo> friday;

    @SerializedName("Суббота")
    private ArrayList<LessonInfo> saturday;

    public WeekSchedule() {}

    public WeekSchedule(ArrayList<LessonInfo> monday, ArrayList<LessonInfo> tuesday,
                        ArrayList<LessonInfo> wednesday, ArrayList<LessonInfo> thursday,
                        ArrayList<LessonInfo> friday, ArrayList<LessonInfo> saturday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }

    public ArrayList<LessonInfo> getMonday() {
        return monday;
    }

    public void setMonday(ArrayList<LessonInfo> monday) {
        this.monday = monday;
    }

    public ArrayList<LessonInfo> getTuesday() {
        return tuesday;
    }

    public void setTuesday(ArrayList<LessonInfo> tuesday) {
        this.tuesday = tuesday;
    }

    public ArrayList<LessonInfo> getWednesday() {
        return wednesday;
    }

    public void setWednesday(ArrayList<LessonInfo> wednesday) {
        this.wednesday = wednesday;
    }

    public ArrayList<LessonInfo> getThursday() {
        return thursday;
    }

    public void setThursday(ArrayList<LessonInfo> thursday) {
        this.thursday = thursday;
    }

    public ArrayList<LessonInfo> getFriday() {
        return friday;
    }

    public void setFriday(ArrayList<LessonInfo> friday) {
        this.friday = friday;
    }

    public ArrayList<LessonInfo> getSaturday() {
        return saturday;
    }

    public void setSaturday(ArrayList<LessonInfo> saturday) {
        this.saturday = saturday;
    }
}