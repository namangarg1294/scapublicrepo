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
public class MyPayement {

    @Override
    public String toString() {
            return studentName + "          " + studentContactNo + "        " + batchId + "         " + grossAmount;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentContactNo() {
        return studentContactNo;
    }

    public void setStudentContactNo(String studentContactNo) {
        this.studentContactNo = studentContactNo;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

   
    String studentName;
    String studentContactNo;
    String batchId;
    double grossAmount;
    String email;
    double courseFee;

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    
}
