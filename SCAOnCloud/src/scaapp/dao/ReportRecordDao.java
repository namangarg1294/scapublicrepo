/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import scaapp.utils.DBConnection;

/**
 *
 * @author Comp8
 */
public class ReportRecordDao {
     public static boolean addReport(String date)throws SQLException{
        //int count= getRowsCount()+1;
            String insertCourseQry = "INSERT INTO  report_record VALUES (?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
                  ps.setString(1,date);
                  
                 int i = ps.executeUpdate();
                   if(i>0){
                    return true;
                }else{
                    return false;
                }
          }
}
