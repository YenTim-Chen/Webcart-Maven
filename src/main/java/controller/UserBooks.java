/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.BookService;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author timchen
 */
@WebServlet(name = "UserBooks", urlPatterns = {"/UserBooks"})
public class UserBooks extends HttpServlet {

      /**
       * Processes requests for both HTTP
       * <code>GET</code> and
       * <code>POST</code> methods.
       *
       * @param request servlet request
       * @param response servlet response
       * @throws ServletException if a servlet-specific error occurs
       * @throws IOException if an I/O error occurs
       */
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
             if(!checkLogin(request))
            {
            response.sendRedirect("Login.jsp");
            }else{
            ServletContext context=this.getServletContext();
            BookService bookservice = (BookService)context.getAttribute("BookService");
            UserService uservice=(UserService)context.getAttribute("UserService");
            System.out.println(uservice.getUser());
            //ServletContext context = this.getServletContext();
            HttpSession session=request.getSession();
            session.setAttribute("books", bookservice.getBook());
            //context.setAttribute("books", bookservice.getbook());
            response.sendRedirect("Userjsp.jsp");
            //request.getRequestDispatcher("book.jsp").forward(request, response);
            }
            
      }
       public Boolean checkLogin(HttpServletRequest request)
      {
            boolean isLogin=false;
            HttpSession session=request.getSession();
            if(session.getAttribute("username")!=null)
            {
            isLogin=true;
            }
            
      return isLogin;
      }

      // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
      /**
       * Handles the HTTP
       * <code>GET</code> method.
       *
       * @param request servlet request
       * @param response servlet response
       * @throws ServletException if a servlet-specific error occurs
       * @throws IOException if an I/O error occurs
       */
      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
            processRequest(request, response);
      }

      /**
       * Handles the HTTP
       * <code>POST</code> method.
       *
       * @param request servlet request
       * @param response servlet response
       * @throws ServletException if a servlet-specific error occurs
       * @throws IOException if an I/O error occurs
       */
      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
            processRequest(request, response);
      }

      /**
       * Returns a short description of the servlet.
       *
       * @return a String containing servlet description
       */
      @Override
      public String getServletInfo() {
            return "Short description";
      }// </editor-fold>
}
