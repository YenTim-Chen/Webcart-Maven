/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.BookDao;
import java.util.List;
import model.bookbean;

public class BookService {

      //insertbook addbook = new insertbook();
      BookDao bookdaoimpl;

      public BookService(BookDao bookdao) {
            this.bookdaoimpl = bookdao;
      }

      public List<bookbean> getSerach(bookbean bookbean)
      {
      return bookdaoimpl.getSearch(bookbean);
      }
     
      
      public void addBook(bookbean bookbean) {
            bookdaoimpl.addBook(bookbean);//!
      }
      //selectbook getbook = new selectbook();
      
      public List<bookbean> getBook() {
            return bookdaoimpl.getBooks();//!
      }

      public bookbean getOnebook(int id) {
            
            return bookdaoimpl.getOnebook(id);
      }
}
