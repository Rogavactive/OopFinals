<%--
  Created by IntelliJ IDEA.
  User: dimit
  Date: 07/19/18
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <style>
      .seat{
        height: 80px;
        width:9%;
        margin:1px;
        background-color:green;
        display:inline-block;
      }
      .seat:hover{
        /*cursor:*/
      }
    </style>
    <script src="index.js"></script>
    <title>$Title$</title>
  </head>
  <body>
    <%
      int amount = 50, oneRow = 10;
      for (int i = 0; i < amount/oneRow; i++) {
        for (int j = 0; j <oneRow ; j++) {
            int id = oneRow*i+j;
    %><div class="seat" id="<%=id%>" onclick="clickTicket(<%=id%>)"><%=id%></div><%
        }
        %><br><%
      }
      %>
  </body>
</html>
