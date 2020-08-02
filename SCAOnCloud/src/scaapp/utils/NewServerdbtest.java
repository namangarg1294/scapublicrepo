/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Comp8
 */
public class NewServerdbtest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
           Class.forName("com.mysql.jdbc.Driver");
           System.out.println("Loaded");
           
           Connection conn=DriverManager.getConnection("jdbc:mysql://103.120.178.143/scaoncloud","scaoncloud","9752345221@Naman");
        System.out.println("Connected");
    }
}
