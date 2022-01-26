
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="g"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<html>
<head>
    <title>All Facultys:</title>
</head>
<body>

<h1>INFO MESSAGE: </h1>
<jsp:text>
    ${massage}
</jsp:text>

<h2>All Facultys:</h2>
<table border="2" cellpadding="2" width="60%">
    <tr>
        <th>Name</th>
        <th>Head Name</th>
        <security:authorize access="hasRole('ADMIN')">
            <th>Edit</th>
            <th>Delete</th>
        </security:authorize>
        <security:csrfInput/>
    </tr>

    <%--        list передаем из studentControllera--%>
    <g:forEach var="faculty" items="${list}">
        <tr>
            <td>${faculty.name}</td>
            <td>${faculty.headName}</td>
            <security:authorize access="hasRole('ADMIN')">
                <td><a href="editFaculty/${faculty.id}">Edit</a> </td>
                <td><a href="deleteFaculty/${faculty.id}">Delete</a> </td>
            </security:authorize>
            <security:csrfInput/>
        </tr>
    </g:forEach>
</table>
<security:authorize access="hasRole('ADMIN')">
    <div><td><a href="addFaculty">Add new Faculty Button</a> </td></div>
</security:authorize>
<security:csrfInput/>
<ul>
    <li><a href="./viewAllGroups">View all groups</a></li>
    <li><a href="./viewAllStudents">View all students</a></li>
    <li><a href="./viewAllSubjects">View all subject</a></li>
    <li><a href="./findStudent">Find Student</a></li>
    <li><a href="./logout">Find Student</a></li>
</ul>
</body>
</html>

<%--------------------------------------------------------------------%>
