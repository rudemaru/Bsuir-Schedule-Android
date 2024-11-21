package com.example.bsuir_schedule_app.model;

import com.google.gson.annotations.SerializedName;

public class Schedule {
    @SerializedName("schedules")
    private WeekSchedule schedules;

    public Schedule() {}

    public Schedule(WeekSchedule schedules) {
        this.schedules = schedules;
    }

    public WeekSchedule getSchedules() {
        return schedules;
    }

    public void setSchedules(WeekSchedule schedules) {
        this.schedules = schedules;
    }
}