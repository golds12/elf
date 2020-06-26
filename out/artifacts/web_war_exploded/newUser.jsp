<%--
  Created by IntelliJ IDEA.
  User: Jan
  Date: 26.06.2020
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insert Data</title>
</head>
<body>
<!-- Give Servlet reference to the form as an instances
GET and POST services can be according to the problem statement-->
<form action="./insertUser" method="post">
    <p>login:</p>
    <!-- Create an element with mandatory name attribute,
    so that data can be transfer to the servlet using getParameter() -->
    <input type="text" name="login"/>
    <br/>
    <p>haslo:</p>
    <input type="text" name="password"/>
    <br/><br/><br/>
    <input type="submit"/>
</form>
</body>
</html>