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
import java.util.Date;
import scaapp.utils.DBConnection;

/**
 *
 * @author Comp10
 */
public class LastBackupDao {
    public static boolean insertFirstTransactionDate() throws SQLException,ParseException{ 
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into last_backup values(?)");
        java.sql.Date sqlDate = new java.sql.Date(new Date().getTime()); 

        ps.setDate(1, sqlDate);
        return ps.executeUpdate()>0;
    }
    
    public static String retrieveLastTransactionDate() throws SQLException{
        String selectQry = "select * from last_backup";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(selectQry);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            return rs.getString(1);
        }
        else{
            return "Last backup date not found!";
        }
    }
}
