/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.bookbean;

/**
 *
 * @author timchen
 */
public interface BookDao {
      public void addBook(bookbean bookbean);
      public List<bookbean> getBooks();
      public bookbean getOnebook(int id);
      public List<bookbean> getSearch(bookbean bookbean);
}
