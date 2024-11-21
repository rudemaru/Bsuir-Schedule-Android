package com.example.bsuir_schedule_app.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LessonInfo {
    @SerializedName("announcement")
    public Boolean announcement;

    @SerializedName("weekNumber")
    public List<Integer> weekNumber;

    @SerializedName("numSubgroup")
    public Integer numSubgroup;

    @SerializedName("auditories")
    public List<String> auditories;

    @SerializedName("startLessonTime")
    public String startLessonTime;

    @SerializedName("endLessonTime")
    public String endLessonTime;

    @SerializedName("subject")
    public String subject;

    @SerializedName("subjectFullName")
    public String subjectFullName;

    @SerializedName("lessonTypeAbbrev")
    public String lessonTypeAbbrev;

    @SerializedName("split")
    public Boolean split;

    public LessonInfo() {}

    public LessonInfo(Boolean announcement, List<Integer> weekNumber, Integer numSubgroup,
                      List<String> auditories, String startLessonTime, String endLessonTime,
                      String subject, String subjectFullName, String lessonTypeAbbrev,
                      Boolean split) {
        this.announcement = announcement;
        this.weekNumber = weekNumber;
        this.numSubgroup = numSubgroup;
        this.auditories = auditories;
        this.startLessonTime = startLessonTime;
        this.endLessonTime = endLessonTime;
        this.subject = subject;
        this.subjectFullName = subjectFullName;
        this.lessonTypeAbbrev = lessonTypeAbbrev;
        this.split = split;
    }

    public Boolean getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Boolean announcement) {
        this.announcement = announcement;
    }

    public List<Integer> getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(List<Integer> weekNumber) {
        this.weekNumber = weekNumber;
    }

    public Integer getNumSubgroup() {
        return numSubgroup;
    }

    public void setNumSubgroup(Integer numSubgroup) {
        this.numSubgroup = numSubgroup;
    }

    public List<String> getAuditories() {
        return auditories;
    }

    public void setAuditories(List<String> auditories) {
        this.auditories = auditories;
    }

    public String getStartLessonTime() {
        return startLessonTime;
    }

    public void setStartLessonTime(String startLessonTime) {
        this.startLessonTime = startLessonTime;
    }

    public String getEndLessonTime() {
        return endLessonTime;
    }

    public void setEndLessonTime(String endLessonTime) {
        this.endLessonTime = endLessonTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectFullName() {
        return subjectFullName;
    }

    public void setSubjectFullName(String subjectFullName) {
        this.subjectFullName = subjectFullName;
    }

    public String getLessonTypeAbbrev() {
        return lessonTypeAbbrev;
    }

    public void setLessonTypeAbbrev(String lessonTypeAbbrev) {
        this.lessonTypeAbbrev = lessonTypeAbbrev;
    }

    public Boolean getSplit() {
        return split;
    }

    public void setSplit(Boolean split) {
        this.split = split;
    }

    public String getStartLessonTimeForSorting() {
        return startLessonTime;
    }
}