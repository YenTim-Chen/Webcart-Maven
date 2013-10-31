<%-- 
    Document   : Search
    Created on : Oct 31, 2013, 10:39:47 AM
    Author     : timchen
--%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
      <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
      </head>
      <body>
            
            <h1>找書？ 請輸入相關資訊</h1>
            <form action="Search" method="post">
            ID:<input type="text"name="id"/></br>
            Bookname:<input type="text" name="bookname"/></br>
            Publisher:<input type="text" name="publisher"/></br>
            Price:<input type="text" name="price"/></br>
            <input type="submit" value="Send"/>
            </form>
            <a href="UserBooks">回首頁</a>
            
                    <table border='1'>
            <tr>
                <th>id</th>
                <th>bookname</th>
                <th>publisher</th>
                <th>price</th>
            </tr>
            <c:forEach var="aa" items="${booklist}">
                <tr>
                    <td>${aa.id}</td>
                    <td>${aa.bookname}</td>
                    <td>${aa.publisher}</td>
                    <td>${aa.price}</td>
                </tr>
            </c:forEach>
        </table>
            
      </body>
</html>
