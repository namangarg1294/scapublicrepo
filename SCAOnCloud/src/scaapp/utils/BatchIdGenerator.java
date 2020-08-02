/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import scaapp.dao.BatchDao;
import scaapp.dao.CenterDao;
import scaapp.dao.CourseDao;
import scaapp.dao.EmployeeDao;

/**
 *
 * @author Comp8
 */
public class BatchIdGenerator {
    StringBuilder sb=new StringBuilder();
  public BatchIdGenerator()
  {
//        try {
//            String id=String.valueOf(BatchDao.getCount());
//            for(int i=0;i<4-id.length();i++)
//            {
//                sb.append("0");
//            }
//            sb.append(id+"-");
//        } catch (SQLException ex) {
//            Logger.getLogger(BatchIdGenerator.class.getName()).log(Level.SEVERE, null, ex);
//        }
   }
   public void setCenter(int id)
   {
        try {
            sb.append(CenterDao.getCenter(id).charAt(0));
        } catch (SQLException ex) {
            Logger.getLogger(BatchIdGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public void setFaculty(String emp)
   {
        try {
            String arr[]=emp.split(" ");
            for(int i=0;i<arr.length;i++)
            {
                sb.append(arr[i].charAt(0));
            }
        } catch (Exception ex) {
            Logger.getLogger(BatchIdGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   public void setCourse(String id)
   {
//        try {
//            String arr[]=CourseDao.getCourse(id).split(" ");
//            if(arr[0].equalsIgnoreCase("C++"))
//            arr[0]=arr[0].replaceAll("+","P");
//            else{
//            for(int i=0;i<arr.length;i++)
//            {
//                sb.append(arr[i].charAt(0));
//            }}
//        } catch (SQLException ex) {
//            Logger.getLogger(BatchIdGenerator.class.getName()).log(Level.SEVERE, null, ex);
//        }
       String []arr=id.split(" ");
       if(arr.length==1)
       {
           
           sb.append(arr[0].substring(0, 2));
           return;
       }
       for(String s:arr)
       sb.append(s.charAt(0));
   }
   public void setTime(String hh,String mm,String x)
   {
     sb.append(hh+mm+x);
   }
   public void setDate(Date date)
   {
       SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yy");
       String start=sdf.format(date);
      start= start.replaceAll("-", "");
       sb.append(start.toUpperCase());
   }
   public void setDays(String days){
       sb.append(days);
   }
   
   public StringBuilder getId()
   {
       return sb;
   }
}