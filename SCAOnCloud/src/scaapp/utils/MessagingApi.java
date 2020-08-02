/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author Naman
 */
public class MessagingApi {
    public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException {
        String urlParameters  = "<MESSAGE>\n" +
"    <AUTHKEY>156888A53vJzZT5948aa55</AUTHKEY>\n" +
"    <ROUTE>transactional</ROUTE >\n" +
"    <CAMPAIGN>BULKSMS</CAMPAIGN >\n" +
"    <COUNTRY>91</COUNTRY>\n" +
"    <SENDER>SCASEC</SENDER>\n" +
"    <SMS TEXT=\"hii this is test message\">\n" +
"	    <ADDRESS TO=\"9752345221\"></ADDRESS>\n" +
"	    <ADDRESS TO=\"7974909360\"></ADDRESS>\n" +
"    </SMS>\n" +
"</MESSAGE>";
        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int    postDataLength = postData.length;
        String request        = "http://sms.fly.biz/api/postsms.php";
        URL    url            = new URL( request );
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();           
        conn.setDoOutput( true );
        conn.setInstanceFollowRedirects( false );
        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
        conn.setRequestProperty( "charset", "utf-8");
        conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        conn.setUseCaches( false );
        try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
           wr.write( postData );
        }
      
    }
}
