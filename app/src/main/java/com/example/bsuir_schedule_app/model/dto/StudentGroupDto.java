package com.example.bsuir_schedule_app.model.dto;

import com.google.gson.annotations.SerializedName;

public class StudentGroupDto {
    @SerializedName("studentGroupDto")
    private GroupDto groupDto;

    public StudentGroupDto() {}

    public StudentGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
    }

    public GroupDto getGroupDto() {
        return groupDto;
    }

    public void setGroupDto(GroupDto groupDto) {
        this.groupDto = groupDto;
    }
}