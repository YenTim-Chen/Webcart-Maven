/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import model.Userbean;

/**
 *
 * @author timchen
 */
public interface UserDAO {
      public List<String> getUser();
      
      public void addUser(Userbean userbean);
      public List<String> selectById(Userbean userbean);
}
