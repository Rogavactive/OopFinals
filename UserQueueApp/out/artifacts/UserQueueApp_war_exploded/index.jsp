<%--
  Created by IntelliJ IDEA.
  User: dimit
  Date: 07/15/18
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
        window.onload = function (ev) {
            askForLogin();
        };
      var timeout = null;
      function askForLogin() {
          $.ajax({
              url: "QueueServlet",
              type: 'POST',
              data: {
                  type:"LoginRequest"
              },
              success: function (data) {
                  if (data === "true") {
                      window.location.href = 'https://www.google.com';
                  }else if(data==="error"){
                      $('#loading-message').text("Something went wrong. Error:500");
                  }
                  else{
                      $('#loading-message').text("You are in queue: "+data);
                      clearTimeout(timeout);
                      timeout = setTimeout(askForLogin, 100);
                  }
              }
          });
      }
    </script>
  </head>
  <body>
        <p id="loading-message"></p>
  </body>
</html>
