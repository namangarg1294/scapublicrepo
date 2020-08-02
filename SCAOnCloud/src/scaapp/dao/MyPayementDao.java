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
import scaapp.pojo.IDCardPrintingDetails;
import scaapp.pojo.MyPayement;
import scaapp.utils.DBConnection;

/**
 *
 * @author Comp8
 */
public class MyPayementDao {
     public static ArrayList<MyPayement> getMyPayementDetailsDateWise(int empId,String dateOfPayement)throws SQLException
      {
         ArrayList<MyPayement> list=new ArrayList<>();
          String selectQry = "select t2.student_name,t2.student_contact_no,t1.batch_id,t1.gross_amount from student_transaction t1 INNER JOIN student_details t2 ON t1.student_id=t2.student_contact_no where employee_id=? and date_of_payement=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setInt(1,empId);
            System.out.println("In my payements "+dateOfPayement);
            ps.setString(2, dateOfPayement);
                  ResultSet rs=ps.executeQuery();
                  while(rs.next()){
                     MyPayement mp=new MyPayement();
                     mp.setStudentName(rs.getString(1));
                     mp.setStudentContactNo(rs.getString(2));
                     mp.setGrossAmount(rs.getDouble(4));
                     mp.setBatchId(rs.getString(3));
                     list.add(mp);
                  
                  
                  }
                
                  return list;
      }
}
