
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="g"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<html>
<head>
    <title>All Groups:</title>
</head>
<body>
    <h2>All Groups:</h2>
    <table border="2" cellpadding="2" width="60%">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Faculty Name</th>
            <th>Head Name</th>
            <security:authorize access="hasRole('ADMIN')">
            <th>Edit</th>
            <th>Delete</th>
            </security:authorize>
            <security:csrfInput/>
        </tr>

<%--        list передаем из studentControllera--%>
        <g:forEach var="group" items="${list}">
            <tr>
                <td>${group.id}</td>
                <td>${group.name}</td>
                <td>${group.facultyName}</td>
                <td>${group.headName}</td>
                <security:authorize access="hasRole('ADMIN')">
                <td><a href="editGroup/${group.id}">Edit</a> </td>
                <td><a href="deleteGroup/${group.id}">Delete</a> </td>
                </security:authorize>
                <security:csrfInput/>

            </tr>
        </g:forEach>
    </table>
    <security:authorize access="hasRole('ADMIN')">
        <div><td><a href="addGroup">Add new Group Button</a> </td></div>
    </security:authorize>
    <security:csrfInput/>
    <ul>
        <li><a href="./viewAllFacultys">View all facultys</a></li>
        <li><a href="./viewAllStudents">View all students</a></li>
        <li><a href="./findStudent">Find Student</a></li>
        <li><a href="./logout">LogOut</a></li>

    </ul>
</body>
</html>

<%--------------------------------------------------------------------%>
