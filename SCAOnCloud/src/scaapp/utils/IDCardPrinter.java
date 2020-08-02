/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

/**
 *
 * @author Comp8
 */
                       /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.onbarcode.barcode.UPCA;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import scaapp.pojo.IDCardPrintingDetails;

/**
 *
 * @author Comp8
 */
public class IDCardPrinter implements Printable {
// String [] Names=
//     {"Prayatna Jain","Nupur Parihar","Naman Garg","Sachin Kapoor","Seema Joshi",
//     "Paramjeet Singh","Piyush Tiwari","Sakshi Garg","Ankit Jain","Akriti Jain","Priyansh Jain","Rajat Nigam",
//     "Khushboo","Amrita","Abhay","Mohnish","Ram"};
ArrayList<IDCardPrintingDetails> printingDataList=new ArrayList<>();
 //int currentPage=0;

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
 
//        if (page >0) { /* We have only one page, and 'page' is zero-based */
//            return NO_SUCH_PAGE;
//        }
        System.out.println(page);
if (page >printingDataList.size()/8) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }





//pf.setOrientation(PageFormat.LANDSCAPE);
 
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        // AffineTransform orig = g2d.getTransform();
                    // g2d.rotate(-Math.PI/2);
      // g2d.setTransform(AffineTransform.getRotateInstance(Math.toRadians(90),500, 1500));
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        //g2d.translate(pf.getImageableY(), pf.getImageableX());
       BufferedImage image=null;
       BufferedImage logo=null;
 
        /* Now we perform our rendering */
          //g.drawString(page+"", 10, 10);
        //String [] Names={"Prayatna Jain","Nupur Parihar","Naman Garg","Sachin Kapoor","Seema Joshi","Paramjeet Singh","Piyush Tiwari","Sakshi Garg","Ankit Jain","Akriti Jain","Priyansh Jain"};
        int x=10;
        int y=40;
        int width=255;
        int length=165;
        int gap=180;
        int n=0;
        int m=0;
        int padding=0;
          // String text = "I don't see the problem";
           // FontMetrics fm = g2d.getFontMetrics();
           // int q = (getWidth() - fm.stringWidth(text)) / 2;
            //int y = ((getHeight-() - fm.getHeight()) / 2) + fm.getDescent();
           
           
                     //g2.draw(barra);
                   
                   //  g2.setColor(Color.BLACK);
                   // g2d.drawString(text, x, y);
                    // g2d.setTransform(orig);
           
           
           // g2d.drawString(text, x, y);
            //g2d.dispose();
         try {
            //image =ImageIO.read(new File("D:\\Aapa\\barcode\\barcode-code1288.jpg"));
            System.out.println("Working Directory = " +
            System.getProperty("user.dir"));
            logo=resize(ImageIO.read(new File("src\\resources\\logo.png")),70,25);
        } catch (IOException ex) {
            Logger.getLogger(IDCardPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
      UPCA barcode = new UPCA();
         
          
        for(int i=0;i<8;i++){
           
            System.out.println("index "+(i+page*8));
            
            if((i+page*8)>printingDataList.size()-1)
                break;
            
            IDCardPrintingDetails idCPD=printingDataList.get(i+page*8);
            
            int flag=0;
            try {
           
barcode.setData(idCPD.getStudentId()+"1"); 
barcode.setX(1); 
barcode.drawBarcode("src\\barcode\\"+idCPD.getStudentId()+".png"); 

        } catch (Exception ex) {
            Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
            
            
            
            
            //Barcode.getBarcode().drawBarcode(idCPD.getStudentId());
             try {
            image =ImageIO.read(new File("src\\barcode\\"+idCPD.getStudentId()+".png"));
            //logo=resize(ImageIO.read(new File("D:\\sca\\logo.png")),70,25);
        } catch (IOException ex) {
            Logger.getLogger(IDCardPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(i%2!=0){
                x=300;
                g.drawImage(logo, x, y+2+gap*n, null); 
                // g.drawString("Session "+idCPD.getSession(), x+90, y+18+gap*n);
                g.drawString(idCPD.getBatchId(), x+80, y+18+gap*n);
                g.drawRect(x, y+gap*n, width,length);
                //g.drawString("Batch Id: "+idCPD.getBatchId(),x+160,y+20+gap*n+25);
                g.drawString("Name: "+idCPD.getStudentName(), x+20, y+20+gap*n+25);
                g.drawString("Course: "+idCPD.getCourseName(),x+20,y+40+gap*n+20);
                //g.drawString("Time: "+idCPD.getBatchTime()+"  Sem: "+idCPD.getSem(),x+20,y+60+gap*n+15);
                g.drawString("Time: "+idCPD.getBatchTime(),x+20,y+60+gap*n+15);
                g.drawString("Days: "+idCPD.getDays(),x+20,y+80+gap*n+10);
                g.drawImage(image, x+10, y+85+gap*n+20, null);
                g.drawRect(x+160, y+25+gap*n, 80,100);
                g.drawString("PHOTO",x+175,y+75+gap*n);
                String centre=null;
                String phno=null;
                if(idCPD.getBatchId().startsWith("M"))
                { centre="MANSAROVAR";
                  phno="07554271659";  
                }
                else{
                    centre="INDRAPURI";
                    phno="07554274659";  
                }
                g.drawString(centre,x+160,y+140+gap*n);
                g.drawString(phno,x+160,y+155+gap*n);
                n++;
                
                System.out.println("idcard");
                System.out.println("Naman ,prayatna in if");
//                IDCardImageUtil idcp = new IDCardImageUtil(width, length, gap, n, idCPD, image, logo);
//                try {
//                    idcp.createAndSendIdCard();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    System.out.println("Wait 5 minutes");;
//                }
                               //Mailer
//                IDCardImageUtil idcp = new IDCardImageUtil(width, length, gap, n, idCPD, image, logo);
//                idcp.createAndSendIdCard();
//                BufferedImage bufferedImage = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);
//
//                Graphics2D g1 = bufferedImage.createGraphics();
//                g1.setColor(Color.WHITE);
//                g1.fillRect(0, 0+gap*n, width,length);
//                g1.setColor(Color.BLACK);
//                g1.drawImage(logo, 0, 0+2+gap*n, null); 
//                g1.drawString(idCPD.getBatchId(), 0+80, 0+18+gap*n);
//                g1.drawString("Name: "+idCPD.getStudentName(), 0+20, 0+20+gap*n+25);
//                g1.drawString("Course: "+idCPD.getCourseName(),0+20,0+40+gap*n+20);
//                g1.drawString("Time: "+idCPD.getBatchTime(),0+20,0+60+gap*n+15);
//                g1.drawString("Days: MWF  Sem: 3",0+20,0+80+gap*n+10);
//                g1.drawImage(image, 0+10, 0+85+gap*n+20, null);
//                g1.drawRect(0+160, 0+35+gap*n+20, 80,100);
//                g1.drawString("PHOTO",0+175,0+75+gap*n+20);
//                g1.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
//                AffineTransform at = new AffineTransform();
//                at.setToRotation(Math.PI / 6.0);
//                g1.setTransform(at);
//                g1.setColor(Color.BLACK);
//                Color curr = g1.getColor();
//                g1.setColor(new Color(curr.getRed(), curr.getGreen(), curr.getBlue(), 0.2f));
//                g1.drawString("DIGITAL COPY", 50, 23);
//                
//                try{
//                    // Save as JPEG
//                    String imgName = idCPD.getStudentName()+"_"+idCPD.getEmail()+"_"+idCPD.getCourseName()+".png";
//;                    File file = new File(imgName);
//                    //File file = new File("cache\\idcards\\"+imgName);
//                    ImageIO.write(bufferedImage, "png", file);
//                    System.out.println("Image saved");
//                }
//                catch(IOException ex){
//                    ex.printStackTrace();
//                }
                
            }
            else{
                x=10;
                g.drawImage(logo, x, y+2+gap*n, null); 
                
                //g.drawString("Session "+idCPD.getSession(), x+90, y+18+gap*n);
                g.drawString(idCPD.getBatchId(), x+80, y+18+gap*n);
                g.drawRect(x, y+gap*m, width,length);
                //g.drawString("Batch Id: "+idCPD.getBatchId(),x+160,y+20+gap*n+25);
                //g.drawString("B0001-MSKC630E16DEC18",x+160,y+20+gap*n+25);
                g.drawString("Name: "+idCPD.getStudentName(), x+20, y+20+gap*m+25);
                g.drawString("Course: "+idCPD.getCourseName(),x+20,y+40+gap*m+20);
                //g.drawString("Time: "+idCPD.getBatchTime()+"  Sem: "+idCPD.getSem(),x+20,y+60+gap*n+15);
                g.drawString("Time: "+idCPD.getBatchTime(),x+20,y+60+gap*n+15);
                g.drawString("Days: "+idCPD.getDays(),x+20,y+80+gap*n+10);
                g.drawImage(image, x+10, y+85+gap*m+20, null);
                g.drawRect(x+160, y+25+gap*m, 80,100);
                g.drawString("PHOTO",x+175,y+75+gap*m);
                String centre=null;
                String phno=null;
                if(idCPD.getBatchId().startsWith("M"))
                { centre="MANSAROVAR";
                  phno="07554271659";  
                }
                else{
                    centre="INDRAPURI";
                    phno="07554274659";  
                }
                g.drawString(centre,x+160,y+140+gap*n);
                g.drawString(phno,x+160,y+155+gap*n);
                m++;

                System.out.println("idcard");
//                IDCardImageUtil idcp = new IDCardImageUtil(width, length, gap, n, idCPD, image, logo);
//                try {
//                    System.out.println("hashcode of idcp:"+idcp.hashCode());
//                    idcp.createAndSendIdCard();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    System.out.println("Wait 5 minutes");;
//                }
                    //Mailer
//                IDCardImageUtil idcp = new IDCardImageUtil(width, length, gap, n, idCPD, image, logo);
//                idcp.createAndSendIdCard();
//                IDCardImageUtil idcp = new IDCardImageUtil(width, length, gap, n, idCPD, image, logo);
//                idcp.createAndSendIdCard();
//                BufferedImage bufferedImage = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);
//
//                Graphics2D g1 = bufferedImage.createGraphics();
//                g1.setColor(Color.WHITE);
//                g1.fillRect(0, 0+gap*n, width,length);
//                g1.setColor(Color.BLACK);
//                g1.drawImage(logo, 0, 0+2+gap*n, null); 
//                g1.drawString(idCPD.getBatchId(), 0+80, 0+18+gap*n);
//                g1.drawString("Name: "+idCPD.getStudentName(), 0+20, 0+20+gap*n+25);
//                g1.drawString("Course: "+idCPD.getCourseName(),0+20,0+40+gap*n+20);
//                g1.drawString("Time: "+idCPD.getBatchTime(),0+20,0+60+gap*n+15);
//                g1.drawString("Days: MWF  Sem: 3",0+20,0+80+gap*n+10);
//                g1.drawImage(image, 0+10, 0+85+gap*n+20, null);
//                g1.drawRect(0+160, 0+35+gap*n+20, 80,100);
//                g1.drawString("PHOTO",0+175,0+75+gap*n+20);
//                g1.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
//                AffineTransform at = new AffineTransform();
//                at.setToRotation(Math.PI / 6.0);
//                g1.setTransform(at);
//                Color curr = g1.getColor();
//                g1.setColor(new Color(curr.getRed(), curr.getGreen(), curr.getBlue(), 0.2f));
//                g1.drawString("DIGITAL COPY", 50, 23);
//
//                try{
//                    // Save as JPEG                    
//                    String imgName = idCPD.getStudentName()+"_"+idCPD.getEmail()+"_"+idCPD.getCourseName()+".png";
//
//                    File file = new File(imgName);
//                    //File file = new File("cache\\idcards\\"+imgName);
//                    System.out.println(file.getAbsolutePath());
//                    ImageIO.write(bufferedImage, "png", file);
//                    System.out.println("Image saved");
//                }   
//                catch(IOException ex){
//                    ex.printStackTrace();
//                }
//                } catch (Exception ex) {
//                    System.out.println("Wait 5 minutes");;
//                }
            }
        }
        x=300;
//         for(int i=0;i<4;i++){
//             try {
//            image = ImageIO.read(new File("D:\\sca\\AllBarCode\\image-1-123456700018.jpg"));
//        } catch (IOException ex) {
//            Logger.getLogger(HelloWorldPrinter.class.getName()).log(Level.SEVERE, null, ex);
//        }
//             g.drawRect(x, y+gap*i, width,length);
//        g.drawString("Name: "+Names[i+5], x+20, y+20+gap*i);
//        g.drawString("Course: JAVA(P)",x+20,y+40+gap*i);
//         g.drawImage(image, x+20, y+60+gap*i, null);
//          g.drawRect(x+140, y+25+gap*i, 60,80);
//            g.drawString("PHOTO",x+150,y+65+gap*i);
//        }
         
         
         
         
         
         
         
         
         /*g.drawRect(80, 80, 200,130);
        g.drawString("Name: Naman Garg", 100, 100);
        g.drawString("Phno: 9752345221",100,120);
         g.drawImage(image, 100, 140, null);
        
        g.drawRect(300, 80, 200,130);
        g.drawString("Name: Prayatna Jain", 320, 100);
        g.drawString("Phno: 9752345222",320,120);
         g.drawImage(image, 320, 140, null);
        
         g.drawRect(80, 230, 200,130);
        g.drawString("Name: Nupur Parihar", 100, 250);
        g.drawString("Phno: 9752345223",100,270);
         g.drawImage(image, 100, 290, null);
         
         
        g.drawRect(300, 230, 200,130);
        g.drawString("Name: Piyush tiwari", 320, 250);
        g.drawString("Phno: 9752345224",320,270);
        g.drawImage(image, 320, 290, null);*/
     
        /* tell the caller that this page is part of the printed document */
       // currentPage+=7;
        
        return PAGE_EXISTS;
    }
 
    public void printIdCard(ArrayList<IDCardPrintingDetails> printingDataList) {
       this.printingDataList=printingDataList;
         PrinterJob job = PrinterJob.getPrinterJob();
         
         job.setPrintable(this);
         
//        PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//         //aset.add(DialogTypeSelection.NATIVE);
//        PrintService[] services = PrintServiceLookup.lookupPrintServices(
//                            DocFlavor.INPUT_STREAM.JPEG, null);
//        boolean ok=false;
// //PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
// if (services.length > 0) {
//    PrintService service =  ServiceUI.printDialog(null, 50, 50,
//                                               services, services[0],
//                                               null,
//                                               aset);
//    if (service != null) {
//   ok=true;
//    }
// }
       //boolean ok=job.printDialog(aset);
        boolean ok = job.printDialog();
         
         
         if (ok) {
             try {
                  job.print();
                  
                  
                  
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
         }
    }
 
//    public static void main(String args[]) {
//  
//        UIManager.put("swing.boldMetal", Boolean.FALSE);
//        JFrame f = new JFrame("Hello World Printer");
//        f.addWindowListener(new WindowAdapter() {
//           public void windowClosing(WindowEvent e) {System.exit(0);}
//        });
//        JButton printButton = new JButton("Print Hello World");
//        printButton.addActionListener(new IDCardPrinter());
//        f.add("Center", printButton);
//        f.pack();
//        f.setVisible(true);
//    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
}  
}

