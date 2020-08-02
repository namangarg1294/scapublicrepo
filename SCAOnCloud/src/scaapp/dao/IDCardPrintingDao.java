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
import java.util.Date;
import scaapp.pojo.IDCardPrintingDetails;
import scaapp.utils.DBConnection;

/**
 *
 * @author Comp8
 */
public class IDCardPrintingDao {
    public static IDCardPrintingDetails getPrintingDetails(String batchId)throws SQLException
      {
          //ArrayList<IDCardPrintingDetails> list=new ArrayList<>();
          String insertBatchQry = "select course_name,time,days from batch_details INNER JOIN course_details ON batch_details.course_id=course_details.course_id and batch_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, batchId);
                  ResultSet rs=ps.executeQuery();
                  if(rs.next()){
                      IDCardPrintingDetails idCPD=new IDCardPrintingDetails();
                      idCPD.setBatchId(batchId);
                     // idCPD.setSession(rs.getString(1));
                      idCPD.setCourseName(rs.getString(1));
                      idCPD.setBatchTime(rs.getString(2));
                      idCPD.setDays(rs.getString(3));
                      return idCPD;
                  
                  
                  }
                
                  return null;
      }
     public static boolean insertInIdPrintingDetails(String studentId,String batchId,int empId) throws SQLException
      {   
          //ArrayList<String> sb=new ArrayList<>();
          String insertQry = "insert into id_card_print_details (student_id,batch_id,emp_id) values(?,?,?)";
          
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertQry);
            ps.setString(1, studentId);
            ps.setString(2, batchId);
            ps.setInt(3, empId);
           return ps.executeUpdate()>0;
            
      }
     public static boolean checkIsIdCardAlreadyPrinted(String studentId,String batchId) throws SQLException
     {
         String selectQuery="select * from id_card_print_details where student_id=? and batch_id=?";
         Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQuery);
            System.out.println("In dao "+studentId);
             System.out.println("In dao "+batchId);
            ps.setString(1, studentId);
            ps.setString(2, batchId);
             ResultSet rs=ps.executeQuery();
           if(rs.next())
               return true;
         return false;
     }
}
