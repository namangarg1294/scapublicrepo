/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.dao;


import Pojo.BookDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import scaapp.utils.DBConnection;


/**
 *
 * @author Lenovo
 */
public class BookDao {
     public static boolean addBook(BookDetails book)throws SQLException{
        //int count= getRowsCount()+1;
            String insertCourseQry = "Insert into book_details(`book_id`, `book_name`, `book_price`) values(?,?,?)";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(insertCourseQry);
            String str=book.getBookName();
            str=str.replaceAll("PLUS", "+");
                  ps.setString(2, str);
                  ps.setDouble(3, book.getBookPrice());
                  ps.setInt(1, book.getBookId());
                 int i = ps.executeUpdate();
                   if(i>0){
                    return true;
                }else{
                    return false;
                }
          }
}
