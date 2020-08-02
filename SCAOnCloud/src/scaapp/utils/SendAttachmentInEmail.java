/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;



import com.sun.mail.imap.IMAPStore;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Quota;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendAttachmentInEmail {
    public static boolean sendMessage(String username, String password, String to, String from, String subject, String body, String fileName) {
        System.out.println("Send Message");
        try {
            
            // Assuming you are sending email through relay.jangosmtp.net
            String host = "smtp.gmail.com";
            
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            
            // Get the Session object.
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            
            
            
            
//            Store store = session.getStore("imaps");
//            store.connect(host, username, password);
//
//            if (!IMAPStore.class.isInstance(store))
//                throw new IllegalStateException("Is not IMAPStore");
//
//            IMAPStore imapStore = (IMAPStore) store;
//            Quota[] quotas = imapStore.getQuota("INBOX");
//
//            for (Quota quota : quotas) {
//                System.out.println(String.format("quotaRoot:'%s'", quota.quotaRoot));
//
//                for (Quota.Resource resource : quota.resources) {
//                    System.out.println(String.format("name:'%s', limit:'%s', usage:'%s'",
//                            resource.name, resource.limit, resource.usage));
//                }
//            }
            
            
            
            
            
            
            try {
                // Create a default MimeMessage object.
                Message message = new MimeMessage(session);
                
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));
                
                // Set To: header field of the header.
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(to));
                
                // Set Subject: header field
                message.setSubject(subject);
                
                // Create the message part
                BodyPart messageBodyPart = new MimeBodyPart();
                
                // Now set the actual message
                messageBodyPart.setText(body);
                
                // Create a multipar message
                Multipart multipart = new MimeMultipart();
                
                // Set text message part
                multipart.addBodyPart(messageBodyPart);
                
                // Part two is attachment
                messageBodyPart = new MimeBodyPart();
                //String filename = "D:\\dailyreportThu Nov 01 13-49-40 IST 2018.xls";
                DataSource source = new FileDataSource(fileName);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(fileName);
                multipart.addBodyPart(messageBodyPart);
                
                // Send the complete message parts
                message.setContent(multipart);
                
                // Send message
                Transport.send(message);
                
                System.out.println("Message sent successfully to "+to);
            }
            catch (MessagingException e) {
                e.printStackTrace();
                return false;
                //throw new RuntimeException(e);
            }
        }
        catch (Exception ex) {
            Logger.getLogger(SendAttachmentInEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
