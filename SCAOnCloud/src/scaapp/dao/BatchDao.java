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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import scaapp.pojo.BatchDetails;
import scaapp.pojo.BatchDetailsDisplay;

import scaapp.pojo.ShowBatch;
import scaapp.utils.DBConnection;

/**
 *
 * @author Comp08
 */
public class BatchDao {
    
      public static boolean addBatch(BatchDetails batch)throws SQLException{
         
            String insertBatchQry = "INSERT INTO `batch_details`(`batch_id`,`faculty_id`, `course_id`, `centre_id`, `days`, `time`, `starting_date`) VALUES (?,?,?,?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
                  ps.setString(1, batch.getBatch_id());
                  ps.setInt(2, batch.getBatch_faculty());
                 // ps.setString(3, batch.getSession_id());
                  ps.setInt(3,batch.getBatch_course());
                  ps.setInt(4, batch.getBatch_centre());
                  ps.setString(5, batch.getBatch_days());
                  ps.setString(6, batch.getBatch_timming());
                  ps.setString(7,batch.getBatch_start_date() );
                  
                  
                 int i = ps.executeUpdate();
                   if(i>0){
                    return true;
                }else{
                    return false;
                }
          }
      public static int getCount() throws SQLException
      { 
          int count=0;
          String insertBatchQry = "select count(*) from batch_details";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
                 // ps.setInt(1, id);
                  ResultSet rs=ps.executeQuery();
                 if(rs.next())
                  {
                      count=rs.getInt(1);
                  }
                  return count+1;
      }
      public static ArrayList<String> getTime(int course)throws SQLException
      {
          ArrayList<String> time=new ArrayList<>();
          String insertBatchQry = "select batch_timming from batch where course_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setInt(1, course);
                  ResultSet rs=ps.executeQuery();
                  while(rs.next())
                      time.add(rs.getString(1));
                  if(time.isEmpty())
                      time.add("No Batch Available For This Course Now");
                  return time;
      }
      public static ArrayList<String> getDate(int course, String tim)throws SQLException
      {
          ArrayList<String> datee=new ArrayList<>();
          String insertBatchQry = "select batch_start_date from batch where course_id=? and batch_timming=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setInt(1, course);
            ps.setString(2, tim);
                  ResultSet rs=ps.executeQuery();
                  while(rs.next())
                      datee.add(rs.getString(1));
                  if(datee.isEmpty())
                      datee.add("No Batch Available For This Time Now");
                  return datee;
      }
      public static boolean getId(String id) throws SQLException
      {
          String insertBatchQry = "select * from batch where batch_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
                return true;
            else
                return false;
      }
      public static ArrayList<String> getBatch(int course)throws SQLException, ParseException
      {
          Date today=new Date();
         long l =today.getTime()-31556952000l;
          ArrayList<String> batch=new ArrayList<>();
          String insertBatchQry = "select batch_start_date, batch_id, batch_timming from batch where course_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setInt(1, course);
                  ResultSet rs=ps.executeQuery();
                  while(rs.next())
                  {
                       SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                        Date dt=sdf.parse(rs.getString(1));
                        Long time=dt.getTime();
                        if(time>l)
                        { batch.add(rs.getString(2)+"  |  "+rs.getString(3));}
                  }
                  if(batch.isEmpty())
                      batch.add("No Batch Available This Time Now");
                  return batch;
      }
      public static ArrayList<ShowBatch> showRecord() throws SQLException
      {   
          ArrayList<ShowBatch> sb=new ArrayList<>();
          String insertBatchQry = "SELECT b.batch_id, (SELECT emp_name FROM employee_details WHERE emp_id = b.faculty_id) as faculty, (SELECT c.course_name "
                  + "FROM course_details as c WHERE c.course_id = b.course_id) "
                  + "as course, (SELECT ct.centre_name FROM centre_details as ct "
                  + "WHERE ct.centre_id = b.centre_id) as centre, (SELECT COUNT(DISTINCT "
                  + "t.student_id) FROM student_transaction as t WHERE b.batch_id = t.batch_id) "
                  + "as students, (SELECT SUM(p.gross_amount) FROM student_transaction as p "
                  + "WHERE b.batch_id = p.batch_id) as fee, b.starting_date, b.time, b.days "
                  + "FROM batch_details AS b where STR_TO_DATE(b.starting_date, '%d-%m-%Y')"
                  + ">STR_TO_DATE(?, '%Y-%m-%d')";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                ShowBatch bs=new ShowBatch();
                bs.setBatchid(rs.getString(1)); 
                bs.setStart(rs.getString(4));
                bs.setTimming(rs.getString(3));
                bs.setDays(rs.getString(2));
                
                bs.setCourse(rs.getString(6));
                bs.setFaculty(rs.getString(5));
                sb.add(bs);
            }
            return sb;
      }
     
      public static BatchDetailsDisplay showRecord(String batchId) throws SQLException
      {   
          ArrayList<ShowBatch> sb=new ArrayList<>();
          String showBatchQry = "SELECT t2.course_name, t3.emp_name, t1.time,t1.days,t1.is_active,t2.course_fee,t1.starting_date \n" +
"FROM batch_details t1\n" +
"INNER JOIN course_details t2 ON t1.course_id = t2.course_id\n" +
"INNER JOIN employee_details t3 ON t1.faculty_id = t3.emp_id\n" +
"WHERE batch_id =?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(showBatchQry);
            ps.setString(1, batchId);
            ps.setQueryTimeout(5);
            ResultSet rs=ps.executeQuery();
            BatchDetailsDisplay bdd=null;
            if(rs.next())
            {
               bdd=new BatchDetailsDisplay();
               bdd.setFacultyName(rs.getString(2));
               bdd.setBatchTime(rs.getString(3));
               bdd.setCourseName(rs.getString(1));
                bdd.setBatchDays(rs.getString(4));
                bdd.setActive(rs.getInt(5));
                bdd.setFee(rs.getInt(6));
                bdd.setStartingDate(rs.getString(7));
               //sb.add(bs);
            }
            return bdd;
      }
        public static ArrayList<String> getAllBatchId() throws SQLException
      {   
          ArrayList<String> sb=new ArrayList<>();
          String insertBatchQry = "select batch_id from batch_details";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               
                sb.add(rs.getString(1));
            }
            return sb;
      }
        
        public static ArrayList<String> getAllBatchId(int centreId) throws SQLException
      {   
          ArrayList<String> sb=new ArrayList<>();
          String insertBatchQry = "select batch_id from batch_details where centre_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
        ps.setInt(1, centreId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               
                sb.add(rs.getString(1));
            }
            return sb;
      }
        public static ArrayList<ShowBatch> getAllBatches(int centreId) throws SQLException
      {   
          ArrayList<ShowBatch> sb=new ArrayList<>();
          String insertBatchQry = "select * from batch_details where centre_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
        ps.setInt(1, centreId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                ShowBatch sbz=new ShowBatch();
                sbz.setBatchid(rs.getString(1));
                sbz.setStart(rs.getString(7));
                        
               
                sb.add(sbz);
            }
            return sb;
      }
          public static ArrayList<String> getAllActiveBatchId(int centreId) throws SQLException
      {   
          ArrayList<String> sb=new ArrayList<>();
          String insertBatchQry = "select batch_id from batch_details where centre_id=? and is_active=1";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
        ps.setInt(1, centreId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               
                sb.add(rs.getString(1));
            }
            return sb;
      }
      public static ArrayList<String> getAllBatchIdOfSingleStudent(String sId) throws SQLException
      {   
          ArrayList<String> sb=new ArrayList<>();
          String insertBatchQry = "select batch_id from batch_details where student_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, sId);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               
                sb.add(rs.getInt(1)+"");
            }
            return sb;
      }
      
      public static boolean updateBatch(BatchDetails batch)throws SQLException{
         
            String insertBatchQry = "update batch set batch_category_id=? ,batch_name=? ,course_id=? ,batch_start_date=? ,batch_timming=?, faculty_id=?, batch_centre=?, batch_days=? where batch_id=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
                  ps.setInt(1, batch.getBatch_category_id());
                  ps.setString(2, batch.getBatch_name());
                  ps.setInt(3, batch.getBatch_course());
                  ps.setString(4, batch.getBatch_start_date());
                  ps.setString(5, batch.getBatch_timming());
                  ps.setInt(6, batch.getBatch_faculty());
                  ps.setInt(7, batch.getBatch_centre());
                  ps.setString(8, batch.getBatch_days());
                  ps.setString(9, batch.getBatch_id());
                 int i = ps.executeUpdate();
                   if(i>0){
                    return true;
                }else{
                    return false;
                }
          } 
      public static boolean delete(String code)throws SQLException{
            String insertCourseQry = "delete from batch where batch_id=? ";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ps.setString(1, code);
            int rs = ps.executeUpdate();
            return true;
        
               
          }
      public static String getCourse(String id) throws SQLException{
          String insertBatchQry = "select batch_name from batch where batch_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
                return rs.getString(1);
            else
                return null;
      }
        public static boolean isActive(String id) throws SQLException{
          String insertBatchQry = "select is_active from batch_details where batch_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
                return rs.getInt(1)==1;
            else
                return false;
      }
      public static boolean changeActiveStatus(String batch,int code) throws SQLException{
          String insertBatchQry = "update batch_details set is_active=? where batch_id=? ";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setInt(1, code);
            ps.setString(2, batch);
             int i = ps.executeUpdate();
                   if(i>0){
                    return true;
                }else{
                    return false;
                }
      }
      public static boolean changeBatch(String oldBatchId,String newBatchId,String studentId) throws SQLException{
          String insertBatchQry = "update student_registration set batch_id=? where batch_id=? and student_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, newBatchId);
            ps.setString(2, oldBatchId);
             ps.setString(3, studentId);
             int i = ps.executeUpdate();
                   insertBatchQry = "update student_transaction set batch_id=? where batch_id=? and student_id=?";
          conn = DBConnection.getConnection();
           ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, newBatchId);
            ps.setString(2, oldBatchId);
             ps.setString(3, studentId);
            ps.executeUpdate();
            return true;
      }
      
}
