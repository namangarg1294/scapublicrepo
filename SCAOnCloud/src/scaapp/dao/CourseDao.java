/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.dao;


import Pojo.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import scaapp.utils.DBConnection;



/**
 *
 * @author Comp08
 */
public class CourseDao {
    
    public static boolean addCourse(Course course)throws SQLException{
        //int count= getRowsCount()+1;
            String insertCourseQry = "Insert into course_details(course_name,no_of_lectures,course_fee) values(?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            String str=course.getCourseName();
            str=str.replaceAll("PLUS", "+");
                  ps.setString(1, str);
                  ps.setInt(2, course.getCourseLectures());
                  ps.setDouble(3, course.getCourseFee());
                 
                 int i = ps.executeUpdate();
                   if(i>0){
                    return true;
                }else{
                    return false;
                }
          }
    
    public static ArrayList<String> getCourse()throws SQLException{
            int i=1;
            ArrayList<String> courseList = new ArrayList<String>();
            String insertCourseQry = "Select course_name from course_details";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               courseList.add(rs.getString(1));
               //i++;
           }
            return courseList;    
          }
    public static ArrayList<Course> getCourseData()throws SQLException{
            
            ArrayList<Course> courseList = new ArrayList<>();
            String insertCourseQry = "Select * from course_details";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               Course crs= new Course();
               crs.setCourseId(rs.getInt(1));
               crs.setCourseName(rs.getString(2));
               crs.setCourseLectures(rs.getInt(3));
               crs.setCourseFee(rs.getDouble(4));
               courseList.add(crs);
           }
            return courseList;    
          }
        public static Course getById(int id)throws SQLException{
            String insertCourseQry = "Select * from course where course_id=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Course crs= new Course();
            while(rs.next()){
               crs.setCourseId(rs.getInt(1));
               crs.setCourseName(rs.getString(2));
               crs.setCourseLectures(rs.getInt(3));
               crs.setCourseFee(rs.getDouble(4));
           }
            return crs;    
          }
        public static boolean update(Course cd)throws SQLException{
            String insertCourseQry = "update course set course_name=?, course_lectures=?, course_fee=? where course_id=? ";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            String str=cd.getCourseName();
            str=str.replaceAll("plus", "+");
            ps.setString(1,str);
            ps.setInt(2, cd.getCourseLectures());
            ps.setDouble(3, cd.getCourseFee());
            ps.setInt(4, cd.getCourseId());
            int rs = ps.executeUpdate();
            if(rs>0)
            return true;
            else
                return false;
        
               
          }
    public static boolean delete(int id)throws SQLException{
            String insertCourseQry = "delete from course where course_id=? ";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();
            return true;
        
               
          }
    public static int getRowsCount() throws SQLException
    {
        String insertCourseQry = "Select count(*) as no from course";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ResultSet rs = ps.executeQuery();
            Course crs= new Course();
            if(rs.next()){
               return (rs.getInt(1));}
            else return 0;
    }
    public static int getLectureNo(String id) throws SQLException
    {
        String insertCourseQry = "Select course_lectures from course where course_id=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt(1);
            else return 0;
    }
     public static int getCourseFeeByBatchId(String batchId) throws SQLException
    {
        String insertCourseQry = "SELECT t1.batch_id, t2.course_fee\n" +
"FROM course_details t2\n" +
"INNER JOIN batch_details t1 ON t1.course_id = t2.course_id\n" +
"HAVING t1.batch_id =?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ps.setString(1, batchId);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return rs.getInt(2);
            else return 0;
    }
     
     public static String getCourse(int id)throws SQLException
      {
          String name="";
          String insertBatchQry = "select course_name from course_details where course_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setInt(1, id);
                  ResultSet rs=ps.executeQuery();
               if(rs.next())
                   name=rs.getString(1);
                     
                  return name;
      }
        public static int getCourseId(String name)throws SQLException
      {
         int id=0;
          String insertBatchQry = "select course_id from course_details where course_name=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, name);
                  ResultSet rs=ps.executeQuery();
               if(rs.next())
                   id=rs.getInt(1);
                     System.out.println("Id of course is "+id);
                  return id;
      }
}
