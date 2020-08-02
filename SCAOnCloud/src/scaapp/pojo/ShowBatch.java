/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.pojo;

/**
 *
 * @author Clay
 */
public class ShowBatch {
    String batchid,batchname,timming,start,faculty,center,days,category,course;
    int students;

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
    int fee;
    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public String getTimming() {
        return timming;
    }

    public void setTimming(String timming) {
        this.timming = timming;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
