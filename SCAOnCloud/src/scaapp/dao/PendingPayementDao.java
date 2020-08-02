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
import scaapp.pojo.MyPayement;
import scaapp.utils.DBConnection;

/**
 *
 * @author Comp8
 */
public class PendingPayementDao {
     public static ArrayList<MyPayement> getPendingPayementBatchWise(String batchId,int fee)throws SQLException
      {
         ArrayList<MyPayement> list=new ArrayList<>();
          String selectQry = "SELECT t2.student_name, t2.student_contact_no, t1.batch_id, SUM( t1.gross_amount ),t2.student_email_id \n" +
"FROM student_transaction t1\n" +
"INNER JOIN student_details t2 ON t1.student_id = t2.student_contact_no\n" +
"GROUP BY t1.batch_id, t1.student_id\n" +
"HAVING SUM( gross_amount ) <?\n" +
"AND batch_id =?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setString(2,batchId);
            ps.setInt(1, fee);
                  ResultSet rs=ps.executeQuery();
                  while(rs.next()){
                     MyPayement mp=new MyPayement();
                     mp.setStudentName(rs.getString(1));
                     mp.setStudentContactNo(rs.getString(2));
                     mp.setGrossAmount(rs.getDouble(4));
                     mp.setBatchId(rs.getString(3));
                     mp.setEmail(rs.getString(5));
                     list.add(mp);
                      System.out.println(mp);
                  
                  }
                  
                
                  return list;
      }
     public static void main(String[] args) throws SQLException {
        //getMyPayementDetailsDateWise(1,4000);
    }
}
