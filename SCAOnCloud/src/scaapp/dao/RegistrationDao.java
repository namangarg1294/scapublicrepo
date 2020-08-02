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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import scaapp.pojo.StudentTransaction;
import scaapp.utils.DBConnection;

/**
 *
 * @author Comp8
 */
public class RegistrationDao {
      public static boolean isRegistered(String studentId,String batchId) throws SQLException
      {   
          //ArrayList<String> sb=new ArrayList<>();
          String selectQry = "select registration_id from student_registration where student_id=? and batch_id=?";
          
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setString(1, studentId);
            ps.setString(2, batchId);
            ps.setQueryTimeout(5);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
               
                return true;
            }
            return false;
      }
      public static int getRegistrationId(String studentId,String batchId) throws SQLException
      {   
          //ArrayList<String> sb=new ArrayList<>();
          String selectQry = "select registration_id from student_registration where student_id=? and batch_id=?";
          
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setString(1, studentId);
            ps.setString(2, batchId);
            ps.setQueryTimeout(5);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
               
                return rs.getInt(1);
            }
            return -1;
      }
      
        public static boolean isRegisteredInVideos(String studentId,String batchId) throws SQLException
      {   
          //ArrayList<String> sb=new ArrayList<>();
          String selectQry = "select isVideo from student_registration where student_id=? and batch_id=?";
          
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setString(1, studentId);
            ps.setString(2, batchId);
            ps.setQueryTimeout(5);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
               
               if(rs.getInt(1)==1)
                   return true;
            }
            return false;
      }
         public static String getRemark(int registrationId) throws SQLException
      {   
          //ArrayList<String> sb=new ArrayList<>();
          String selectQry = "select remark from remark_mapping where registration_id=?";
          
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setInt(1, registrationId);
            
            ps.setQueryTimeout(5);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
               
             return rs.getString(1);
            }
            return null;
      }
          public static int getDiscount(int registrationId) throws SQLException
      {   
          //ArrayList<String> sb=new ArrayList<>();
          String selectQry = "select discount from remark_mapping where registration_id=?";
          
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setInt(1, registrationId);
            
            ps.setQueryTimeout(5);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
               
             return rs.getInt(1);
            }
            return 0;
      }
       public static boolean registerStudentInBatch(String studentId,String batchId) throws SQLException
      {   
          //ArrayList<String> sb=new ArrayList<>();
          String selectQry = "insert into student_registration (student_id,batch_id,registration_date) values(?,?,?)";
          
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setString(1, studentId);
            ps.setString(2, batchId);
            ps.setString(3, new Date().toString());
              ps.setQueryTimeout(5);
           return ps.executeUpdate()>0;
            
      }
       public static boolean mapRegistrationAndRemark(int registrationId,String remark,int discount) throws SQLException
      {   
          //ArrayList<String> sb=new ArrayList<>();
          String selectQry = "insert into remark_mapping (registration_id,Remark,discount) values(?,?,?)";
          
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setInt(1, registrationId);
            ps.setString(2, remark);
            ps.setInt(3,discount);
           
              ps.setQueryTimeout(5);
           return ps.executeUpdate()>0;
            
      }
       public static boolean updateRemark(int registrationId,String remark, int discount) throws SQLException
      {
          //ArrayList<String> sb=new ArrayList<>();
          String selectQry = "update remark_mapping set Remark=?,discount=? where registration_id=?";
          
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setInt(3, registrationId);
            ps.setInt(2, discount);
            ps.setString(1, remark);
           
              ps.setQueryTimeout(5);
           return ps.executeUpdate()>0;
            
      }
        public static ArrayList<String> getAllStudentRegisteredInBatch(String batchId) throws SQLException
      {   
          //ArrayList<String> sb=new ArrayList<>();
          String selectQry = "select student_id from student_registration where batch_id=?";
          ArrayList<String>list=new ArrayList<>();
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(selectQry);
            ps.setString(1,batchId);
              ps.setQueryTimeout(5);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               
                list.add(rs.getString(1));
            }
            return list;
            
      }
        public static ArrayList<String> getAllBatchIdOfSingleStudent(String sId) throws SQLException
      {   
          ArrayList<String> sb=new ArrayList<>();
          String insertBatchQry = "select batch_id from student_registration where student_id=?";
          Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertBatchQry);
            ps.setString(1, sId);
              ps.setQueryTimeout(5);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
               
                sb.add(rs.getString(1));
            }
            return sb;
      }
         public static void registerStudentInBatch(StudentTransaction st,int isVideo) throws SQLException {

	// This is to hold the response of executeBatch()
	int[] result = null;
        Connection conn=DBConnection.getConnection();
	try {
                
		Statement stmt = conn.createStatement();

		conn.setAutoCommit(false);
                
                
                
                
                
                // Setting auto-commit off
		String SQL = "Insert into student_transaction (transaction_id,student_id,employee_id,batch_id,centre_id,payement_mode_id,gross_amount,cgst_amount,sgat_amount,net_amount,date_of_payement)values('"+st.getTransactionId()+"','"+st.getStudentId()+"',"+st.getEmployeeId()+",'"+st.getBatchId()+"',"+st.getCentreId()+","+st.getPayementModeId()+","+Math.round(st.getGrossAmount())+","+Math.round(st.getCgstAmount())+","+Math.round(st.getSgstAmount())+","+Math.round(st.getNetAmount())+",'"+st.getDateOfPayement()+"')";
		stmt.addBatch(SQL); // add statement to Batch
		SQL = "insert into student_registration (student_id,batch_id,registration_date,isVideo) values('"+st.getStudentId()+"','"+st.getBatchId()+"','"+st.getDateOfPayement()+"','"+isVideo+"')";
		stmt.addBatch(SQL); // add second statement to Batch
                stmt.setQueryTimeout(5);
		result = stmt.executeBatch(); // execute the Batch
		conn.commit(); // commit
	} catch (SQLException e) {
		conn.rollback(); // rollBack in case of an exception
                System.out.println(e);
		throw e;
	} finally {
		if (conn != null)
			conn.close(); // finally close the connection
	}
	System.out.println("Number of rows affected: " + result.length);
}
         
   
         
                
}
