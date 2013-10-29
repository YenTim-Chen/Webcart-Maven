/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.imp;

import DAO.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Userbean;

/**
 *
 * @author timchen
 */
public class UserDAOimpl implements UserDAO {

      private DataSource datasource;

      public UserDAOimpl(DataSource datasource) {
            this.datasource = datasource;
      }

      @Override
      public List<String> getUser() {
            Connection connection = null;
            PreparedStatement preparedstatement = null;
            ResultSet resultset = null;
            List<String> userinfo = new ArrayList<>();
            try {
                  String sql = "SELECT *FROM UserLogin";
                  connection = datasource.getConnection();
                  preparedstatement = connection.prepareStatement(sql);
                  resultset = preparedstatement.executeQuery();
                  while (resultset.next()) {
                        System.out.println(resultset.getString(1));
                        System.out.println(resultset.getString(2));
                        userinfo.add(resultset.getString(1));
                        userinfo.add(resultset.getString("password"));
                  }

            } catch (SQLException ex) {
            } finally {
                  try {
                        if (resultset != null) {
                              resultset.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if (preparedstatement != null) {
                              preparedstatement.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if (connection != null) {
                              connection.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
            return userinfo;
      }

      public List<String> selectById(Userbean userbean) {
            Connection connection = null;
            PreparedStatement preparedstatement = null;
            ResultSet resultset = null;
            String UserName = null;
            List<String> getUser = new ArrayList<>();
            try {

                  connection = datasource.getConnection();
                  String sql = "SELECT *FROM UserLogin";
                  preparedstatement = connection.prepareStatement(sql);
                  resultset = preparedstatement.executeQuery(sql);
                  //preparedstatement.setString(1, userbean.getAccount());
                  while (resultset.next()) {
                        UserName = resultset.getString(1);
                        
                              if (userbean.getAccount().equals(UserName)) {
                                    getUser.add(resultset.getString(1));
                                    getUser.add(resultset.getString(2));
                                 }

                        
                  }
            } catch (SQLException ex) {
                  Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                  try {
                        if (resultset != null) {
                              resultset.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if (preparedstatement != null) {
                              preparedstatement.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if (connection != null) {
                              connection.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
            return getUser;
      }

      public void addUser(Userbean userbean) {
            Connection connection = null;
            PreparedStatement preparedstatement = null;

            try {
                  String sql = "INSERT INTO UserLogin(account,password)values(?,?)";
                  connection = datasource.getConnection();
                  preparedstatement = connection.prepareStatement(sql);
                  preparedstatement.setString(1, userbean.getAccount());
                  preparedstatement.setString(2, userbean.getPassword());
                  preparedstatement.executeUpdate();
            } catch (SQLException ex) {
                  Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally
            {
                  try {
                        if(preparedstatement!=null)
                        {
                        preparedstatement.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                        if(connection!=null)
                        {
                        connection.close();
                        }
                  } catch (SQLException ex) {
                        Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
      }
}
