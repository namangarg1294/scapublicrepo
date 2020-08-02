/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Date;
import org.apache.commons.net.ntp.NTPUDPClient; 
import org.apache.commons.net.ntp.TimeInfo;

public class TimeLookup {
    static String []timeServers = new String[]{"time.windows.com","time.nist.gov","time-a.nist.gov","time-nw.nist.gov","time-b.nist.gov"};
    public static Date getDate()  {
        Date realdate = null;
       
        for(int i=0;i<timeServers.length;i++){
            Date d = getDate(i);
            if(d!=null)
                return d;
            if(i==(timeServers.length-1))
                i=-1;
        }
        return null;
    }
    public static Date getDate(int i)  {
        Date realdate = null;
        try
        {
//            String TIME_SERVER = "time-a.nist.gov";
            NTPUDPClient timeClient = new NTPUDPClient();
            timeClient.setDefaultTimeout(400);
            InetAddress inetAddress = InetAddress.getByName(timeServers[i]);
            TimeInfo timeInfo = timeClient.getTime(inetAddress);
            long returnTime = timeInfo.getReturnTime();
            Date time = new Date(returnTime);
            long systemtime = System.currentTimeMillis();
            timeInfo.computeDetails();
            realdate = new Date(systemtime + timeInfo.getOffset());
                if(realdate!=null){
                    return realdate;
                }

//            System.out.println("Time from " + TIME_SERVER + ": " + realdate);
//            System.out.println(""+time.getTime());
        }
        catch(SocketTimeoutException ex){
            System.out.println("Exception "+ex);
        }
        catch(Exception ex)
        {
            System.out.println("Exception "+ex);
//                ex.printStackTrace();
        }
        return null; 
    }
    public static void main(String[] args) {
        System.out.println(getDate());
    } 
}