/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.imp;

import BookDAO.insertbook;
import BookDAO.selectbook;
import DataSouce.datasouse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bookbean;
import DAO.BookDao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author timchen
 */
public class BookDAOimpl implements BookDao{
      DataSource datasource;

      public BookDAOimpl(DataSource datasource) {
            this.datasource = datasource;
      }
      
      
      
      public void addBook(bookbean bookbean) {
            Connection connection = null;
            PreparedStatement preparedstatement = null;
            

            try {
                  connection = datasource.getConnection();
                         
                  String sql = "INSERT INTO Bookstore(bookname,publisher,price)values(?,?,?)";
                  preparedstatement = connection.prepareStatement(sql);
                  preparedstatement.setString(1, bookbean.getBookname());
                  preparedstatement.setString(2, bookbean.getPublisher());
                  preparedstatement.setInt(3, bookbean.getPrice());
                  preparedstatement.executeUpdate();
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            } finally {
                  try {
                        if (preparedstatement != null) {
                              preparedstatement.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(insertbook.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if (connection != null) {
                              connection.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(insertbook.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
      }
      
      public  List<bookbean> getBooks() {
            Connection connection = null;
            PreparedStatement preparedstatement = null;
            ResultSet resultset = null;
            List<bookbean> allbook=new ArrayList<>();
            
            try {
                 connection = datasource.getConnection();
                  preparedstatement = connection.prepareStatement("SELECT * FROM Bookstore");
                  resultset = preparedstatement.executeQuery();
                  while (resultset.next()) {
                        bookbean bookbean=new bookbean();//?
                        bookbean.setId(resultset.getInt(1));
                        bookbean.setBookname(resultset.getString("bookname"));
                        bookbean.setPublisher(resultset.getString("publisher"));
                        bookbean.setPrice(resultset.getInt(4));
                        allbook.add(bookbean);
//                        System.out.println(resultset.getInt(1));
//                        System.out.println(resultset.getString("bookname"));
//                        System.out.println(resultset.getString("publisher"));
//                        System.out.println(resultset.getInt(4));
                  }
                  
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            } finally {
                  try {
                        if (resultset != null) {
                              resultset.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(selectbook.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if (preparedstatement != null) {
                              preparedstatement.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(selectbook.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if (connection != null) {
                              connection.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(selectbook.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
            return allbook;
      }
      
       public  bookbean getOnebook(int id) {
            Connection connection = null;
            PreparedStatement preparedstatement = null;
            ResultSet resultset = null;
            List<bookbean> book=new ArrayList<>();
            bookbean bookbean=new bookbean();
            try {
                  connection = datasource.getConnection();
                  preparedstatement = connection.prepareStatement("SELECT *FROM Bookstore where id=?");
                  preparedstatement.setInt(1,id);
                  
                  resultset = preparedstatement.executeQuery();
                  
                  while(resultset.next())
                  {
                  
                  bookbean.setId(resultset.getInt(1));
                  bookbean.setBookname(resultset.getString("bookname"));
                  bookbean.setPublisher(resultset.getString("publisher"));
                  bookbean.setPrice(resultset.getInt(4));
                  book.add(bookbean);
//                  
//                  System.out.println(resultset.getInt(1));
//                  System.out.println(resultset.getString("bookname"));
//                  System.out.println(resultset.getString("publisher"));
//                  System.out.println(resultset.getInt(4));
                  }
            } catch (SQLException e) {
                  new RuntimeException();
            } finally {
                  try {
                        if (resultset != null) {
                              resultset.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(selectbook.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if (preparedstatement != null) {
                              preparedstatement.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(selectbook.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if (connection != null) {
                              connection.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(selectbook.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
            return bookbean;
      }
       
       
}
