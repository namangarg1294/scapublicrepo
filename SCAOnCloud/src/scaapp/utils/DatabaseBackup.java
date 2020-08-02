package scaapp.utils;


import com.intenum.jdbc.backup.Backup;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import scaapp.dao.LastBackupDao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Comp10
 */
public class DatabaseBackup {
    public static void main(String[] args) {
        execute();
    }
    
    public static void execute(){
        try {
            String currentDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
            System.out.println(LastBackupDao.retrieveLastTransactionDate());
            if(!(currentDate.equals(LastBackupDao.retrieveLastTransactionDate()))){
                //LastBackupDao.insertFirstTransactionDate();
                backup(DBConnection.getConnection());
                //mailBackupFile("sharmacomputeracademy@gmail.com", "sac.par@1979", "prayatnaj@gmail.com", "SCA", "Transactions backup "+new Date(), "PFA Transactions backup file", "backup.sql");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBackup.class.getName()).log(Level.SEVERE, null, ex);
        } //catch (ParseException ex) {
//            Logger.getLogger(DatabaseBackup.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public static void backup(Connection conn){
        try {
            Backup backup = new Backup(conn);
            FileOutputStream fos = new FileOutputStream("D:\\backuptemp.sql");
            System.out.println(new File("").getAbsolutePath());
            System.out.println("Backup start time : " + new Date().toString());
            backup.execute(new PrintStream(fos));
            System.out.print("Backup end time : " + new Date().toString());
            fos.close();
            tempSwapAndDelete();
        } 
        catch (Exception ex) {
            System.out.println("Error while backing up database");
            ex.printStackTrace();
        }
    }
    
    public static void tempSwapAndDelete() throws Exception{
        FileUtils.copyFile(new File("backuptemp.sql"), new File("D:\\backup.sql"));
        boolean isFileDeleted = new File("backuptemp.sql").delete();
        System.out.println(isFileDeleted);
    }
    
    public static void mailBackupFile(String username, String password, String to, String from, String subject, String body, String fileName){
        SendAttachmentInEmail.sendMessage(username, password, to, from, subject, body, fileName);
    }
}
