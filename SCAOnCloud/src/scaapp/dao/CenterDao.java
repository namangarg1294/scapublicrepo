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
import scaapp.pojo.CenterDetails;
import scaapp.utils.DBConnection;


/**
 *
 * @author Lenovo
 */
public class CenterDao {
    public static boolean addCenter(CenterDetails center)throws SQLException{
         
            String insertBatchQry = "INSERT INTO `centre_details`(`centre_name`, `centre_address`, `centre_phone_no`, `centre_incharge`) VALUES (?,?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
                  ps.setString(1, center.getCentre_name());
                  ps.setString(2, center.getCentre_address());
                  ps.setString(3, center.getCentre_phone_no());
                  ps.setInt(4, center.getCentre_incharge());
                 int i = ps.executeUpdate();
                   if(i>0){
                    return true;
                }else{
                    return false;
                }
          }
      public static String getCenter(int id)throws SQLException
      {
          String name="";
          String insertBatchQry = "select centre_name from centre_details where centre_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setInt(1, id);
                  ResultSet rs=ps.executeQuery();
               if(rs.next())
                   name=rs.getString(1);
                     
                  return name;
      }
      
      public static int getCenterId(String centreName)throws SQLException
      {
          int id=0;
          String insertBatchQry = "select centre_id from centre_details where centre_name=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, centreName);
                  ResultSet rs=ps.executeQuery();
               if(rs.next())
                   id=rs.getInt(1);
                     
                  return id;
      }
      
      
}
