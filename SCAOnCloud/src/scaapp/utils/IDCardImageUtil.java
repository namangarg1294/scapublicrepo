/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import scaapp.pojo.IDCardPrintingDetails;

/**
 *
 * @author Comp10
 */
public class IDCardImageUtil {
    
    static String prevPhoneNo;
    final int width;
    final int length;
    final int gap; 
    final int n; 
    final IDCardPrintingDetails idCPD;
    final BufferedImage image;
    final BufferedImage logo;
    
    IDCardImageUtil(int width, int length, int gap, int n, IDCardPrintingDetails idCPD, BufferedImage image, BufferedImage logo){
        this.width = width;
        this.length = length;
        this.gap = gap;
        this.n = n;
        this.idCPD = idCPD;
        this.image = image;
        this.logo = logo;
    }
    
    public void createAndSendIdCard() throws Exception{
        
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                BufferedImage bufferedImage = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);

                Graphics2D g1 = bufferedImage.createGraphics();
                g1.setColor(Color.WHITE);
                g1.fillRect(0, 0+gap*n, width,length);
                g1.setColor(Color.BLACK);
                g1.drawImage(logo, 0, 0+2+gap*n, null); 
                g1.drawString(idCPD.getBatchId(), 0+80, 0+18+gap*n);
                g1.drawString("Name: "+idCPD.getStudentName(), 0+20, 0+20+gap*n+25);
                g1.drawString("Course: "+idCPD.getCourseName(),0+20,0+40+gap*n+20);
                g1.drawString("Time: "+idCPD.getBatchTime(),0+20,0+60+gap*n+15);
                g1.drawString("Days: MWF  Sem: 3",0+20,0+80+gap*n+10);
                g1.drawImage(image, 0+10, 0+85+gap*n+20, null);
                g1.drawRect(0+160, 0+35+gap*n+20, 80,100);
                g1.drawString("PHOTO",0+175,0+75+gap*n+20);
                g1.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
                AffineTransform at = new AffineTransform();
                at.setToRotation(Math.PI / 6.0);
                g1.setTransform(at);
                Color curr = g1.getColor();
                g1.setColor(new Color(curr.getRed(), curr.getGreen(), curr.getBlue(), 0.2f));
                g1.drawString("DIGITAL COPY", 50, 23);
                System.out.println("hashcode is:"+this.hashCode());
                
                try{
                    // Save as JPEG
                    
                    String imgName = idCPD.getStudentName()+"_"+idCPD.getEmail()+"_"+idCPD.getCourseName()+".png";
                    final File file = new File(imgName);
                    file.createNewFile();
                    ImageIO.write(bufferedImage, "png", file);
                    //File file = new File("cache\\idcards\\"+imgName);
                    System.out.println("diff "+String.valueOf(new Date().getTime()-file.lastModified()));
//                    if(!file.exists()||new Date().getTime()-file.lastModified()>10000){
//                        file.createNewFile();
//                        System.out.println("fis:"+file.getName());
//                        new Thread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                try {                            
//                                    ImageIO.write(bufferedImage, "png", file);
//                                } catch (IOException ex) {
//                                    System.out.println("123123"+ex);
//                                }
//                            }
//                        }).start();
//                        
//                        System.out.println("Image saved");
//                        SendAttachmentInEmail.sendMessage("sharmacomputeracademy", "sac.par@1979", "zackalvestutorials@gmail.com", "SCA", "Digital ID card -SCA "+new Date(), "Hi "+idCPD.getStudentName()+","+" PFA your Digital ID card for the "+idCPD.getCourseName()+" course", imgName);
//                       
//                    }
                    //ex.printStackTrace();
                    }
                
                catch(Exception e){
                    System.out.println("Already Sent!");
                }
                    }
            
        });
        th.start();
    }
}