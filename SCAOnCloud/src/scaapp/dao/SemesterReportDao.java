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
import java.util.HashMap;
import scaapp.pojo.MyPayement;
import scaapp.pojo.ShowBatch;
import scaapp.pojo.StudentTransaction;
import scaapp.utils.DBConnection;

/**
 *
 * @author Naman
 */
public class SemesterReportDao {
    
       public static int getTotalCollectedFee(String startDate,String endDate)throws SQLException{
         String getStudentTransactionDetailsQry = "SELECT sum(`gross_amount`) FROM `student_transaction` "
                 + "WHERE `date_of_payement` BETWEEN ? and ?";
         //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
         public static int getTotalCollectedFee(String startDate,String endDate,int centre)throws SQLException{
         String getStudentTransactionDetailsQry = "SELECT sum(`gross_amount`) FROM `student_transaction` "
                 + "WHERE `date_of_payement` BETWEEN ? and ? and centre_id=?";
         //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
           ps.setInt(3,centre);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
           public static int getTotalCollectedFee(String startDate,String endDate,int centre,int eid)throws SQLException{
         String getStudentTransactionDetailsQry =  "SELECT sum(`gross_amount`) FROM `student_transaction` "
                 + "t1 INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                 + "WHERE `date_of_payement` BETWEEN ? and ? and t1.centre_id=? and t3.faculty_id=?";
         //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
           ps.setInt(3,centre);
           ps.setInt(4,eid);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
           
            public static int getTotalCollectedFeeBothCentre(String startDate,String endDate,int eid)throws SQLException{
         String getStudentTransactionDetailsQry =  "SELECT sum(`gross_amount`) FROM `student_transaction` "
                 + "t1 INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                 + "WHERE `date_of_payement` BETWEEN ? and ? and t3.faculty_id=?";
         //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
           ps.setInt(3,eid);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     } 
        public static int getTotalLeftFee(String startDate,String endDate)throws SQLException{
            String getStudentTransactionDetailsQry = "select sum(x) from (SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement FROM "
                    + "student_transaction t1 INNER JOIN student_details t2 ON t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                    + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id HAVING SUM(gross_amount )<t4.course_fee and t1.date_of_payement "
                    + "between ? and ? ) as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
         public static int getTotalLeftFee(String startDate,String endDate,int centre)throws SQLException{
            String getStudentTransactionDetailsQry = "select sum(x) from (SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t1.centre_id FROM "
                    + "student_transaction t1 INNER JOIN student_details t2 ON t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                    + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id HAVING SUM(gross_amount )<t4.course_fee and t1.date_of_payement "
                    + "between ? and ? and t1.centre_id=? ) as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
           ps.setInt(3,centre);
           ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
                public static int getTotalLeftFee(String startDate,String endDate,int centre,int eid)throws SQLException{
            String getStudentTransactionDetailsQry = "select sum(x) from (SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t1.centre_id,t3.faculty_id FROM "
                    + "student_transaction t1 INNER JOIN student_details t2 ON t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                    + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id HAVING SUM(gross_amount )<t4.course_fee and t1.date_of_payement "
                    + "between ? and ? and t1.centre_id=? and t3.faculty_id=? ) as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
           ps.setInt(3,centre);
            ps.setInt(4,eid);
           ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
                
        public static int getTotalLeftFeeBothCentre(String startDate,String endDate,int eid)throws SQLException{
            String getStudentTransactionDetailsQry = "select sum(x) from (SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t1.centre_id,t3.faculty_id FROM "
                    + "student_transaction t1 INNER JOIN student_details t2 ON t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                    + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id HAVING SUM(gross_amount )<t4.course_fee and t1.date_of_payement "
                    + "between ? and ? and t3.faculty_id=? ) as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setInt(3,eid);
           ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }        
         public static int getTotalStudents(String startDate,String endDate)throws SQLException{
            String getStudentTransactionDetailsQry = "SELECT count(*) FROM `student_registration` WHERE "
                    + "`registration_date` BETWEEN ? and ?";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
           public static int getTotalStudents(String startDate,String endDate,int centre)throws SQLException{
            String getStudentTransactionDetailsQry = "SELECT count(*) FROM `student_registration` WHERE "
                    + "`registration_date` BETWEEN ? and ? and batch_id like ?";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
           if(centre==1)
               ps.setString(3, "M%");
            else
               ps.setString(3, "I%");
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
             public static int getTotalStudents(String startDate,String endDate,int centre,int eid)throws SQLException{
            String getStudentTransactionDetailsQry = "SELECT count(*) FROM `student_registration` t1 "
                    + "INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id WHERE "
                    + "`registration_date` BETWEEN ? and ? and t1.batch_id like ? and t3.faculty_id=?";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
           
           if(centre==1)
               ps.setString(3, "M%");
            else
               ps.setString(3, "I%");
           ps.setInt(4, eid);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
              public static int getTotalStudentsBothCentre(String startDate,String endDate,int eid)throws SQLException{
            String getStudentTransactionDetailsQry = "SELECT count(*) FROM `student_registration` t1 "
                    + "INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id WHERE "
                    + "`registration_date` BETWEEN ? and ? and t3.faculty_id=?";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
             ps.setInt(3, eid);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
          public static int getTotalStudentsWhoPaid100Rs(String startDate,String endDate)throws SQLException{
            String getStudentTransactionDetailsQry = "select count(x) from (SELECT t2.student_name, t2.student_contact_no as x, t1.batch_id, "
                    + "SUM( t1.gross_amount ),t2.student_email_id, t1.date_of_payement FROM student_transaction t1 "
                    + "INNER JOIN student_details t2 ON t1.student_id = t2.student_contact_no GROUP BY t1.batch_id, t1.student_id HAVING SUM( gross_amount ) <101 "
                    + "and t1.date_of_payement between ? and ?) as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
             public static int getTotalStudentsWhoPaid100Rs(String startDate,String endDate,int centre)throws SQLException{
            String getStudentTransactionDetailsQry = "select count(x) from (SELECT t2.student_name, t2.student_contact_no as x, t1.batch_id, "
                    + "SUM( t1.gross_amount ),t2.student_email_id, t1.date_of_payement, t1.centre_id FROM student_transaction t1 "
                    + "INNER JOIN student_details t2 ON t1.student_id = t2.student_contact_no GROUP BY t1.batch_id, t1.student_id HAVING SUM( gross_amount ) <101 "
                    + "and t1.date_of_payement between ? and ? and t1.centre_id=?) as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setInt(3,centre);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
               public static int getTotalStudentsWhoPaid100Rs(String startDate,String endDate,int centre,int eid)throws SQLException{
            String getStudentTransactionDetailsQry = "select count(x) from (SELECT t2.student_name, t2.student_contact_no as x, "
                    + "t1.batch_id,t3.faculty_id, "
                    + "SUM( t1.gross_amount ),t2.student_email_id, t1.date_of_payement, t1.centre_id FROM student_transaction t1 "
                    + "INNER JOIN student_details t2 ON t1.student_id = t2.student_contact_no INNER JOIN batch_details t3 "
                    + "on t1.batch_id=t3.batch_id GROUP BY t1.batch_id, t1.student_id HAVING SUM( gross_amount ) <101 "
                    + "and t1.date_of_payement between ? and ? and t1.centre_id=? and t3.faculty_id=?) as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setInt(3,centre);
            ps.setInt(4, eid);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
     public static int getTotalStudentsWhoPaid100RsBothCentre(String startDate,String endDate,int eid)throws SQLException{
            String getStudentTransactionDetailsQry = "select count(x) from (SELECT t2.student_name, t2.student_contact_no as x, "
                    + "t1.batch_id,t3.faculty_id, "
                    + "SUM( t1.gross_amount ),t2.student_email_id, t1.date_of_payement, t1.centre_id FROM student_transaction t1 "
                    + "INNER JOIN student_details t2 ON t1.student_id = t2.student_contact_no INNER JOIN batch_details t3 "
                    + "on t1.batch_id=t3.batch_id GROUP BY t1.batch_id, t1.student_id HAVING SUM( gross_amount ) <101 "
                    + "and t1.date_of_payement between ? and ?  and t3.faculty_id=?) as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setInt(3, eid);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
               public static int getTotalStudentsWhoHaveNotCompletedFee(String startDate,String endDate)throws SQLException{
            String getStudentTransactionDetailsQry = "select count(x) from (SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement "
                    + "FROM student_transaction t1 INNER JOIN student_details t2 ON "
                    + "t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                    + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id "
                    + "HAVING SUM(gross_amount )<t4.course_fee and t1.date_of_payement between ? and ? ) "
                    + "as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
             public static int getTotalStudentsWhoHaveNotCompletedFee(String startDate,String endDate,int centre)throws SQLException{
            String getStudentTransactionDetailsQry = "select count(x) from (SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t1.centre_id "
                    + "FROM student_transaction t1 INNER JOIN student_details t2 ON "
                    + "t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                    + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id "
                    + "HAVING SUM(gross_amount )<t4.course_fee and t1.date_of_payement between ? and ? and t1.centre_id=? ) "
                    + "as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setInt(3,centre);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
               public static int getTotalStudentsWhoHaveNotCompletedFee(String startDate,String endDate,int centre,int eid)throws SQLException{
            String getStudentTransactionDetailsQry = "select count(x) from (SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t1.centre_id,t3.faculty_id "
                    + "FROM student_transaction t1 INNER JOIN student_details t2 ON "
                    + "t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                    + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id "
                    + "HAVING SUM(gross_amount )<t4.course_fee and t1.date_of_payement between ? and ? and t1.centre_id=? and t3.faculty_id=? ) "
                    + "as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setInt(3,centre);
             ps.setInt(4,eid);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
      public static int getTotalStudentsWhoHaveNotCompletedFeeBothCentre(String startDate,String endDate,int eid)throws SQLException{
            String getStudentTransactionDetailsQry = "select count(x) from (SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t1.centre_id,t3.faculty_id "
                    + "FROM student_transaction t1 INNER JOIN student_details t2 ON "
                    + "t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                    + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id "
                    + "HAVING SUM(gross_amount )<t4.course_fee and t1.date_of_payement between ? and ? and t3.faculty_id=? ) "
                    + "as tc";
            //int studentDetailId=0;
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);   
             ps.setInt(3,eid);
            ps.setQueryTimeout(20);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              return rs.getInt(1);
           }
           return 0;
     }
       public static ArrayList<MyPayement> getPendingPayement(int eid,String startDate,String endDate)throws SQLException
      {
         ArrayList<MyPayement> list=new ArrayList<>();
          String selectQry = "SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t2.student_name,t3.faculty_id,t2.student_contact_no,t2.student_email_id,t1.batch_id FROM "
                  + "student_transaction t1 INNER JOIN student_details t2 ON t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                  + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id HAVING SUM(gross_amount )<t4.course_fee and "
                  + "t3.faculty_id=? and t1.date_of_payement between ? and ? ";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setString(2,startDate);
            ps.setString(3,endDate);
            ps.setInt(1, eid);
                  ResultSet rs=ps.executeQuery();
                  while(rs.next()){
                     MyPayement mp=new MyPayement();
                        mp.setGrossAmount(rs.getDouble(1));
                        mp.setCourseFee(rs.getDouble(2));
                         mp.setStudentName(rs.getString(4));
                     
                     
                    
                     mp.setStudentContactNo(rs.getString(6));
                  
                     mp.setBatchId(rs.getString(8));
                     mp.setEmail(rs.getString(7));
                     list.add(mp);
                      System.out.println(mp);
                  
                  }
                  
                
                  return list;
      }
       public static ArrayList<MyPayement> getPendingPayement(String startDate,String endDate)throws SQLException
      {
         ArrayList<MyPayement> list=new ArrayList<>();
          String selectQry = "SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t2.student_name,t3.faculty_id,t2.student_contact_no,t2.student_email_id,t1.batch_id FROM "
                  + "student_transaction t1 INNER JOIN student_details t2 ON t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                  + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id HAVING SUM(gross_amount )<t4.course_fee and "
                  + "t1.date_of_payement between ? and ? ";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setString(1,startDate);
            ps.setString(2,endDate);
                  ResultSet rs=ps.executeQuery();
                  while(rs.next()){
                     MyPayement mp=new MyPayement();
                        mp.setGrossAmount(rs.getDouble(1));
                        mp.setCourseFee(rs.getDouble(2));
                         mp.setStudentName(rs.getString(4));
                     
                     
                    
                     mp.setStudentContactNo(rs.getString(6));
                  
                     mp.setBatchId(rs.getString(8));
                     mp.setEmail(rs.getString(7));
                     list.add(mp);
                      System.out.println(mp);
                  
                  }
                  
                
                  return list;
      }
        public static ArrayList<MyPayement> getPendingPayementCentreWise(String startDate,String endDate,int centre)throws SQLException
      {
         ArrayList<MyPayement> list=new ArrayList<>();
          String selectQry = "SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t2.student_name,t3.faculty_id,t2.student_contact_no,t2.student_email_id,t1.batch_id,t1.centre_id FROM "
                  + "student_transaction t1 INNER JOIN student_details t2 ON t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                  + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id HAVING SUM(gross_amount )<t4.course_fee and "
                  + "t1.date_of_payement between ? and ? and t1.centre_id=? ";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setString(1,startDate);
            ps.setString(2,endDate);
             ps.setInt(3,centre);
                  ResultSet rs=ps.executeQuery();
                  while(rs.next()){
                     MyPayement mp=new MyPayement();
                        mp.setGrossAmount(rs.getDouble(1));
                        mp.setCourseFee(rs.getDouble(2));
                         mp.setStudentName(rs.getString(4));
                     
                     
                    
                     mp.setStudentContactNo(rs.getString(6));
                  
                     mp.setBatchId(rs.getString(8));
                     mp.setEmail(rs.getString(7));
                     list.add(mp);
                      System.out.println(mp);
                  
                  }
                  
                
                  return list;
      }
       public static ArrayList<MyPayement> getPendingPayementCentreWise(String startDate, String endDate, int centre, int eid) throws SQLException {
        ArrayList<MyPayement> list = new ArrayList<>();
        String selectQry = "SELECT t4.course_fee-SUM( t1.gross_amount) as x,t4.course_fee,t1.date_of_payement,t2.student_name,t3.faculty_id,t2.student_contact_no,t2.student_email_id,t1.batch_id,t1.centre_id FROM "
                + "student_transaction t1 INNER JOIN student_details t2 ON t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id "
                + "INNER join course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id HAVING SUM(gross_amount )<t4.course_fee and "
                + "t1.date_of_payement between ? and ? and t1.centre_id=? and t3.faculty_id=? ";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(selectQry);
        ps.setString(1, startDate);
        ps.setString(2, endDate);
        ps.setInt(3, centre);
        ps.setInt(4, eid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MyPayement mp = new MyPayement();
            mp.setGrossAmount(rs.getDouble(1));
            mp.setCourseFee(rs.getDouble(2));
            mp.setStudentName(rs.getString(4));
            mp.setStudentContactNo(rs.getString(6));
            mp.setBatchId(rs.getString(8));
            mp.setEmail(rs.getString(7));
            list.add(mp);
            System.out.println(mp);

        }

        return list;
    }
        public static HashMap<String,Integer> getPendingFeeBatchWise(String startDate, String endDate) throws SQLException {
        ArrayList<MyPayement> list = new ArrayList<>();
        String selectQry = "select sum(x) as Fee_left,k as batch from (SELECT t4.course_fee-SUM( t1.gross_amount) as x,"
                + "t4.course_fee,t1.date_of_payement,t1.batch_id as k FROM student_transaction t1 INNER JOIN student_details "
                + "t2 ON t1.student_id =t2.student_contact_no INNER JOIN batch_details t3 on t1.batch_id=t3.batch_id INNER join "
                + "course_details t4 on t3.course_id=t4.course_id GROUP BY t1.batch_id, t1.student_id HAVING "
                + "SUM(gross_amount )<t4.course_fee "
                + "and t1.date_of_payement between ? and ?) as tc GROUP by k";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(selectQry);
        ps.setString(1, startDate);
        ps.setString(2, endDate);
        ResultSet rs = ps.executeQuery();
        HashMap<String,Integer> leftFeeMap=new HashMap<>();
        while (rs.next()) {
         String batch=rs.getString(2);
         int leftFee=rs.getInt(1);
         if(leftFeeMap.containsKey(batch)){
             leftFeeMap.put(batch,leftFeeMap.get(leftFeeMap)+leftFee);
         }
         else{
             leftFeeMap.put(batch, leftFee);
         }

        }

        return leftFeeMap;
    }
         public static HashMap<String,Integer> getCollectedFeeBatchWise(String startDate, String endDate) throws SQLException {
        ArrayList<MyPayement> list = new ArrayList<>();
        String selectQry = "SELECT sum(`gross_amount`),`batch_id` FROM `student_transaction` where "
                + "date_of_payement BETWEEN ? and ? GROUP by `batch_id`";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(selectQry);
        ps.setString(1, startDate);
        ps.setString(2, endDate);
        HashMap<String,Integer> leftFeeMap=new HashMap<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String batch=rs.getString(2);
         int leftFee=rs.getInt(1);
         if(leftFeeMap.containsKey(batch)){
             leftFeeMap.put(batch,leftFeeMap.get(leftFeeMap)+leftFee);
         }
         else{
             leftFeeMap.put(batch, leftFee);
         }

        }

        return leftFeeMap;
    }
          public static HashMap<String,Integer> getCollectedFeeBatchWise(String startDate, String endDate,int centre) throws SQLException {
        ArrayList<MyPayement> list = new ArrayList<>();
        String selectQry = "SELECT sum(`gross_amount`),`batch_id` FROM `student_transaction` where "
                + "date_of_payement BETWEEN ? and ? and centre_id=? GROUP by `batch_id`";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(selectQry);
        ps.setString(1, startDate);
        ps.setString(2, endDate);
        ps.setInt(3, centre);
        HashMap<String,Integer> leftFeeMap=new HashMap<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String batch=rs.getString(2);
         int leftFee=rs.getInt(1);
         if(leftFeeMap.containsKey(batch)){
             leftFeeMap.put(batch,leftFeeMap.get(leftFeeMap)+leftFee);
         }
         else{
             leftFeeMap.put(batch, leftFee);
         }

        }

        return leftFeeMap;
    }
   public static HashMap<String,Integer> getStudentCountBatchWise(String startDate, String endDate) throws SQLException {
        ArrayList<MyPayement> list = new ArrayList<>();
        String selectQry = "SELECT count(*),`batch_id` FROM `student_registration` "
                + "WHERE `registration_date` between ? and ? GROUP by `batch_id`";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(selectQry);
        ps.setString(1, startDate);
        ps.setString(2, endDate);
        HashMap<String,Integer> studentCount=new HashMap<>();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String batch=rs.getString(2);
         int leftFee=rs.getInt(1);
         if(studentCount.containsKey(batch)){
             studentCount.put(batch,studentCount.get(studentCount)+leftFee);
         }
         else{
             studentCount.put(batch, leftFee);
         }

        }

        return studentCount;
    }
     public static ArrayList<ShowBatch> showRecords(String startDate,String end) throws SQLException
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
                  + "between STR_TO_DATE(?, '%Y-%m-%d') and STR_TO_DATE(?, '%Y-%m-%d')";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, startDate);
            ps.setString(2, end);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                ShowBatch bs=new ShowBatch();
                bs.setBatchid(rs.getString(1)); 
                 bs.setFaculty(rs.getString(2));
                 bs.setCourse(rs.getString(3));
                 bs.setCenter(rs.getString(4));
                 bs.setStudents(rs.getInt(5));
                 bs.setFee(rs.getInt(6));
                bs.setStart(rs.getString(7));
                bs.setTimming(rs.getString(8));
                bs.setDays(rs.getString(9));
                
                
               
                sb.add(bs);
            }
            return sb;
      }
     public static ArrayList<ShowBatch> showRecords(String startDate,String faculty,String centre) throws SQLException
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
                  + ">STR_TO_DATE(?, '%Y-%m-%d') and faculty=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, startDate);
            ps.setString(2, faculty);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                ShowBatch bs=new ShowBatch();
                bs.setBatchid(rs.getString(1)); 
                 bs.setFaculty(rs.getString(2));
                 bs.setCourse(rs.getString(3));
                 bs.setCenter(rs.getString(4));
                 bs.setStudents(rs.getInt(5));
                 bs.setFee(rs.getInt(6));
                bs.setStart(rs.getString(7));
                bs.setTimming(rs.getString(8));
                bs.setDays(rs.getString(9));
                
                
               
                sb.add(bs);
            }
            return sb;
      }
      public static ArrayList<ShowBatch> showRecords(String startDate,String faculty,int centre) throws SQLException
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
            ps.setString(1, startDate);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                ShowBatch bs=new ShowBatch();
                bs.setBatchid(rs.getString(1)); 
                 bs.setFaculty(rs.getString(2));
                 bs.setCourse(rs.getString(3));
                 bs.setCenter(rs.getString(4));
                 bs.setStudents(rs.getInt(5));
                 bs.setFee(rs.getInt(6));
                bs.setStart(rs.getString(7));
                bs.setTimming(rs.getString(8));
                bs.setDays(rs.getString(9));
                
                
               
                sb.add(bs);
            }
            return sb;
      }
}
