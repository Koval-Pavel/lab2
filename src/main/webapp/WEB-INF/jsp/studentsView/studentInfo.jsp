<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
    <title>Student INFO:</title>
</head>
<body>

<h2>INFO MESSAGE: </h2>
<%--dobavit' NAME i td.--%>
<jsp:text>
    ${massage}
</jsp:text>

<h2>Subjects:</h2>
<table border="2" cellpadding="2" width="60%">
    <tr>

        <th>Subject Name</th>
        <th>Mark</th>
        <security:authorize access="hasRole('ADMIN')">
            <th>Edit</th>
            <th>Delete</th>
        </security:authorize>
        <security:csrfInput/>

    </tr>

<%--    <c:forEach var="student" items="${list}">--%>

<%--        <tr>--%>
<%--            <td>${student.id}</td>--%>
<%--            <td><a href="editStudent/${student.id}">Edit</a> </td>--%>
<%--            <td>${student.name}</td>--%>
<%--            <td>${student.groupName}</td>--%>
<%--            <td>${student.teamMate_Name}</td>--%>
<%--            <security:authorize access="hasRole('ADMIN')">--%>
<%--                <td><a href="editStudent/${student.id}">Edit</a> </td>--%>
<%--                <td><a href="deleteStudent/${student.id}">Delete</a> </td>--%>
<%--            </security:authorize>--%>
<%--            <security:csrfInput/>--%>

<%--        </tr>--%>
<%--    </c:forEach>--%>
</table>
<%--<security:authorize access="hasRole('ADMIN')">--%>
<%--    <div><td><a href="addStudent">Add new Student Button</a> </td></div>--%>
<%--</security:authorize>--%>
<%--<security:csrfInput/>--%>
<ul>
    <li><a href="../viewAllFacultys">View all facultys</a></li>
    <li><a href="../viewAllGroups">View all groups</a></li>
    <li><a href="../findStudent">Find Student</a></li>
    <li><a href="../logout">LogOut</a></li>

</ul>
</body>
</html>
