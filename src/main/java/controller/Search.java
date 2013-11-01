/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.BookService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.bookbean;


@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

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
//            response.setContentType("text/html;charset=UTF-8");
//            System.out.println(request.getCharacterEncoding());
            System.out.println(response.getCharacterEncoding());
//            request.setCharacterEncoding("utf-8");
            
            String sid=(String)request.getParameter("id");
            int id;
            if(sid.isEmpty())
            {
                  id=0;
            }else
            {
            id=Integer.parseInt(sid);
            }
            String bookname=(String)request.getParameter("bookname");
            
            String publisher=(String)request.getParameter("publisher");
            
            String sprice=(String)request.getParameter("price");
            int price;
            if(sprice.isEmpty())
            {
                  price=0;
            }else
            {
            price=Integer.parseInt(sprice);
            }
            bookbean bookbean=new bookbean(id,bookname,publisher,price);
            
            ServletContext context=this.getServletContext();
            BookService bookservice=(BookService)context.getAttribute("BookService");
            List<bookbean> booklist=new ArrayList<>();
            booklist=bookservice.getSerach(bookbean);
            HttpSession session=request.getSession();
            session.setAttribute("booklist", booklist);
            
            String url="Search.jsp";
            request.getRequestDispatcher(url).forward(request, response);
            
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
