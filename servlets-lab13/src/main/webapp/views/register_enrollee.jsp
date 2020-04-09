<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>

    <h3>Register an enrollee for ${facultyName}</h3>

    <form method="post">
        <label for="name">Name</label>
        <input type="text" id="name" name="name">

        <label for="login">Login</label>
        <input type="text" id="login" name="login">

        <label for="password">Password</label>
        <input type="password" id="password" name="password">

        <c:forEach items="${subjects}" var="subject">
            <label for="${subject.name}">${subject.name}</label>
            <input type="number" name="${subject.name}" id="${subject.name}">
        </c:forEach>

        <input type="submit" value="Register employee">
    </form>

</body>
</html>
