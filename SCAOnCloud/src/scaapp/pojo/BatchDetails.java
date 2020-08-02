/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.pojo;

/**
 *
 * @author Comp08
 */
public class BatchDetails {

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public BatchDetails() {
    }

    public BatchDetails(String batch_id, String batch_name, int batch_course, String batch_start_date, String batch_timming, int batch_faculty, int batch_centre, String session_id) {
        this.batch_id = batch_id;
        this.batch_name = batch_name;
        this.batch_course = batch_course;
        this.batch_start_date = batch_start_date;
        this.batch_timming = batch_timming;
        this.batch_faculty = batch_faculty;
        this.batch_centre = batch_centre;
        this.session_id = session_id;
    }

    public String getBatch_days() {
        return batch_days;
    }

    public void setBatch_days(String batch_days) {
        this.batch_days = batch_days;
    }
    
    String batch_id;
    int batch_category_id;
    String batch_name;
    int batch_course;
    String batch_start_date;
    String batch_timming;
    int batch_faculty;
    int batch_centre;
    String batch_days;
String session_id;
   

    public String getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public int getBatch_category_id() {
        return batch_category_id;
    }

    public void setBatch_category_id(int batch_category_id) {
        this.batch_category_id = batch_category_id;
    }

    public String getBatch_name() {
        return batch_name;
    }

    public void setBatch_name(String batch_name) {
        this.batch_name = batch_name;
    }

    public int getBatch_course() {
        return batch_course;
    }

    public void setBatch_course(int batch_course) {
        this.batch_course = batch_course;
    }

    public String getBatch_start_date() {
        return batch_start_date;
    }

    public void setBatch_start_date(String batch_start_date) {
        this.batch_start_date = batch_start_date;
    }

    public String getBatch_timming() {
        return batch_timming;
    }

    public void setBatch_timming(String batch_timming) {
        this.batch_timming = batch_timming;
    }

    public int getBatch_centre() {
        return batch_centre;
    }

    public void setBatch_centre(int batch_centre) {
        this.batch_centre = batch_centre;
    }

    public int getBatch_faculty() {
        return batch_faculty;
    }

    public void setBatch_faculty(int batch_faculty) {
        this.batch_faculty = batch_faculty;
    }
   
    
}
