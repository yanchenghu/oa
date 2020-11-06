package com.ruoyi.resume.domain;

/**
 接收简历学历经验通用类
 *@author ych
 * */
public class GenEducationExperience {
    private String start_time_year;
    private String start_time_month;
    private String end_time_year;
    private String end_time_month;
    private Integer still_active;
    private String school_name;
    private String school_level;
    private String study_model;
    private String location;
    private String degree;
    private String major;
    private String GPA;
    private String ranking;
    private String courses;
    private String department;
    private String school_rank;
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
    public String getSchool_name() {
        return school_name;
    }
    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }
    public String getSchool_level() {
        return school_level;
    }
    public void setSchool_level(String school_level) {
        this.school_level = school_level;
    }
    public String getStudy_model() {
        return study_model;
    }
    public void setStudy_model(String study_model) {
        this.study_model = study_model;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public String getGPA() {
        return GPA;
    }
    public void setGPA(String gPA) {
        GPA = gPA;
    }
    public String getRanking() {
        return ranking;
    }
    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
    public String getCourses() {
        return courses;
    }
    public void setCourses(String courses) {
        this.courses = courses;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getSchool_rank() {
        return school_rank;
    }
    public void setSchool_rank(String school_rank) {
        this.school_rank = school_rank;
    }
}
