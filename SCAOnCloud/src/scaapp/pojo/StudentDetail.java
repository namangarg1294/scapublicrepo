/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.pojo;

import java.util.Date;

/**
 *
 * @author Comp08
 */
public class StudentDetail {

    @Override
    public String toString() {
        return "StudentDetail{" + "studentName=" + studentName + ", studentContact=" + studentContact + ", fatherContact=" + fatherContact + ", studentEmail=" + studentEmail + ", studentCollege=" + studentCollage + ", studentSem=" + studentSem + ", studentId=" + studentId + ", permanentAddress=" + permanentAddress + ", currentAddress=" + currentAddress + ", registrationDate=" + registrationDate + '}';
    }

    public StudentDetail(String studentName, String studentContact, String fatherContact, String studentEmail, String studentCollege, String studentSem, String studentId, String permanentAddress, String currentAddress, Date registrationDate) {
        this.studentName = studentName;
        this.studentContact = studentContact;
        this.fatherContact = fatherContact;
        this.studentEmail = studentEmail;
        this.studentCollage = studentCollege;
        this.studentSem = studentSem;
        this.studentId = studentId;
        this.permanentAddress = permanentAddress;
        this.currentAddress = currentAddress;
        this.registrationDate = registrationDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getFatherContact() {
        return fatherContact;
    }

    public void setFatherContact(String fatherContact) {
        this.fatherContact = fatherContact;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentCollege() {
        return studentCollage;
    }

    public void setStudentCollege(String studentCollege) {
        this.studentCollage = studentCollege;
    }

    public String getStudentSem() {
        return studentSem;
    }

    public void setStudentSem(String studentSem) {
        this.studentSem = studentSem;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
   
    String studentName;
    String studentContact;
    String fatherContact;
    String studentEmail;
    String studentCollage;
    String studentSem;
    String studentId;
    String permanentAddress;
    String currentAddress;
    Date registrationDate;

    
    public StudentDetail() {
    }

    public void setStudentDetails(String name, String email, String collage, String sem) {
       studentName=name;
       studentEmail=email;
       studentCollage=collage;
       studentSem=sem;
       
       
    }

}
