/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.dao;

import scaapp.pojo.EmployeeDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import scaapp.utils.DBConnection;


public class EmployeeDao {
    
    public static String getEmployeeById(int id)throws SQLException{
        String name="";
        String insertBatchQry = "select emp_name from employee_details where emp_id=?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertBatchQry);
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
           name=rs.getString(1);
        return name;
    }
    
    public static int getEmployeeId(String empName)throws SQLException{
        int id=0;
        String insertBatchQry = "select emp_id from employee_details where emp_name=?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(insertBatchQry);
        ps.setString(1, empName);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            id=rs.getInt(1);
        return id;
    }
    
     public static boolean addEmployee(EmployeeDetails Employee)throws SQLException{
        //int count= getRowsCount()+1;
            String insertCourseQry = "INSERT INTO `employee_details`(`emp_name`, `emp_phone_no`, `password`) VALUES (?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            String str=Employee.getEmp_name();
                  ps.setString(2, Employee.getEmp_phone_no());
                  ps.setString(3, Employee.getPassword());
                  ps.setString(1, str);
                 int i = ps.executeUpdate();
                   if(i>0){
                    return true;
                }else{
                    return false;
                }
          }
      public static EmployeeDetails validateEmployee(String contact_no,String password)throws SQLException{
         String getStudentDetailsQry="select * from employee_details where emp_phone_no = ? and password = ?";
         int employeeId=0;
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentDetailsQry);
           ps.setQueryTimeout(0);
           ps.setString(1,contact_no);
           ps.setString(2,password);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
               EmployeeDetails ed=new EmployeeDetails();
               ed.setEmp_id(rs.getInt(1));
               ed.setEmp_name(rs.getString(2));
               ed.setEmp_phone_no(rs.getString(3));
               ed.setPassword(rs.getString(4));
               return ed;
           }
               return null;
          
     }
        public static ArrayList<String> getAllEmployees()throws SQLException{
            //int count= getRowsCount()+1;
            String insertCourseQry = "SELECT emp_name FROM `employee_details`";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ArrayList<String> arr=new ArrayList<String>();
                 ResultSet rs=ps.executeQuery();
                 while(rs.next())
                 {
                     arr.add(rs.getString(1));
                 }
                 return arr;
          }
        
    public static String getPasswordById(int id)throws SQLException{
        String password = "";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT password FROM employee_details WHERE emp_id = ?");
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
           password = rs.getString(1);
        }
        return password;
    }
 
    public static boolean changePassword(int id,String newpassword)throws SQLException{
        DBConnection.setBatchType("NORMAL");
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE employee_details SET password = ? WHERE emp_id = ?");
        ps.setString(1,newpassword);
        ps.setInt(2,id);
        int i =ps.executeUpdate();
        DBConnection.setBatchType("PROJECT");
        conn = DBConnection.getConnection();
        ps = conn.prepareStatement("UPDATE employee_details SET password = ? WHERE emp_id = ?");
        ps.setString(1,newpassword);
        ps.setInt(2,id);
        i =ps.executeUpdate();
        
        if(i>0){
            return true;
        }else{
        return false;
        }
    }
     public static boolean CheckPermission(EmployeeDetails Employee)throws SQLException{
        //int count= getRowsCount()+1;
            String insertCourseQry = "select * from role_description where emp_id=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            String str=Employee.getEmp_name();
                  ps.setInt(1, Employee.getEmp_id());
                  
                 ResultSet rs = ps.executeQuery();
                   if(rs.next()){
                    return true;
                }else{
                    return false;
                }
          }
     public static boolean CheckHSPPermission(String pass)throws SQLException{
        //int count= getRowsCount()+1;
            String insertCourseQry = "select * from employee_details where password=? and emp_name=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
           
                  ps.setString(1, pass);
                  ps.setString(2, "HSP");
                 ResultSet rs = ps.executeQuery();
                   if(rs.next()){
                    return true;
                }else{
                    return false;
                }
     }
     public static boolean CheckHSPHighPermission(String pass)throws SQLException{
        //int count= getRowsCount()+1;
            String insertCourseQry = "select * from employee_details where password=? and emp_name=?";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
           
                  ps.setString(1, pass);
                  ps.setString(2, "HSP2");
                 ResultSet rs = ps.executeQuery();
                   if(rs.next()){
                    return true;
                }else{
                    return false;
                }
          }
     public static ArrayList<String> getAllFaculties()throws SQLException{
            //int count= getRowsCount()+1;
            String insertCourseQry = "SELECT emp_name FROM `employee_details` where role='Faculty'";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            ArrayList<String> arr=new ArrayList<String>();
                 ResultSet rs=ps.executeQuery();
                 while(rs.next())
                 {
                     arr.add(rs.getString(1));
                 }
                 return arr;
          }
}