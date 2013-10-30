<%-- 
    Document   : Adminjsp
    Created on : Oct 30, 2013, 3:50:58 PM
    Author     : timchen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
      <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            
      </head>
      <body>
             
            <font color="blue" size="5">Admin   ${username}</font></br>
            <a href="addbook.jsp"<h3>addbook</h3></a>
            <a href="http://localhost:8084/Webcar1008/Logout"<h2>logout</h2></a></br>
            <table border='1'>
            <tr>
                <th>id</th>
                <th>bookname</th>
                <th>publisher</th>
                <th>price</th>
            </tr>
            <c:forEach var="aa" items="${books}">
                <tr>
                    <td>${aa.id}</td>
                    <td>${aa.bookname}</td>
                    <td>${aa.publisher}</td>
                    <td>${aa.price}</td>
                    <td><a href="show?bookid=${aa.id}">資訊</a></td>
                </tr>
            </c:forEach>
        </table>
      </body>
</html>
