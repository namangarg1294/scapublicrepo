/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.pojo;

/**
 *
 * @author Comp8
 */
public class BatchDetailsDisplay {

    @Override
    public String toString() {
        return "BatchDetailsDisplay{" + "courseName=" + courseName + ", facultyName=" + facultyName + ", batchTime=" + batchTime + ", batchDays=" + batchDays + '}';
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }

    public String getBatchDays() {
        return batchDays;
    }

    public void setBatchDays(String batchDays) {
        this.batchDays = batchDays;
    }
    String courseName;
    String facultyName;
    String batchTime;
    String batchDays;
    String startingDate;

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }
    int fee;

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
    int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
            
}
