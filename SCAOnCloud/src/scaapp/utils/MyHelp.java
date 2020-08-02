/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import scaapp.dao.RegistrationDao;
import scaapp.dao.StudentDetailDao;
import scaapp.dao.StudentTransactionDao;
import scaapp.gui.FeePayment;
import scaapp.pojo.StudentDetail;
import scaapp.pojo.StudentTransaction;

/**
 *
 * @author Comp8
 */
public class MyHelp {
    public static void main(String[] args) throws SQLException {
        ArrayList <String> list= StudentDetailDao.getAllStudentsContactNo();
        for(String s : list){
            register(s);
        }
    }
   
   
    public static void register(String no){
         StudentDetail sd=null;
                
      try {
            
//                StudentTransactionDao.addStudentTransaction(st);
//                new GSTReceiptPrinter().printGstReciept(st,sd.getStudentName());
//                startSet();
                
              
                
                // 
            
               
               sd=StudentDetailDao.getStudentDetailsByContactNo(no);
  
                
               //sd=StudentDetailDao.getStudentDetailsByContactNo(studentId.getText().substring(0,10));
               sd.setStudentContact(no);
              
               System.out.println(sd);
            } catch (SQLException ex) {
                Logger.getLogger(FeePayment.class.getName()).log(Level.SEVERE, null, ex);
            }
//         if(first){
//            first=false;
//            return;
//                 }
         
             Date d=null;
             try {
          d=StudentTransactionDao.getDate();
                 //st.setTransactionId("MN/2018-2019/1008");
            } catch (Exception ex) {
                Logger.getLogger(FeePayment.class.getName()).log(Level.SEVERE, null, ex);
            }
            Calendar c=Calendar.getInstance();
            c.setTime(d);
            int year;
             int month = c.get(Calendar.MONTH);
                if(month>=3)
                    year=c.get(Calendar.YEAR);
                else
                    year=c.get(Calendar.YEAR)-1;
             int amount=Integer.parseInt(100+"");
             StudentTransaction st=new StudentTransaction();
             st.setEmployeeId(10);
             st.setStudentId(sd.getStudentContact());
            try {
               
            } catch (Exception ex) {
                Logger.getLogger(FeePayment.class.getName()).log(Level.SEVERE, null, ex);
            }
             st.setPayementModeId(1);
             st.setBatchId("MSKP430E17DEC18MWF");
             st.setCentreId(1);
             st.setGrossAmount(amount);
             st.setCgstAmount((int)(amount*0.09));
             st.setSgstAmount((int)(amount*0.09));
             st.setNetAmount(amount-(st.getSgstAmount()+st.getCgstAmount()));
             SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
             String date=sdf.format(d);
             st.setDateOfPayement(date);
            
            try {
                RegistrationDao.registerStudentInBatch(st.getStudentId(), st.getBatchId());
                //StudentTransactionDao.addStudentTransaction(st);
               // new GSTReceiptPrinter().printGstReciept(st,sd.getStudentName());
//                String s=sd.getStudentName();
//                Runnable r=()-> new GSTReceiptPrinter().printGstReciept(st,s);
//                Thread t=new Thread(r);
//                t.start();
                
               for(int i=0;i<10;i++){
                    st.setTransactionId("MN/"+year+"-"+(year+1)+"/"+StudentTransactionDao.getNewTransactionId(null));
                 StudentTransactionDao.addStudentTransaction(st);  
               }
                
                
                
                
                // 
            } catch (SQLException ex) {
                Logger.getLogger(FeePayment.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
}
}
