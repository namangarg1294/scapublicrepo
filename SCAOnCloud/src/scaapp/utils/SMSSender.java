/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import scaapp.dao.ReportRecordDao;
import scaapp.dao.StudentTransactionDao;
import static scaapp.dao.StudentTransactionDao.getTotalTransactionDateWiseCentreWise;

/**
 *
 * @author Comp8
 */
public class SMSSender {
    public static void main(String []args) throws SQLException {
         String c_id="8120100004";//Sharma sir
         //String c_id="9826086245";//Sachin sir
        // String c_id="9752345221"; //Naman garg
    String amount="10000";
    System.out.println("hello from otp sender");
    //int otp=(int)(Math.random()*10000);
    //String ot=otp+"";
      Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        //cal.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
       // String start=sdf.format(cal.getTime());
       
    
    // SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
     //Calender c=Calender.
     String date=sdf.format(cal.getTime());
     
     try{
         ReportRecordDao.addReport(date);
     }
     catch(Exception e){
         System.out.println(e);
         return;
     }
     
     
    int amountInd=getTotalTransactionDateWiseCentreWise(date,2);
    int amountMan=getTotalTransactionDateWiseCentreWise(date,1);
     String authkey = "156888A53vJzZT5948aa55";     
    String mobiles = "91"+c_id.trim();
    //String mobiles = request.getParameter("phoneno");
    String senderId = "SCACLD";
    //char[] getotp = GenerateOTP.iha_otp();
   // String otp=String.valueOf(getotp);
    
    String message = "Message from SCAONCLOUD\n" +
"Yesterday's collection date-"+sdf2.format(cal.getTime())+
"\nIndrapuri-"+amountInd+"/-"+
"\nMansarovar-"+amountMan+"/-";
    int setotp = Integer.parseInt(cal.get(Calendar.YEAR)+"");		
    URLConnection myURLConnection=null;
    URL myURL=null;
    BufferedReader reader=null;

    String encoded_message=URLEncoder.encode(message);
    String mainUrl="http://my.msgwow.com/api/otp.php?";

    StringBuilder sbPostData= new StringBuilder(mainUrl);
    sbPostData.append("authkey="+authkey);
    sbPostData.append("&mobiles="+mobiles);
    sbPostData.append("&message="+encoded_message);
    sbPostData.append("&sender="+senderId);
    sbPostData.append("&otp="+setotp);

    mainUrl = sbPostData.toString();
     try
        {
                
           myURL = new URL(mainUrl);
           myURLConnection = myURL.openConnection();
           myURLConnection.connect();
           reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
        
           String resp;
           while ((resp = reader.readLine()) != null)
           System.out.println(resp);
           reader.close();
        }
        catch (IOException e){
                e.printStackTrace();
        }
     catch(Exception e){
         e.printStackTrace();
     }
     System.out.println("on line 66");
    
  
    }
}
