<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>

<ul>
    <c:forEach items="${studentNames}" var="studentName">
        <li>
           ${studentName.getName()}
        </li>
    </c:forEach>
</ul>

</body>
</html>
