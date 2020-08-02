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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import scaapp.dao.BatchDao;
import scaapp.pojo.BatchDetailsDisplay;
import scaapp.pojo.StudentTransaction;

/**
 *
 * @author Comp8
 */
public class DuplicateGSTReceiptPrinter implements Printable, ActionListener {
 String [] Names=
     {"Prayatna Jain","Nupur Parihar"};
 StudentTransaction st;
 String name;
 String emp;
 String date;
 
 
 //int currentPage=0;
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
 
//        if (page >0) { /* We have only one page, and 'page' is zero-based */
//            return NO_SUCH_PAGE;
//        }
        
        System.out.println(page);
if (page >0) { /* We have only one page, and 'page' is zero-based */
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
       BufferedImage dupli=null;
       
 
        /* Now we perform our rendering */
          //g.drawString(page+"", 10, 10);
        //String [] Names={"Prayatna Jain","Nupur Parihar","Naman Garg","Sachin Kapoor","Seema Joshi","Paramjeet Singh","Piyush Tiwari","Sakshi Garg","Ankit Jain","Akriti Jain","Priyansh Jain"};
        int x=10;
        int y=40;
        int width=455;
        int length=165;
        int gap=363;
        int n=0;
        int m=0;
        int padding=0;
          // String text = "I don't see the problem";
           // FontMetrics fm = g2d.getFontMetrics();
           // int x = (getWidth() - fm.stringWidth(text)) / 2;
            //int y = ((getHeight-() - fm.getHeight()) / 2) + fm.getDescent();
           
           
                     //g2.draw(barra);
                   
                   //  g2.setColor(Color.BLACK);
                   // g2d.drawString(text, x, y);
                    // g2d.setTransform(orig);
           
           
           // g2d.drawString(text, x, y);
            //g2d.dispose();
          g.drawLine(0, y+363, x+600, y+363);
        
        for(int i=0;i<2;i++){
            UPCA barcode = new UPCA();
            try {
           
barcode.setData(st.getStudentId()+"1"); 
barcode.setX(1); 
barcode.drawBarcode("src\\barcode\\"+st.getStudentId()+".png"); 

        } catch (Exception ex) {
            Logger.getLogger(Barcode.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
            
            
            try {
            image =ImageIO.read(new File("src\\barcode\\"+st.getStudentId()+".png"));
            logo=ImageIO.read(new File("src\\resources\\logo.png"));
            dupli=ImageIO.read(new File("src\\resources\\duplicatereceiptbg.png"));        
        } catch (IOException ex) {
            Logger.getLogger(DuplicateGSTReceiptPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("index "+(i+page*8));
            if((i+page*8)>Names.length-1)
                break;
            
            BatchDetailsDisplay bdd=null;
            try {
                //            if(i%2!=0){
//                x=10;
                //g.drawLine(line.x1, line.y1, line.x2, line.y2);
                bdd=BatchDao.showRecord(st.getBatchId());
            } catch (SQLException ex) {
                Logger.getLogger(DuplicateGSTReceiptPrinter.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");
            String today=sdf.format(new Date());
             //g.drawImage(dupli, x, y+gap*n, null);
             g.drawImage(dupli, x-10, y+gap*n, null);
                 g.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
                 g.drawImage(logo, x, y+35+gap*n, null); 
                 g.drawString("TAX-INVOICE",x+240,y+gap*n+20);
                 g.drawString("HO : PB-5, Top Floor, Mansarovar Complex, Shivaji Nagar,", x+180, y+50+gap*n);
                 g.drawString("Bhopal-462016 T : 0755-4271659 / 0755-4273659", x+180, y+70+gap*n);
                 g.drawString("G.S.T - 23AAOFS7475B1Z1", x+180, y+90+gap*n);
                 g.drawString("Receipt No. "+st.getTransactionId(), x, y+140+gap*n);
                 g.drawString("Date of Payement- "+date, x+380, y+140+gap*n);
                 g.drawString("Received with thanks from Ms/Mr   -   "+name.toUpperCase(), x, y+160+gap*n);
                 g.drawString("Net Amount  -  "+Math.round((st.getGrossAmount()/1.18) * 100.0) / 100.0, x, y+180+gap*n);
                 g.drawString("By Cheque No. :  -", x, y+200+gap*n);
                 g.drawString("Bank :   -", x+220, y+200+gap*n);
                 g.drawString("Date :   -", x+440, y+200+gap*n);
                 g.drawString("For :  "+bdd.getCourseName()+"  ("+st.getBatchId()+")", x, y+220+gap*n);
                 g.drawString("CGST @ 9% - Rs. "+Math.round((((st.getGrossAmount()/1.18)*0.09 )* 100.0))/100.0, x, y+240+gap*n);
                 g.drawString("SGST @ 9% - Rs. "+Math.round((((st.getGrossAmount()/1.18)*0.09 )* 100.0))/100.0, x, y+260+gap*n);
                 g.drawString("Cashier Name -  "+emp, x, y+280+gap*n);
                 g.drawString("Rs. - "+st.getGrossAmount(), x, y+300+gap*n);
                 g.drawString("For SHARMA COMPUTER ACADEMY", x+350, y+300+gap*n);
                 g.drawString("BO : 38, Sector A, Indrapuri, Opposite Bhel Gate No. 1, Raisen Road, Bhopal -462023  T:0755-4274659", x, y+345+gap*n);
                 g.setFont(new Font("TimesRoman", Font.PLAIN, 8)); 
                 g.drawString("Note: 1. Fee not refundable and to be paid in lumpsum.", x, y+315+gap*n);
                 g.drawString("         2. It is compulsory to submit 'Break Application' before discontinuing Otherwise fee deposited will Lapse.", x, y+325+gap*n);
                 g.drawImage(image, x+350, y+220+gap*n, null);
                
                 
                 if(i==1){
                    BufferedImage bufferedImage = new BufferedImage(560, 360, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g1 = bufferedImage.createGraphics();
                    g1.setColor(Color.WHITE);
                    g1.fillRect(0, 0, 560,360);
                    g1.setColor(Color.BLACK);
                    g1.drawImage(logo, 0, 0+35+0*n, null); 
                    g1.drawString("TA0-INVOICE",0+240,0+0*n+20);
                    g1.drawString("HO : PB-5, Top Floor, Mansarovar Comple0, Shivaji Nag1ar,", 0+180, 0+50+0*n);
                    g1.drawString("Bhopal-462016 T : 0755-4271659 / 0755-4273659", 0+180, 0+70+0*n);
                    g1.drawString("g1.S.T - 23AAOFS7475B1Z1", 0+180, 0+90+0*n);
                    g1.drawString("Receipt No. "+st.getTransactionId(), 0, 0+140+0*n);
                    g1.drawString("Date of Payement- "+date, 0+380, 0+140+0*n);
                    g1.drawString("Received with thanks from Ms/Mr   -   "+name.toUpperCase(), 0, 0+160+0*n);
                    g1.drawString("The Sum Of Rupees  -  "+NumberToWord.getName((int)st.getNetAmount()), 0, 0+180+0*n);
                    g1.drawString("B0 Cheque No. :  -", 0, 0+200+0*n);
                    g1.drawString("Bank :   -", 0+220, 0+200+0*n);
                    g1.drawString("Date :   -", 0+440, 0+200+0*n);
                    g1.drawString("For :  "+bdd.getCourseName()+"  ("+st.getBatchId()+")", 0, 0+220+0*n);
                    g1.drawString("CGST @ 9% - Rs. "+st.getCgstAmount(), 0, 0+240+0*n);
                    g1.drawString("SGST @ 9% - Rs. "+st.getSgstAmount(), 0, 0+260+0*n);
                    g1.drawString("Cashier Name -  "+emp, 0, 0+280+0*n);
                    g1.drawString("Rs. - "+st.getGrossAmount(), 0, 0+300+0*n);
                    g1.drawString("For SHARMA COMPUTER ACADEM0", 0+350, 0+300+0*n);
                    g1.drawString("BO : 38, Sector A, Indrapuri, Opposite Bhel g1ate No. 1, Raisen Road, Bhopal -462023  T:0755-4274659", 0, 0+345+0*n);
                    g1.setFont(new Font("TimesRoman", Font.PLAIN, 8)); 
                    g1.drawString("Note: 1. Fee not refundable and to be paid in lumpsum.", 0, 0+315+0*n);
                    g1.drawString("         2. It is compulsor0 to submit 'Break Application' before discontinuing1 Otherwise fee deposited will Lapse.", 0, 0+325+0*n);
                    g1.drawImage(image, 0+350, 0+220+0*n, null);
                     //g1.drawImage(dupli,0, 0+140+0*n, null);
                    // Save as JPEG
                    String imgName = name.toUpperCase()+"-"+st.getTransactionId().replaceAll("\\\\", "-").replaceAll("/", "-")+"-"+st.getEmail()+".png";
                    System.out.println(imgName);
                    File file = new File("src\\receipt\\"+imgName);
                    //File file = new File("cache\\idcards\\"+imgName);
                    final String name1 = name;
                    final BatchDetailsDisplay b = bdd;
                    System.out.println(st.getEmail());
                    final String email = st.getEmail();
                    Thread th =new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                ImageIO.write(bufferedImage, "png", file);
                                SendAttachmentInEmail.sendMessage("scalivedigital@gmail.com", "9752345221", email, "SCA", "Digital Receipt -SCA "+new Date(), "Hi "+name1+","+" PFA your Digital receipt for the "+b.getCourseName()+" course", "src\\receipt\\"+imgName);
                                System.out.println("Image saved");
                            }
                            catch(IOException ex){
                                ex.printStackTrace();
                            }
                        }       
                    });
                    if(!file.exists()){
                       // th.start();
                    }
                }
                 
             //g.drawRect(x, y+gap*n, width,length);
//        g.drawString("Batch Id: SCA-X",x+160,y+20+gap*n+25);
//        g.drawString("Name: "+Names[i+page*8], x+20, y+20+gap*n+25);
//        g.drawString("Course: JAVA(P)",x+20,y+40+gap*n+20);
//        g.drawString("Time: 6:30 P.M",x+20,y+60+gap*n+15);
//        g.drawString("Days: MWF  Sem: 3",x+20,y+80+gap*n+10);
//        g.drawImage(image, x+10, y+85+gap*n+20, null);
      
        //g.drawRect(x+160, y+35+gap*n+20, 80,100);
       // g.drawString("PHOTO",x+175,y+75+gap*n+20);
          n++;
//            }
//            else{
//                x=10;
//            g.drawImage(logo, x, y+2+gap*n, null); 
//            g.drawString("Session Jan-Jul 2018", x+90, y+18+gap*n);
//            g.drawRect(x, y+gap*m, width,length);
//            g.drawString("Batch Id: SCA-X",x+160,y+20+gap*n+25);
//            g.drawString("Name: "+Names[i+page*8], x+20, y+20+gap*m+25);
//            g.drawString("Course: JAVA(P)",x+20,y+40+gap*m+20);
//            g.drawString("Time: 6:30 P.M",x+20,y+60+gap*n+15);
//            g.drawString("Days: MWF  Sem: 3",x+20,y+80+gap*n+10);
//            g.drawImage(image, x+10, y+85+gap*m+20, null);
//            g.drawRect(x+160, y+35+gap*m+20, 80,100);
//            g.drawString("PHOTO",x+175,y+75+gap*m+20);
//            m++;
//                   
//            
//            }
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
 
    public void actionPerformed(ActionEvent e) {
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         
         boolean ok = job.printDialog();
         
         
         if (ok) {
             try {
                  job.print();
                  
                  
                  
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
         }
    }
 
    public  void printGstReciept(StudentTransaction st,String name,String emp) {
            this.st=st;
            this.name=name;
            this.emp=emp;
            this.date=st.getDateOfPayement();
//        UIManager.put("swing.boldMetal", Boolean.FALSE);
//        JFrame f = new JFrame("Hello World Printer");
//        f.addWindowListener(new WindowAdapter() {
//           public void windowClosing(WindowEvent e) {System.exit(0);}
//        });
//        JButton printButton = new JButton("Print Hello World");
//        printButton.addActionListener(new GSTReceiptPrinter());
//        f.add("Center", printButton);
//        f.pack();
//        f.setVisible(true);
           PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         
         boolean ok = job.printDialog();
         
         
         if (ok) {
             try {
                  job.print();
                  
                  
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
         }
        
    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
}  
}

