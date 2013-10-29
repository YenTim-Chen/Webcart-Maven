package DataSouce;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class datasouse {
       private static final String host="localhost";
           private static final String port="3306";
           private static final String db="test";
           private static final String jdbcurl="jdbc:mysql://"+host+":"+port+"/"+db+"?zeroDateTimeBehavior=convertToNull";
           private static final String user="root";
           private static final String password="base";
           
      public Connection getConnection() 
      {
            Connection connection;
             try {
                   Class.forName("com.mysql.jdbc.Driver");
             } catch (ClassNotFoundException ex) {
                   Logger.getLogger(datasouse.class.getName()).log(Level.SEVERE, null, ex);
             }
             try {
                   connection = DriverManager.getConnection(jdbcurl,user,password);
                   return connection;  
             } catch (SQLException ex) {
                   ex.printStackTrace();
             }
             return null;
             
      }
      
}
