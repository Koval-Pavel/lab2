<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 15.01.2022
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
    <title>All students:</title>


</head>
<body>
    <h2>All Students:</h2>
    <table border="2" cellpadding="2" width="60%">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Group</th>
            <th>Age</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
<%--        list передаем из studentControllera--%>
        <c:forEach var="student" items="${list}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.group}</td>
                <td>${student.age}</td>
                <td><a href="editStudent/${student.id}">Edit</a> </td>
                <td><a href="deleteStudent/${student.id}">Delete</a> </td>
            </tr>
        </c:forEach>
    </table>
    <security:authorize access="hasRole('ADMIN')">
        <div><td><a href="addStudent">Add new Student Button</a> </td></div>
    </security:authorize>
    <security:csrfInput/>
</body>
</html>
