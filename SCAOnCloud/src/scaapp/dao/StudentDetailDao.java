/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import scaapp.pojo.IDCardPrintingDetails;
import scaapp.pojo.StudentDetail;
import scaapp.pojo.StudentTransaction;
import scaapp.utils.DBConnection;


/**
 *
 * @author Comp08
 */
public class StudentDetailDao {
    public static void main(String[] args) throws SQLException {
       // getAllStudentsContactNohh();
       test();
    }
    
     public static int addStudentDetail(StudentDetail studentInquiry)throws SQLException{
         
            String insertStudentDetailsQry = "Insert into student_details(student_contact_no,student_name,father_contact_no,student_email_id,student_collage,student_sem,registration_date,permanent_address,temperory_address) values(?,?,?,?,?,?,?,?,?)";

            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertStudentDetailsQry);
                  ps.setString(1, studentInquiry.getStudentContact());
                  ps.setString(2, studentInquiry.getStudentName());
                  ps.setString(3, studentInquiry.getFatherContact());
                  ps.setString(4, studentInquiry.getStudentEmail());
                  ps.setString(5, studentInquiry.getStudentCollege());
                  ps.setString(6, studentInquiry.getStudentSem());
                  ps.setString(7, studentInquiry.getRegistrationDate().toString().substring(0,18));
                  ps.setString(8, studentInquiry.getPermanentAddress());
                  ps.setString(9, studentInquiry.getCurrentAddress());
                 int i = ps.executeUpdate();
                  return i;
     }
    
     public static StudentDetail getStudentDetailsByContactNo(String contact_no)throws SQLException{
         System.out.println(contact_no);
       String getStudentDetailsQry = "select * from student_details where student_contact_no=?";
       StudentDetail studentDetail = null;
           Connection conn = DBConnection.getConnection();
           PreparedStatement ps = conn.prepareStatement(getStudentDetailsQry);
           ps.setString(1,contact_no);
           ps.setQueryTimeout(5);
           System.out.println(ps);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               studentDetail = new StudentDetail();
               String name = rs.getString(2);
               String email = rs.getString(4);
               String college = rs.getString(5);  
               String sem = rs.getString(6);
          studentDetail.setStudentDetails(name, email, college,sem);
          
           }
            return studentDetail;         
   }
       public static StudentDetail getFullStudentDetailsByContactNo(String contact_no)throws SQLException{
         System.out.println(contact_no);
       String getStudentDetailsQry = "select * from student_details where student_contact_no=?";
       StudentDetail studentDetail = null;
           Connection conn = DBConnection.getConnection();
           PreparedStatement ps = conn.prepareStatement(getStudentDetailsQry);
           ps.setString(1,contact_no);
           ps.setQueryTimeout(5);
           System.out.println(ps);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               studentDetail = new StudentDetail();
               String name = rs.getString(2);
               String email = rs.getString(4);
               String college = rs.getString(5);  
               String sem = rs.getString(6);
          studentDetail.setStudentDetails(name, email, college,sem);
          
           }
            return studentDetail;         
   }
     
     
     public static ArrayList<IDCardPrintingDetails> getStudentDetailsBatchWise(String batchId)throws SQLException{
        // System.out.println(contact_no);
       String getStudentDetailsQry = "SELECT student_contact_no,student_name,student_email_id,student_sem FROM student_details INNER JOIN student_registration on student_details.student_contact_no=student_registration.student_id  WHERE student_registration.batch_id=?";
       StudentDetail studentDetail = null;
       ArrayList<IDCardPrintingDetails> printingDataList = new ArrayList<>();
           Connection conn = DBConnection.getConnection();
           PreparedStatement ps = conn.prepareStatement(getStudentDetailsQry);
           ps.setString(1,batchId);
           ps.setQueryTimeout(5);
           
           ResultSet rs = ps.executeQuery();
            if(batchId.length()>20)
                batchId=batchId.substring(0, 21)+"..";
           int count=0;
          while(rs.next()){
              IDCardPrintingDetails id=new IDCardPrintingDetails();
              id.setBatchId(batchId);
              id.setStudentId(rs.getString(1));
              id.setStudentName(rs.getString(2));
              id.setEmail(rs.getString(3));
              id.setSem(rs.getString(4));
//               studentDetail = new StudentDetail();
//               String name = rs.getString(2);
//               String email = rs.getString(4);
//               String college = rs.getString(5);  
//               String sem = rs.getString(6);
//          studentDetail.setStudentDetails(name, email, college,sem);
//          
             printingDataList.add(id);
           }
          System.out.println(count);
            return printingDataList;         
   }
     
     public static int validateStudent(String contact_no)throws SQLException{
         String getStudentDetailsQry="select * from student_detail  where student_contact_no = ? ";
         int studentDetailId=0;
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentDetailsQry);
           ps.setString(1,contact_no);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           
           if(rs.next()){
               studentDetailId = rs.getInt(1);
               return studentDetailId;
           }else{
               return studentDetailId;
           }
     }
 public static ArrayList<String> getAllStudentsContactNo()throws SQLException{
         String getStudentTransactionDetailsQry="select student_contact_no from student_details";
         //int studentDetailId=0;
           ArrayList<String> list=new ArrayList<>();
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              list.add(rs.getString(1));
           }
           return list;
     }
// public static ArrayList<String> getAllStudentsContactNohh()throws SQLException{//network timeout checker
//         String getStudentTransactionDetailsQry="select * from student_transaction order by transaction_id ";
//         //int studentDetailId=0; 
//           ArrayList<String> list=new ArrayList<>();
//         Connection conn=DBConnection.getConnection();
//           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
//          ps.setQueryTimeout(2);
//           ResultSet rs=ps.executeQuery();
//           while(rs.next()){
//              list.add(rs.getString(1));
//           }
//           System.out.println(list.size());
//           return list;
//     }
 
   public static void test()throws SQLException{
         String getStudentDetailsQry="select * from testDate  ";
         int studentDetailId=0;
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentDetailsQry);
          // ps.setString(1,contact_no);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           
           while(rs.next()){
               Date d = rs.getDate(1);
               System.out.println(d);
           }
           System.out.println("done");
     }
 
}
