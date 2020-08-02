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
import scaapp.utils.DBConnection;


/**
 *
 * @author Lenovo
 */
public class SessionDao {
    public static boolean addSession(String start,String end)throws SQLException{
        //int count= getRowsCount()+1;
            String insertCourseQry = "INSERT INTO `session_details`(`session_start_date`, `session_end_date`) VALUES (?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
                  ps.setString(2, end);
                  ps.setString(1, start);
                 int i = ps.executeUpdate();
                   if(i>0){
                    return true;
                }else{
                    return false;
                }
          }
    public static ArrayList<String> getSession()throws SQLException{
        //int count= getRowsCount()+1;
            String insertCourseQry = "SELECT * FROM `session_details` WHERE `session_start_date` LIKE ? OR `session_start_date` LIKE ?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            Date dt=new Date();
            ArrayList<String> arr=new ArrayList<String>();
            int st=Integer.parseInt(dt.toString().substring(24,28));
                  ps.setString(1, "%"+st);
                  ps.setString(2, "%"+(st+1));
                 ResultSet rs=ps.executeQuery();
                 while(rs.next())
                 {
                     arr.add(rs.getString(1));
                 }
                 return arr;
          }
}
