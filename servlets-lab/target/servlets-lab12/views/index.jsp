<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>

    <form method="get">
        <select name="facultyName">
            <c:forEach items="${facultyNames}" var="facultyName">
                <option value="${facultyName}">
                        ${facultyName}
                </option>
            </c:forEach>
        </select>

        <input type="submit" value="All enrollees" formaction="/servlets-lab12/UniversityServlet/all-enrollees">
        <input type="submit" value="Applied enrollees" formaction="/servlets-lab12/UniversityServlet/applied-enrollees">
        <input type="submit" value="Above average" formaction="/servlets-lab12/UniversityServlet/above-average">
        <input type="submit" value="Average grade" formaction="/servlets-lab12/UniversityServlet/average-grade">
        <input type="submit" value="Register enrollee" formaction="/servlets-lab12/UniversityServlet/register-enrollee">

    </form>

</body>
</html>
