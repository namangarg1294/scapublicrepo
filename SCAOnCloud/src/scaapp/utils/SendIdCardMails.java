/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Comp10
 */
public class SendIdCardMails {
    public static void main(String[] args) {
        try {
            File limit = new File("cache\\limits\\limit.txt");
            System.out.println(limit.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(new FileWriter(limit,true));
            BufferedReader br = new BufferedReader(new FileReader(limit));
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
            String todaysDate = formatter.format(new Date());
            String date = br.readLine();
            System.out.println(date);
            if(date!=null && date.equals(todaysDate)){
                String messagesSent = br.readLine();
                if(messagesSent!=null && Integer.parseInt(messagesSent)<500){
                    File path = new File("cache\\idcards");
                    Thread mailSenderThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if(path.listFiles().length>0){
                                File []imageFiles = path.listFiles();
                                for(int i=0; i<imageFiles.length; i++){
                                    String fileName = imageFiles[i].getName();
                                    String name = fileName.substring(0, fileName.indexOf("-"));
                                    String email = fileName.substring(fileName.indexOf("-")+1, fileName.lastIndexOf("-"));
                                    String course = fileName.substring(fileName.lastIndexOf("-")+1, fileName.lastIndexOf("."));
                                    boolean isSent = SendAttachmentInEmail.sendMessage("sharmacomputeracademy", "sac.par@1979", email, "SCA", "Digital ID card -SCA "+new Date(), "Hi "+name+","+" PFA your Digital ID card for the "+course+" course", imageFiles[i].getAbsolutePath());
                                    if(isSent){
                                        try {
                                            System.out.println(br.readLine());
                                            //StringBuilder sb = new StringBuilder(br.readLine());
                                            String messagesSent = br.readLine();
                                            int messagesSentCount=0;
                                            if(messagesSent==null){
                                                bw.write("1");
                                                //sb.append("1");
                                            }
                                            else{
                                                messagesSentCount = Integer.parseInt(messagesSent);
                                            }
                                            if(messagesSentCount<500){
                                                bw.write(messagesSentCount+1);
                                                //sb.append(messagesSentCount+1);
                                            }
                                            //System.out.println(sb);
                                            bw.flush();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    });
                    mailSenderThread.start();
                }
                else{
                    System.out.println("Limit exceeded");
                }
            }
            else{
                bw.write(todaysDate);
                bw.flush();
            }
//        while(true){
//            try {
//                mailSenderThread.start();
//                mailSenderThread.sleep(60000);
//            } 
//            catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SendIdCardMails.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(SendIdCardMails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
