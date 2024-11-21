package com.example.bsuir_schedule_app.model.dto;

import com.google.gson.annotations.SerializedName;

public class GroupDto {
    @SerializedName("name")
    private String name;

    @SerializedName("facultyAbbrev")
    private String faculty;

    @SerializedName("specialityName")
    private String speciality;

    public GroupDto() {}

    public GroupDto(String name, String faculty, String speciality) {
        this.name = name;
        this.faculty = faculty;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}