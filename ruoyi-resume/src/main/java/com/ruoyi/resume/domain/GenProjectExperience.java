package com.ruoyi.resume.domain;

/**
 接收简历项目经验通用类
 *@author ych
 * */
public class GenProjectExperience {


    private String start_time_year;
    private String start_time_month;
    private String end_time_year;
    private String end_time_month;
    private Integer still_active;
    private String project_name;
    private String location;
    private String job_title;
    private String description;
    public String getStart_time_year() {
        return start_time_year;
    }
    public void setStart_time_year(String start_time_year) {
        this.start_time_year = start_time_year;
    }
    public String getStart_time_month() {
        return start_time_month;
    }
    public void setStart_time_month(String start_time_month) {
        this.start_time_month = start_time_month;
    }
    public String getEnd_time_year() {
        return end_time_year;
    }
    public void setEnd_time_year(String end_time_year) {
        this.end_time_year = end_time_year;
    }
    public String getEnd_time_month() {
        return end_time_month;
    }
    public void setEnd_time_month(String end_time_month) {
        this.end_time_month = end_time_month;
    }
    public Integer getStill_active() {
        return still_active;
    }
    public void setStill_active(Integer still_active) {
        this.still_active = still_active;
    }
    public String getProject_name() {
        return project_name;
    }
    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getJob_title() {
        return job_title;
    }
    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
