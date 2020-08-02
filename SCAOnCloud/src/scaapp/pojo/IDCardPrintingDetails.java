/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.pojo;

import java.util.Objects;

/**
 *
 * @author Comp8
 */
public class IDCardPrintingDetails {

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.batchId);
        hash = 17 * hash + Objects.hashCode(this.studentId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IDCardPrintingDetails other = (IDCardPrintingDetails) obj;
        if (!Objects.equals(this.batchId, other.batchId)) {
            return false;
        }
        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }
        return true;
    }

    public IDCardPrintingDetails(String studentName, String courseName, String batchTime, String session, String batchId, String studentId, String centre) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.batchTime = batchTime;
        this.session = session;
        this.batchId = batchId;
        this.studentId = studentId;
        this.centre = centre;
    }

    public IDCardPrintingDetails() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getBatchTime() {
        return batchTime;
    }

    public void setBatchTime(String batchTime) {
        this.batchTime = batchTime;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }
    String studentName;
    String courseName;
    String batchTime;
    String session;
    String batchId;
    String studentId;
    String centre;
    String email;
    String days;
    String sem;

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    String actualBatch;

    public String getActualBatch() {
        return actualBatch;
    }

    public void setActualBatch(String actualBatch) {
        this.actualBatch = actualBatch;
    }
}
