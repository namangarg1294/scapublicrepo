/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author Comp08
 */
public class Course {

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    int courseId;
    String courseName;
    int courseLectures;
    double courseFee;
    int bookId;

    public Course() {
    }
    String courseContent;

    public Course(String courseName, int courseLectures, double courseFee) {
        this.courseName = courseName;
        this.courseLectures = courseLectures;
        this.courseFee = courseFee;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseLectures() {
        return courseLectures;
    }

    public void setCourseLectures(int courseLectures) {
        this.courseLectures = courseLectures;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public String getCourseContent() {
        return courseContent;
    }

    public void setCourseContent(String courseContent) {
        this.courseContent = courseContent;
    }
   
}
