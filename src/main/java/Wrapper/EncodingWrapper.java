/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Wrapper;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author timchen
 */
public class EncodingWrapper extends HttpServletRequestWrapper{
      private String Encoding;
      public EncodingWrapper(HttpServletRequest request,String Encoding)
      {
      super(request);
      this.Encoding=Encoding;
      }
      
      public String getParameter(String name)
      {
         String value=getRequest().getParameter(name);   
            try {
                  byte[] b=value.getBytes("ISO-8859-1");
                  value=new String(b,"Encoding");
            } catch (UnsupportedEncodingException ex) {
                  Logger.getLogger(EncodingWrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
      return value;
      }
}
