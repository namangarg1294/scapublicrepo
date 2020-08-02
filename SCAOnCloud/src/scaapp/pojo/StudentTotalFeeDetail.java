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
public class StudentTotalFeeDetail {

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }
    String studentId;
    String batchId;
    double totalAmount;
    double leftFee;

    public double getLeftFee() {
        return leftFee;
    }

    public void setLeftFee(double leftFee) {
        this.leftFee = leftFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    String Course;
    String batchStartingDate;

    public String getBatchStartingDate() {
        return batchStartingDate;
    }

    public void setBatchStartingDate(String batchStartingDate) {
        this.batchStartingDate = batchStartingDate;
    }
}
