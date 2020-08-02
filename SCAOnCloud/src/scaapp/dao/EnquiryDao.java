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
import scaapp.pojo.EnquiryDetails;
import scaapp.pojo.StudentTransaction;
import scaapp.utils.DBConnection;

/**
 *
 * @author Naman
 */
public class EnquiryDao {
    public static ArrayList<EnquiryDetails> getEnquiryDetailsBetweenDates(String startDate,String endDate)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT student_contact_no,student_name,student_email_id,cd.course_name \n" +
                                        "FROM student_details sd \n" +
                                        "INNER JOIN enquiry_new e ON sd.student_contact_no = e.contact\n" +
                                        "INNER JOIN course_details cd ON e.course = cd.course_id\n" +
                                        "where e.date between ? and ? and student_contact_no ";
                                        
                                                 //int studentDetailId=0;
           ArrayList<EnquiryDetails> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           //ps.setString(1,course);
          // ps.setString(2,course);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
          // ps.setString(4,course);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           
           while(rs.next()){
               EnquiryDetails ed=new EnquiryDetails();
               ed.setName(rs.getString(2));
               ed.setPhno(rs.getString(1));
               ed.setEmail(rs.getString(3));
               ed.setSubject(rs.getString(4));
               list.add(ed);
               System.out.print(rs.getString(1)+"  ");
               System.out.print(rs.getString(2)+"  ");
               System.out.print(rs.getString(3)+"  ");
               System.out.print(rs.getString(4)+"  ");
               System.out.println();
               System.out.println();
           }
           return list;
     }
      public static ArrayList<EnquiryDetails> getEnquiryDetailsBetweenDatesCetreWise(String startDate,String endDate,String centre)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT student_contact_no,student_name,student_email_id,cd.course_name \n" +
                                        "FROM student_details sd \n" +
                                        "INNER JOIN enquiry_new e ON sd.student_contact_no = e.contact\n" +
                                        "INNER JOIN course_details cd ON e.course = cd.course_id\n" +
                                        "where e.centre=? and e.date between ? and ? and student_contact_no ";
                                        
                                                 //int studentDetailId=0;
           ArrayList<EnquiryDetails> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           //ps.setString(1,course);
           ps.setString(1,centre);
           ps.setString(2,startDate);
           ps.setString(3,endDate);
          // ps.setString(4,course);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           
           while(rs.next()){
                EnquiryDetails ed=new EnquiryDetails();
               ed.setName(rs.getString(2));
               ed.setPhno(rs.getString(1));
               ed.setEmail(rs.getString(3));
               ed.setSubject(rs.getString(4));
               list.add(ed);
               System.out.print(rs.getString(1)+"  ");
               System.out.print(rs.getString(2)+"  ");
               System.out.print(rs.getString(3)+"  ");
               System.out.print(rs.getString(4)+"  ");
               System.out.println();
               System.out.println();
           }
           return list;
     }
    public static ArrayList<EnquiryDetails> getEnquiryDetailsCourseWiseBetweenDates(String startDate,String endDate,String centre,String course)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT student_contact_no,student_name,student_email_id,cd.course_name \n" +
                                        "FROM student_details sd \n" +
                                        "INNER JOIN enquiry_new e ON sd.student_contact_no = e.contact\n" +
                                        "INNER JOIN course_details cd ON e.course = cd.course_id\n" +
                                        "where e.centre=? and cd.course_name=? and e.date between ? and ? and student_contact_no "+
                                        "not in "+
                                        "(select student_id from student_registration sr inner join batch_details bd on sr.batch_id=bd.batch_id "
                 + "                      inner join course_details cd on cd.course_id=bd.course_id where cd.course_name=?)";
           
                                                 //int studentDetailId=0;
           ArrayList<EnquiryDetails> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,centre);
          ps.setString(2,course);
           ps.setString(3,startDate);
           ps.setString(4,endDate);
           ps.setString(5,course);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           System.out.println(course);
           while(rs.next()){
                EnquiryDetails ed=new EnquiryDetails();
               ed.setName(rs.getString(2));
               ed.setPhno(rs.getString(1));
               ed.setEmail(rs.getString(3));
               ed.setSubject(rs.getString(4));
               list.add(ed);
               System.out.print(rs.getString(1)+"  ");
               System.out.print(rs.getString(2)+"  ");
               System.out.print(rs.getString(3)+"  ");
               System.out.print(rs.getString(4)+"  ");
               System.out.println();
               System.out.println();
           }
           return list;
     }
    public static ArrayList<EnquiryDetails> getEnquiryDetailsCourseWiseBetweenDatesBothCentre(String startDate,String endDate,String course)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT student_contact_no,student_name,student_email_id,cd.course_name \n" +
                                        "FROM student_details sd \n" +
                                        "INNER JOIN enquiry_new e ON sd.student_contact_no = e.contact\n" +
                                        "INNER JOIN course_details cd ON e.course = cd.course_id\n" +
                                        "where cd.course_name=? and e.date between ? and ? and student_contact_no "+
                                        "not in "+
                                        "(select student_id from student_registration sr inner join batch_details bd on sr.batch_id=bd.batch_id "
                 + "                      inner join course_details cd on cd.course_id=bd.course_id where cd.course_name=?)";
           
                                                 //int studentDetailId=0;
           ArrayList<EnquiryDetails> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           //ps.setString(1,centre);
          ps.setString(1,course);
           ps.setString(2,startDate);
           ps.setString(3,endDate);
           ps.setString(4,course);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           System.out.println(course);
           while(rs.next()){
                EnquiryDetails ed=new EnquiryDetails();
               ed.setName(rs.getString(2));
               ed.setPhno(rs.getString(1));
               ed.setEmail(rs.getString(3));
               ed.setSubject(rs.getString(4));
               list.add(ed);
               System.out.print(rs.getString(1)+"  ");
               System.out.print(rs.getString(2)+"  ");
               System.out.print(rs.getString(3)+"  ");
               System.out.print(rs.getString(4)+"  ");
               System.out.println();
               System.out.println();
           }
           return list;
     }
}
