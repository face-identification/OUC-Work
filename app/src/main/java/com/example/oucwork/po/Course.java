package com.example.oucwork.po;

/**
 * 课程信息类
 */

public class Course {
    int id;
    String year;    // 学年
    String term;    // 课程所在学期
    String name;
    String teacher;
    String site;
    String week;
    int start_time;
    int end_time;
    int start_week;
    int  end_week;

    public Course(){

    }

    public Course(int id, String year, String term, String name, String teacher, String site, String week, int start_time, int end_time, int start_week, int end_week){
        this.id = id;
        this.year = year;
        this.term = term;
        this.name = name;
        this.teacher = teacher;
        this.site = site;
        this.week = week;
        this.start_time = start_time;
        this.end_time = end_time;
        this.start_week = start_week;
        this.end_week = end_week;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public int getStart_week() {
        return start_week;
    }

    public void setStart_week(int start_week) {
        this.start_week = start_week;
    }

    public int getEnd_week() {
        return end_week;
    }

    public void setEnd_week(int end_week) {
        this.end_week = end_week;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", year='" + year + '\'' +
                ", term='" + term + '\'' +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", site='" + site + '\'' +
                ", week='" + week + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", start_week=" + start_week +
                ", end_week=" + end_week +
                '}';
    }

}
