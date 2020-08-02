/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import scaapp.pojo.StudentTotalFeeDetail;

/**
 *
 * @author Naman
 */
public class TransactionIdUpdater {
    public static void main(String [] args) throws SQLException {
        int id=5163;
        int i=1;
        while(true){
             String getStudentTransactionDetailsQry="update student_transaction set transaction_id=? where transaction_id=?";
         //int studentDetailId=0;
         
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,"SCA/2020-2021/"+i);
           ps.setString(2,"SCA/2020-2021/"+id);
           ps.executeUpdate();
            System.out.println("Updated "+id);
            id++;
           
            i++;
           
          
            
        }
    }
}
