/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Comp8
 */
public class DBConnection {
     private static Connection conn;
      private static Connection conn2;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String user;
    private static String batchType="NORMAL";
//    private static String projectURL="jdbc:mysql://43.242.215.132/tonyandg_scaoncloud";
//    private static String batchesURL="jdbc:mysql://43.242.215.132/tonyandg_batches";
//    private static String userName="sca";
//    private static String password="nyjyzeza6";
   // private static String projectURL="jdbc:mysql://103.120.178.143/scaoncloud";
   // private static String batchesURL="jdbc:mysql://103.120.178.143/batches";
    
    private static String projectURL="jdbc:mysql://103.146.177.123:3306/sca_projects";
    private static String batchesURL="jdbc:mysql://103.146.177.123:3306/sca_batches";
    
    private static String userName="";
    private static String password="";
   // private static String batchType="PROJECT";
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        //Class.forName("oracle.jdbc.OracleDriver");
       //conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sca_on_cloud","root","");
         // conn=DriverManager.getConnection("jdbc:mysql://43.242.215.132/tonyandg_scaoncloud","sca","nyjyzeza6");
          //conn.setNetworkTimeout(null, 1);
          
          //production url
//            if(batchType.equalsIgnoreCase("PROJECT"))
//         conn=DriverManager.getConnection("jdbc:mysql://sg3plvcpnl162431.prod.sin3.secureserver.net/scaoncloud","scaoncloud","9752345221@Naman");
//          else
//            conn=DriverManager.getConnection("jdbc:mysql://sg3plvcpnl162431.prod.sin3.secureserver.net/batchessca","scaoncloud","9752345221@Naman");
//    
      //   conn=DriverManager.getConnection("jdbc:mysql://148.72.208.56/scaoncloud","scaoncloud","Na@9752345221");
        Credentials cr=DBConnectionHelper.getCredentials();
            userName=cr.getId();
            password=cr.getPass();
             if(batchType.equalsIgnoreCase("PROJECT"))
         conn=DriverManager.getConnection(projectURL,userName,password);
          else
            conn=DriverManager.getConnection(batchesURL,userName,password);
    
            
        }
        catch(Exception e)
        {
          System.out.println("STMT PRE while connecting");
          e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        
         try{
            ps=conn.prepareStatement("select * from centre_details");        
           
            rs=ps.executeQuery();
            rs.next();
            }   
        catch(Exception sq){
            System.out.println("In catch constructor");
            try{
            Class.forName("com.mysql.jdbc.Driver");
        //     conn=DriverManager.getConnection("jdbc:mysql://148.72.208.56/scaoncloud","scaoncloud","Na@9752345221");
        
           //conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/iha_platform","root","");
         // conn=DriverManager.getConnection("jdbc:mysql://43.242.215.132/tonyandg_scaoncloud","sca","nyjyzeza6");
           //conn.setNetworkTimeout(null, 1);
            //production url
          //conn=DriverManager.getConnection("jdbc:mysql://sg3plvcpnl162431.prod.sin3.secureserver.net/scaoncloud","scaoncloud","9752345221@Naman");
//          if(batchType.equalsIgnoreCase("PROJECT"))
//         conn=DriverManager.getConnection("jdbc:mysql://sg3plvcpnl162431.prod.sin3.secureserver.net/scaoncloud","scaoncloud","9752345221@Naman");
//          else
//            conn=DriverManager.getConnection("jdbc:mysql://sg3plvcpnl162431.prod.sin3.secureserver.net/batchessca","scaoncloud","9752345221@Naman");
//           //conn=DriverManager.getConnection("jdbc:mysql://localhost/sca_on_cloud","root","");
  
             
             if(batchType.equalsIgnoreCase("PROJECT"))
         conn=DriverManager.getConnection(projectURL,userName,password);
             else
          conn=DriverManager.getConnection(batchesURL,userName,password);   
            }
        catch(Exception e)
        {
          System.out.println("Error in constructor will connecting "+e);
          e.printStackTrace();
          
        }
            }
        
        finally
         {       
        return conn;
    }
    }
    
    
//      public static Connection getConnection2(){
//        
//         try {
//             conn2=DriverManager.getConnection("jdbc:mysql://43.242.215.132/tonyandg_scalab","sca","nyjyzeza6");
//         } catch (SQLException ex) {
//             Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
//         }
//         return conn2;
//    }
    
    
     public static void setBatchType(String batchType){
     DBConnection.batchType=batchType;
      try {
        //   conn=DriverManager.getConnection("jdbc:mysql://148.72.208.56/scaoncloud","scaoncloud","Na@9752345221");
        
//             if( DBConnection.batchType.equalsIgnoreCase("PROJECT"))
//             conn=DriverManager.getConnection("jdbc:mysql://sg3plvcpnl162431.prod.sin3.secureserver.net/scaoncloud","scaoncloud","9752345221@Naman");
//              else
//            conn=DriverManager.getConnection("jdbc:mysql://sg3plvcpnl162431.prod.sin3.secureserver.net/batchessca","scaoncloud","9752345221@Naman");
     
       
             if(batchType.equalsIgnoreCase("PROJECT"))
          conn=DriverManager.getConnection(projectURL,userName,password);
             else
            conn=DriverManager.getConnection(batchesURL,userName,password);
      
      } catch (SQLException ex) {
         Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
     }
     
     }
     
    public static String getBatchType(){
        return  DBConnection.batchType;
    }
}
