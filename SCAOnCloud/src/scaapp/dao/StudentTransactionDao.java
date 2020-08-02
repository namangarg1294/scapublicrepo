/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import scaapp.pojo.StudentTotalFeeDetail;
import scaapp.pojo.StudentTransaction;
import scaapp.utils.DBConnection;
import scaapp.utils.TimeLookup;

/**
 *
 * @author Comp8
 */
public class StudentTransactionDao {
     public static int getNewTransactionId(String financialYear)throws SQLException{
         String getStudentDetailsQry="SELECT max(CAST(SUBSTRING_INDEX(transaction_id, ?, -1) AS SIGNED)) FROM student_transaction ";
         int recieptId=0;
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentDetailsQry);
           ps.setString(1,"/");
           ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
              recieptId = rs.getInt(1);
               System.out.println(recieptId);
           }
           return recieptId+1;
     }
     
     
     
      public static int getNewTransactionIdNew(int year,int centre)throws SQLException{
         String getStudentDetailsQry="SELECT max(CAST(SUBSTRING_INDEX(transaction_id, ?, -1) AS SIGNED)) FROM student_transaction where transaction_id like ?";
         int recieptId=0;
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentDetailsQry);
           ps.setString(1,"/");
           if(centre==1)
           //ps.setString(2,"SCA/%");
             ps.setString(2,"SCA/"+year+"-"+(year+1)+"/%");
           else 
           ps.setString(2,"SCAI/"+year+"-"+(year+1)+"/%");
           ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
              recieptId = rs.getInt(1);
               System.out.println(recieptId);
           }
           return recieptId+1;
     }
     public static void main(String[] args) throws SQLException {
       //  try {
             // getLastTransactionId(null);
             //changeIDForDeveloperUse();
             //dateDemo();
             //changeDateFormateForDeveloperUse();
             //changeDateFormateForDeveloperUse();
          //  DataSwapperDeveloperUse(null);
//         } catch (SQLException ex) {
//             System.out.println(ex);
//         }
       //  System.out.println(getNewTransactionIdNew(null,2));
    }
      public static int addStudentTransaction(StudentTransaction st)throws SQLException{
         
            String insertStudentDetailsQry = "Insert into student_transaction values(?,?,?,?,?,?,?,?,?,?,?,?)";

            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertStudentDetailsQry);
                  ps.setString(1, st.getTransactionId());
                  ps.setString(2, st.getStudentId());
                  ps.setInt(3, st.getEmployeeId());
                  ps.setString(4, st.getBatchId());
                  ps.setInt(5, st.getCentreId());
                  ps.setInt(6, st.getPayementModeId());
                  ps.setLong(7, Math.round(st.getGrossAmount()));
                  ps.setLong(8,Math.round(st.getCgstAmount()));
                  ps.setLong(9, Math.round(st.getSgstAmount()));
                  ps.setLong(10, Math.round(st.getNetAmount()));
                  ps.setString(11, st.getDateOfPayement());
                  ps.setString(12,null);
                  ps.setQueryTimeout(5);
                 int i = ps.executeUpdate();
                 //conn.commit();
                 System.out.println("Commit done:"+i);
                  return i;
     }
       public static int addRemarkForNullifingTransaction(String transactionId,String remark,String timeStamp,int empId)throws SQLException{
         
            String insertStudentDetailsQry = "Insert into  transaction_zero_remark_mapping values(?,?,?,?)";

            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertStudentDetailsQry);
                  ps.setString(1, transactionId);
                  ps.setInt(2, empId);
                  ps.setString(3, remark);
                  ps.setString(4, timeStamp);
                 
                 int i = ps.executeUpdate();
                 //conn.commit();
                 System.out.println("Commit done:"+i);
                  return i;
     }
      public static ArrayList<StudentTransaction> getStudentTransactionDetails(String contact_no)throws SQLException{
         String getStudentTransactionDetailsQry="select * from student_transaction  where student_id = ? ";
         //int studentDetailId=0;
           ArrayList<StudentTransaction> list=new ArrayList<>();
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,contact_no);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               StudentTransaction st=new StudentTransaction();
               st.setTransactionId(rs.getString(1));
               st.setBatchId(rs.getString(4));
               st.setEmployeeId(rs.getInt(3));
               st.setDateOfPayement(rs.getString(11));
               st.setGrossAmount(rs.getDouble(7));
               st.setNetAmount(rs.getDouble(10));
               st.setSgstAmount(rs.getDouble(9));
               st.setCgstAmount(rs.getDouble(8));
             
               list.add(st);
           }
           return list;
     }
            public static ArrayList<StudentTransaction> getStudentTransactionDetails(String contact_no,String batchId)throws SQLException{
         String getStudentTransactionDetailsQry="select student_id,transaction_id,batch_id,emp_name,date_of_payement,gross_amount,centre_id,cgst_amount,sgat_amount,net_amount from student_transaction INNER JOIN employee_details ON student_transaction.employee_id=employee_details.emp_id  having student_transaction.student_id = ? and student_transaction.batch_id=?";
         //int studentDetailId=0;
           ArrayList<StudentTransaction> list=new ArrayList<>();
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,contact_no);
           ps.setString(2,batchId);
            ps.setQueryTimeout(5);
                System.out.println("tran query "+ps);
                //Testing code
                DatabaseMetaData db = conn.getMetaData();
                System.out.println("url "+db.getURL());
                
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               StudentTransaction st=new StudentTransaction();
               st.setStudentId(rs.getString(1));
               st.setTransactionId(rs.getString(2));
               st.setBatchId(rs.getString(3));
               st.setEmpName(rs.getString(4));
               st.setDateOfPayement(rs.getString(5));
               st.setGrossAmount(rs.getDouble(6));
               st.setCentreId(rs.getInt(7));
                st.setNetAmount(rs.getDouble(10));
               st.setSgstAmount(rs.getDouble(9));
               st.setCgstAmount(rs.getDouble(8));
               list.add(st);
           }
           return list;
     }
         public static int getFeePaidByStudent(String contact_no,String batchId)throws SQLException{
         String getStudentTransactionDetailsQry="select count(gross_amount) from student_transaction where student_id=? and batch_id=?";
         //int studentDetailId=0;
         // ArrayList<StudentTransaction> list=new ArrayList<>();
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,contact_no);
           ps.setString(2,batchId);
            ps.setQueryTimeout(5);
                System.out.println("tran query "+ps);
                //Testing code
                DatabaseMetaData db = conn.getMetaData();
                System.out.println("url "+db.getURL());
                
           ResultSet rs=ps.executeQuery();
           int amount=0;
           if(rs.next()){
              amount=rs.getInt(1);
           }
           return amount;
     }
       public static ArrayList<StudentTransaction> getTransactionDetailsOfEmp(String contact_no)throws SQLException{
         String getStudentTransactionDetailsQry="select * from student_transaction  where student_id = ? ";
         //int studentDetailId=0;
           ArrayList<StudentTransaction> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,contact_no);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               StudentTransaction st=new StudentTransaction();
               st.setTransactionId(rs.getString(1));
               st.setBatchId(rs.getString(4));
               st.setEmployeeId(rs.getInt(3));
               st.setDateOfPayement(rs.getString(11));
               st.setGrossAmount(rs.getDouble(7));
               list.add(st);
           }
           return list;
     }
       public static boolean nullifyStudentTransaction(String transactionId)throws SQLException{
           String update="update student_transaction set gross_amount=0,cgst_amount=0,sgat_amount=0,net_amount=0 where transaction_id=?";
   
         //int studentDetailId=0;
           ArrayList<StudentTransaction> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(update);
           ps.setString(1,transactionId);
            ps.setQueryTimeout(5);
          return ps.executeUpdate()>0;
     }
       
       public static ArrayList<StudentTotalFeeDetail> getStudentTotalFeeBatchWise(String contact_no)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT t1.batch_id, t3.course_name,SUM( t1.gross_amount),t3.course_fee,t2.starting_date \n" +
"FROM student_transaction t1\n" +
"INNER JOIN batch_details t2 ON t1.batch_id = t2.batch_id INNER JOIN course_details t3 ON t2.course_id=t3.course_id"
                 + "\n" +
"GROUP BY t1.batch_id, t1.student_id\n" +
"HAVING student_id=?";
         //int studentDetailId=0;
           ArrayList<StudentTotalFeeDetail> list=new ArrayList<>();
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,contact_no);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               StudentTotalFeeDetail st=new StudentTotalFeeDetail();
               st.setBatchId(rs.getString(1));
            st.setCourse(rs.getString(2));
//               st.setEmployeeId(rs.getInt(3));
//               st.setDateOfPayement(rs.getString(11));
               st.setTotalAmount(rs.getDouble(3));
               st.setStudentId(contact_no);
               st.setLeftFee(rs.getDouble(4)-st.getTotalAmount());
               st.setBatchStartingDate(rs.getString(5));
               list.add(st);
           }
           return list;
     }
         public static double getStudentTotalPaidFeeInSingleBatch(String contact_no,String batchId)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT SUM( t1.gross_amount) \n" +
"FROM student_transaction t1\n" +
"GROUP BY t1.batch_id, t1.student_id\n" +
"HAVING student_id=? and batch_id=?";
         //int studentDetailId=0;
           //ArrayList<StudentTotalFeeDetail> list=new ArrayList<>();
         Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,contact_no);
           ps.setString(2, batchId);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           double totalAmount=0;
          if(rs.next()){
               totalAmount=rs.getDouble(1);
           }
           return totalAmount;
     }
         
         
    //Don't edit code below it 
    public static String getGrossAmountOfCashierByDate(String empName, String date) throws SQLException{
        Connection conn = DBConnection.getConnection();
            
        System.out.println("connection recieved "+conn);
        
        String grossAmount="";
        if(conn!=null){
            PreparedStatement ps=conn.prepareStatement("select sum(s.gross_amount) as total_fee_collection from student_transaction as s, employee_details as e where s.employee_id = e.emp_id and e.emp_name=? and s.date_of_payement=?");
            ps.setString(1,empName);
            ps.setString(2,date);
             ps.setQueryTimeout(5);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                grossAmount = rs.getString(1);
            }
        }
        else{
            System.out.println("Connection is null");
        }
        return grossAmount;
    }
    public static ArrayList<StudentTransaction> getTransactionDetailsBetweenDates(String startDate,String endDate)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT * FROM student_transaction WHERE date_of_payement between ? and ? order by date_of_payement";
         //int studentDetailId=0;
           ArrayList<StudentTransaction> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setString(1,startDate);
           ps.setString(2,endDate);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               StudentTransaction st=new StudentTransaction();
               st.setTransactionId(rs.getString(1));
               st.setBatchId(rs.getString(4));
               st.setEmployeeId(rs.getInt(3));
               st.setDateOfPayement(rs.getString(11));
               st.setGrossAmount(rs.getDouble(7));
               st.setSgstAmount(rs.getDouble(9));
               st.setCgstAmount(rs.getDouble(8));
               st.setNetAmount(rs.getDouble(10));
               st.setCentreId(rs.getInt(5));
               st.setStudentId(rs.getString(2));
               list.add(st);
           }
           return list;
     }
    
    
    public static ArrayList<StudentTransaction> getTransactionDetailsBetweenDates(String startDate,String endDate,int centre)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT * FROM student_transaction WHERE centre_id=? and date_of_payement between ? and ? order by date_of_payement";
         //int studentDetailId=0;
           ArrayList<StudentTransaction> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setInt(1, centre);
           ps.setString(2,startDate);
           ps.setString(3,endDate);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               StudentTransaction st=new StudentTransaction();
               st.setTransactionId(rs.getString(1));
               st.setBatchId(rs.getString(4));
               st.setEmployeeId(rs.getInt(3));
               st.setDateOfPayement(rs.getString(11));
               st.setGrossAmount(rs.getDouble(7));
               st.setSgstAmount(rs.getDouble(9));
               st.setCgstAmount(rs.getDouble(8));
               st.setNetAmount(rs.getDouble(10));
               st.setCentreId(rs.getInt(5));
               st.setStudentId(rs.getString(2));
               list.add(st);
           }
           return list;
     }
     public static ArrayList<StudentTransaction> getTransactionDetailsBetweenDates(String startDate,String endDate,int centre,int eid)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT * FROM student_transaction WHERE centre_id=? and employee_id=? and date_of_payement between ? and ? order by date_of_payement";
         //int studentDetailId=0;
           ArrayList<StudentTransaction> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setInt(1, centre);
           ps.setInt(2, eid);
           ps.setString(3,startDate);
           ps.setString(4,endDate);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               StudentTransaction st=new StudentTransaction();
               st.setTransactionId(rs.getString(1));
               st.setBatchId(rs.getString(4));
               st.setEmployeeId(rs.getInt(3));
               st.setDateOfPayement(rs.getString(11));
               st.setGrossAmount(rs.getDouble(7));
               st.setSgstAmount(rs.getDouble(9));
               st.setCgstAmount(rs.getDouble(8));
               st.setNetAmount(rs.getDouble(10));
               st.setCentreId(rs.getInt(5));
               st.setStudentId(rs.getString(2));
               list.add(st);
           }
           return list;
     }
    
    
      public static ArrayList<StudentTransaction> getTransactionDetailsBetweenDatesEmployeeWise(String startDate,String endDate,int eid)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT * FROM student_transaction WHERE employee_id=? and date_of_payement between ? and ? order by date_of_payement";
         //int studentDetailId=0;
           ArrayList<StudentTransaction> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setInt(1, eid);
           ps.setString(2,startDate);
           ps.setString(3,endDate);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
               StudentTransaction st=new StudentTransaction();
               st.setTransactionId(rs.getString(1));
               st.setBatchId(rs.getString(4));
               st.setEmployeeId(rs.getInt(3));
               st.setDateOfPayement(rs.getString(11));
               st.setGrossAmount(rs.getDouble(7));
               st.setSgstAmount(rs.getDouble(9));
               st.setCgstAmount(rs.getDouble(8));
               st.setNetAmount(rs.getDouble(10));
               st.setCentreId(rs.getInt(5));
               st.setStudentId(rs.getString(2));
               list.add(st);
           }
           return list;
     }
      
   public static void changeIDForDeveloperUse() throws SQLException{
        String getStudentTransactionDetailsQry="SELECT * FROM student_transaction";
         //int studentDetailId=0;
           ArrayList<String> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
            ps.setQueryTimeout(5);
//           ps.setInt(1, eid);
//           ps.setString(2,startDate);
//           ps.setString(3,endDate);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              list.add(rs.getString(1));
           }
          String update="update student_transaction set transaction_id=? where transaction_id=?";
    ps=conn.prepareStatement(update);
    int i=1;
    String s1="MN/2018-2019/";
          for(String s:list){
             ps.setString(1,s1+i);
             ps.setString(2, s);
             ps.executeUpdate();
             i++;
          }
   }   
      public static void dateDemoDeveloperUse() throws SQLException{
          java.util.Date utilDate = new java.util.Date(); 
          System.out.println("Util date in Java : " + utilDate);  
          java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); 
          System.out.println("SQL date in Java : " + sqlDate);
          String query = "INSERT INTO testDate (date1) VALUES (?)";
          Connection conn=DBConnection.getConnection();
          PreparedStatement st = conn.prepareStatement(query);
          st.setDate(1, sqlDate);
           st.setQueryTimeout(5);
           st.executeUpdate();
          query = "select * from testDate";
           PreparedStatement ps=conn.prepareStatement(query);
//           ps.setInt(1, eid);
//           ps.setString(2,startDate);
//           ps.setString(3,endDate);
           ResultSet rs=ps.executeQuery();
           System.out.println("Date from db");
           while(rs.next()){
               
               
               System.out.println(new Date(rs.getDate(1).getTime()));
               
           }
           
      }
      
       public static void changeDateFormateForDeveloperUse() throws SQLException, ParseException{
        String getStudentTransactionDetailsQry="SELECT * FROM student_transaction";
         //int studentDetailId=0;
           ArrayList<String> list=new ArrayList<>();
           ArrayList<String> listDate=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
            ps.setQueryTimeout(5);
//           ps.setInt(1, eid);
//           ps.setString(2,startDate);
//           ps.setString(3,endDate);
           ResultSet rs=ps.executeQuery();
           while(rs.next()){
              list.add(rs.getString(1));
              listDate.add(rs.getString(11));
           }
          String update="update student_transaction set date_of_payement=? where transaction_id=?";
    ps=conn.prepareStatement(update);
    //int i=1;
    String s1="MN/2018-2019/";
      SimpleDateFormat sdf1=new SimpleDateFormat("yy-MM-dd");
      SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");
          for(int i=0;i<list.size();i++){
              
             String dor=listDate.get(i);
             int a=Integer.parseInt(dor.substring(0,dor.indexOf("-")));
             if(a!=18){
             Date d=sdf.parse(dor);
             String newDate=sdf1.format(d);
             ps.setString(1,newDate);
             ps.setString(2,list.get(i));
              ps.setQueryTimeout(5);
             ps.executeUpdate();}
          }
   } 
         public static Date getDate(){
//         String getDateQry="SELECT now()";
//         Date date=null;
//         Connection conn=DBConnection.getConnection();
//           PreparedStatement ps=conn.prepareStatement(getDateQry);
//           //ps.setString(1,"/");
//            ps.setQueryTimeout(5);
//           ResultSet rs=ps.executeQuery();
//           if(rs.next()){
//             date = new Date(rs.getDate(1).getTime());
//             
//             
//           }
//           return date;
             
             return TimeLookup.getDate();
     }
         
//        public static void DataSwapperDeveloperUse(String contact_no)throws SQLException{
//         String getStudentTransactionDetailsQry="select * from student_details";
//         //int studentDetailId=0;
//           ArrayList<StudentTransaction> list=new ArrayList<>();
//         Connection conn=DBConnection.getConnection2();
//          Connection conn2=DBConnection.getConnection();
//            System.out.println(conn2);
//           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
//            System.out.println("Connected");
////           ps.setString(1,contact_no);
////            ps.setQueryTimeout(5);
//           ResultSet rs=ps.executeQuery();
//            System.out.println("on 495");
//            int count=0;
//           while(rs.next()){
//               PreparedStatement ps1=conn2.prepareStatement("INSERT INTO student_details (student_contact_no, student_name, student_email_id, registration_date) VALUES (?,?,?,?)");
//           ps1.setString(1,rs.getString(1));
//           ps1.setString(2,rs.getString(2));
//           ps1.setString(3,rs.getString(4));
//           ps1.setString(4,rs.getString(7));
//            System.out.println(count++);
//           // ps1.setQueryTimeout(5);
//           try{
//               System.out.println(ps1.executeUpdate());}
//           catch(Exception e){
//             //  e.printStackTrace();
//           }
//           }
//           //return list;
//     }     
//      
         
         
    public static int getTotalTransactionDateWiseCentreWise(String startDate,int centre)throws SQLException{
         String getStudentTransactionDetailsQry="SELECT sum(gross_amount)FROM student_transaction WHERE centre_id=? and date_of_payement = ? order by date_of_payement";
         //int studentDetailId=0;
           ArrayList<StudentTransaction> list=new ArrayList<>();
           Connection conn=DBConnection.getConnection();
           PreparedStatement ps=conn.prepareStatement(getStudentTransactionDetailsQry);
           ps.setInt(1, centre);
           ps.setString(2,startDate);
         //  ps.setString(3,endDate);
            ps.setQueryTimeout(5);
           ResultSet rs=ps.executeQuery();
          if(rs.next()){
             return rs.getInt(1);
           }
           return 0;
     }     
         
         
         
}
