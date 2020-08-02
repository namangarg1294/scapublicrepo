/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import scaapp.pojo.StudentTransaction;
import scaapp.utils.DBConnection;

/**
 *
 * @author Comp8
 */
public class AttendanceDao {
       public static int addAttendence(String sId,String batchId,String date)throws SQLException{
         
            String insertStudentDetailsQry = "Insert into attendance_details values(?,?,?)";

            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertStudentDetailsQry);
                  ps.setString(1, sId);
                  ps.setString(2, batchId);
                  ps.setString(3, date);
                  
                 int i = ps.executeUpdate();
                 setClassHeld(batchId,date);
                  return i;
     }
       
       public static ArrayList<String> getStudentAttendanceBatchWise(String contact_no,String batchId)throws SQLException{
         String getQry="select date from attendance_details  where student_id=? and batch_id = ? ";
         //int studentDetailId=0;
           ArrayList<String> list=new ArrayList<>();
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getQry);
           ps.setString(1,contact_no);
           ps.setString(2,batchId);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              
             list.add(rs.getString(1));
           }
           return list;
     }
       public static void  setClassHeld(String batchId,String date){
               String insertStudentDetailsQry = "Insert into class_held_record values(?,?)";
                try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertStudentDetailsQry);
                  
                  ps.setString(1, batchId);
                  ps.setString(2, date);
                  
                 int i = ps.executeUpdate();
                  }
                catch(Exception e){
                    
                }


       }
       public static int  getClassHeld(String batchId)throws SQLException{
               String selectQry = "select count(*) from class_held_record where batch_id=?";
                 ArrayList<String> list=new ArrayList<>();
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
                  
                  ps.setString(1, batchId);
                  
                  int i=0;
                 ResultSet rs=ps.executeQuery();
          if(rs.next()){
              
            i=rs.getInt(1);
           }
           return i;
                  
              


       }
       
}
