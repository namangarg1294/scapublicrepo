/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Properties;

/**
 *
 * @author HP
 */
public class DBConnectionHelper {
     public  static Credentials getCredentials() {  
        // Getting encoder  
         
        // Creating byte array  
//        byte  byteArr[] = {1,2};  
//        // encoding byte array  
//        byte byteArr2[] = encoder.encode(byteArr);  
//        System.out.println("Encoded byte array: "+byteArr2);  
//        byte byteArr3[] = new byte[5];                // Make sure it has enough size to store copied bytes  
//        int x = encoder.encode(byteArr,byteArr3);    // Returns number of bytes written  
//        System.out.println("Encoded byte array written to another array: "+byteArr3);  
//        System.out.println("Number of bytes written: "+x);  
      
        // Encoding string  
          Credentials cr=new Credentials();
         try (InputStream input = new FileInputStream("src/resources/server.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            // get the property value and print it out
            cr.setId(prop.getProperty("id"));
            cr.setPass(prop.getProperty("pass"));
          //  System.out.println(prop.getProperty("db.password"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
      return cr;
    }  
}
