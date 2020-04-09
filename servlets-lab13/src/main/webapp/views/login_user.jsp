<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>

<a href="/servlets_lab13_war_exploded/UniversityServlet/register-user">Register</a>
<h3>Login</h3>

<form method="post">
    <label for="login">Login</label>
    <input type="text" id="login" name="login">

    <label for="password">Password</label>
    <input type="password" id="password" name="password">

    <input type="submit" value="Register employee">
</form>

</body>
</html>
