/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Naman
 */
public class OldBatchDetector {
    public static boolean isOld(String batchStartingDate,Date today) throws ParseException{
      
       Calendar c=Calendar.getInstance();
       c.setTime(today);
       int year;
       year=c.get(Calendar.YEAR);
       int month=c.get(Calendar.MONTH);
       SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");
       Date batchDate=sdf.parse(batchStartingDate);
        System.out.println(batchDate);
        
        
        Calendar myCalendar = new GregorianCalendar(year, 0, 1);
        Date janDate = myCalendar.getTime();
        myCalendar = new GregorianCalendar(year, 4, 31);
        Date mayDate = myCalendar.getTime();
        myCalendar = new GregorianCalendar(year, 5,1);
        Date juneDate = myCalendar.getTime();
        myCalendar = new GregorianCalendar(year, 11, 31);
        Date decDate = myCalendar.getTime();
//        System.out.println(batchDate.after(janDate));
//        System.out.println(batchDate.before(mayDate));
//        System.out.println(today);
//        System.out.println(mayDate);
        int session=0;
        if((today.after(janDate)||today.equals(janDate)) && (today.before(mayDate)||today.equals(mayDate)))
                session=1;
        if((today.after(juneDate)||today.equals(juneDate)) && (today.before(decDate)||today.equals(decDate)))
                session=2;
        if(session==1 && (batchDate.after(janDate)||batchDate.equals(janDate))&&(batchDate.before(mayDate)||batchDate.equals(mayDate))){
            System.out.println("New Student");
            return false;
        }
        else if(session==2 && (batchDate.after(juneDate)||batchDate.equals(juneDate))&&(batchDate.before(decDate)||batchDate.equals(decDate))){
            System.out.println("New Student");
            return false;
        }
        else if(batchDate.after(today))
        {
             System.out.println("New Student");
            return false;
        }
        else{
            
            System.out.println("Old student");
            return true;
        }
        
      
    }
    public static void main(String[] args) throws ParseException {
       isOld(null,null);
    }
}
