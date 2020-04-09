<%@ page import="dev.kirillzhelt.db.models.User" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body bgcolor="palegreen">

    <form method="get">
        <select name="facultyName">
            <c:forEach items="${facultyNames}" var="facultyName">
                <option value="${facultyName}">
                        ${facultyName}
                </option>
            </c:forEach>
        </select>

        <%
            User user = (User)session.getAttribute("user");
            if (user != null) {
        %>
            <input type="submit" value="Logout" formaction="/servlets_lab13_war_exploded/UniversityServlet/logout">
            <input type="submit" value="All enrollees" formaction="/servlets_lab13_war_exploded/UniversityServlet/all-enrollees">
            <input type="submit" value="Applied enrollees" formaction="/servlets_lab13_war_exploded/UniversityServlet/applied-enrollees">
            <input type="submit" value="Above average" formaction="/servlets_lab13_war_exploded/UniversityServlet/above-average">
            <input type="submit" value="Average grade" formaction="/servlets_lab13_war_exploded/UniversityServlet/average-grade">
        <%

                if (user.isAdmin()) {
        %>
                    <input type="submit" value="Register enrollee" formaction="/servlets_lab13_war_exploded/UniversityServlet/register-enrollee">
        <%
                }
            } else {
        %>
                    <a href="/servlets_lab13_war_exploded/UniversityServlet/login-user">Login or register</a>
        <%
            }
        %>

    </form>

</body>
</html>
