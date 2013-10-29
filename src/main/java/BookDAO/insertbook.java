package BookDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataSouce.datasouse;
import model.bookbean;

public class insertbook {

      public void addBook(bookbean bookbean) {
            Connection connection = null;
            PreparedStatement preparedstatement = null;


            try {
                  connection = new datasouse().getConnection();

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
}
