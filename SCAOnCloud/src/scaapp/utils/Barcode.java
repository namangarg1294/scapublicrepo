/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import com.onbarcode.barcode.Code128;
import com.onbarcode.barcode.EAN13;
import com.onbarcode.barcode.UPCA;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Barcode {
    static Barcode barcode;
  static{
        try{
          barcode=new Barcode();
    }
        catch(Exception e)
        {
          System.out.println("Exception in barcode class");
        }
    }
    /**
     * @param args the command line arguments
     */
  public static Barcode getBarcode(){
      return barcode;
  }
    public void drawBarcode(String studentId) {
        try {
            // TODO code application logic here
            
// Select a barcode type to create a Java barcode object
UPCA barcode = new UPCA();

// Set barcode data text to encode
barcode.setData(studentId+"1"); 

// Set barcode data text to encode
barcode.setX(1); 

// Generate barcode & encode into GIF format
//barcode.drawBarcode("C://barcode-code128.gif"); 
//
//// Generate barcode & encode into JPG format
barcode.drawBarcode(studentId+".png"); 
//
//// Generate barcode & encode into PNG format
//barcode.drawBarcode("C://barcode-code128.png");
//
//// Generate barcode & encode into EPS format
//barcode.drawBarcode2EPS("C://barcode-code128.eps"); 
//
//// Generate barcode & print into Graphics2D object
//barcode.drawBarcode("Java Graphics2D object"); 
        } catch (Exception ex) {
            Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
}
