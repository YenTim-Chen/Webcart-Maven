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
public class BookDAOimpl implements BookDao {

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

      public List<bookbean> getBooks() {
            Connection connection = null;
            PreparedStatement preparedstatement = null;
            ResultSet resultset = null;
            List<bookbean> allbook = new ArrayList<>();

            try {
                  connection = datasource.getConnection();
                  preparedstatement = connection.prepareStatement("SELECT * FROM Bookstore");
                  resultset = preparedstatement.executeQuery();
                  while (resultset.next()) {
                        bookbean bookbean = new bookbean();//?
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

      public bookbean getOnebook(int id) {
            Connection connection = null;
            PreparedStatement preparedstatement = null;
            ResultSet resultset = null;
            List<bookbean> book = new ArrayList<>();
            bookbean bookbean = new bookbean();
            try {
                  connection = datasource.getConnection();
                  preparedstatement = connection.prepareStatement("SELECT *FROM Bookstore where id=?");
                  preparedstatement.setInt(1, id);

                  resultset = preparedstatement.executeQuery();

                  while (resultset.next()) {

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

      @Override
      public List<bookbean> getSearch(bookbean bookbean) {
            Connection connection = null;
            PreparedStatement preparedstatement = null;
            ResultSet resultset = null;
            List<bookbean> booklist = new ArrayList<>();

            try {
                  Integer id = bookbean.getId();
                  //id='%'+id+'%';

                  String bookname = bookbean.getBookname();
                  //bookname='%'+bookname+'%';

                  String publisher = bookbean.getPublisher();
                  //publisher='%'+publisher+'%';

                  Integer price = bookbean.getPrice();
                  //price='%'+price+'%';

                  connection = datasource.getConnection();
                  String sql;
                  sql = "SELECT *FROM Bookstore where 1=1";
                  if (bookbean.getId() == 0 && bookbean.getBookname().isEmpty() && bookbean.getPublisher().isEmpty() && bookbean.getPrice() == 0) {
                        preparedstatement = connection.prepareStatement(sql);
                        resultset = preparedstatement.executeQuery();
                        while (resultset.next()) {
                              booklist.add(new bookbean(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getInt(4)));

                        }
                  } else {
                        int count = 1;
                        int iid = 0;
                        int ibookname = 0;
                        int ipublisher = 0;
                        int iprice = 0;
                        //sql="SELECT *FROM Bookstore  WHERE id=? and bookname like ?and publisher like ?and price= ?";

                        if (id != 0) {
                              sql = sql.concat(" and id=?");
                              iid = count++;
                              //preparedstatement.setInt(1,id); 這裡是sql語法組完在一次塞值
                        }
                        if (!bookname.isEmpty()) {
                              sql = sql.concat(" and bookname like ?");
                              ibookname = count++;
                              //preparedstatement.setString(1,'%'+ bookname +'%');
                        }
                        if (!publisher.isEmpty()) {
                              sql = sql.concat(" and publisher like ?");
                              ipublisher = count++;
                              //preparedstatement.setString(1,'%'+ publisher +'%');
                        }
                        if (price != 0) {
                              sql = sql.concat(" and price=?");
                              iprice = count++;
                              //preparedstatement.setInt(1,price);
                        }
                        preparedstatement = connection.prepareStatement(sql);
                        if (id != 0) {
                              preparedstatement.setInt(iid, id);
                        }
                        if (!bookname.isEmpty()) {
                              preparedstatement.setString(ibookname, '%' + bookname + '%');
                        }
                        if (!publisher.isEmpty()) {
                              preparedstatement.setString(ipublisher, '%' + publisher + '%');
                        }
                        if (price != 0) {
                              preparedstatement.setInt(iprice, price);
                        }
                        
                        resultset = preparedstatement.executeQuery();
                        while (resultset.next()) {
                              booklist.add(new bookbean(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getInt(4)));
                        }
                  }

            } catch (SQLException ex) {
                  Logger.getLogger(BookDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                  if (resultset != null) {
                        resultset.close();
                  }
            } catch (SQLException ex) {
                  Logger.getLogger(BookDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                  if (preparedstatement != null) {
                        preparedstatement.close();
                  }
            } catch (SQLException ex) {
                  Logger.getLogger(BookDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                  if (connection != null) {
                        connection.close();
                  }
            } catch (SQLException ex) {
                  Logger.getLogger(BookDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return booklist;
      }
}
