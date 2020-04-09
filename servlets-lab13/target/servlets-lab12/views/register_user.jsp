<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>

<a href="/servlets_lab13_war_exploded/UniversityServlet/login-user">Login</a>
<h3>Register</h3>

<form method="post">
    <label for="name">Name</label>
    <input type="text" id="name" name="name">

    <label for="login">Login</label>
    <input type="text" id="login" name="login">

    <label for="password">Password</label>
    <input type="password" id="password" name="password">

    <label for="confirm-password">Confirm password</label>
    <input type="password" id="confirm-password" name="confirm-password">

    <label for="is-admin">Is admin</label>
    <input type="checkbox" id="is-admin" name="is-admin">

    <input type="submit" value="Register employee">
</form>

</body>
</html>
