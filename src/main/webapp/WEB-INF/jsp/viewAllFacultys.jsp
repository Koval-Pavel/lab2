
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="g"%>

<html>
<head>
    <title>All Facultys:</title>
</head>
<body>
<h2>All Facultys:</h2>
<table border="2" cellpadding="2" width="60%">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Head Name</th>
    </tr>

    <%--        list передаем из studentControllera--%>
    <g:forEach var="faculty" items="${list}">
        <tr>
            <td>${faculty.id}</td>
            <td>${faculty.name}</td>
            <td>${faculty.headName}</td>
        </tr>
    </g:forEach>
</table>
<security:authorize access="hasRole('ADMIN')">
    <div><td><a href="addFaculty">Add new Faculty Button</a> </td></div>
</security:authorize>
<security:csrfInput/>
<ul>
    <li><a href="./viewAllStudents">View all students</a></li>
    <li><a href="./viewAllGroups">View all groups</a></li>
    <li><a href="./findStudent">Find Student</a></li>
</ul>
</body>
</html>

<%--------------------------------------------------------------------%>
