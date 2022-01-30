<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
    <title>Group students:</title>
</head>
<body>

<h1>INFO MESSAGE: ${massage}</h1>

<h2>Group Students:</h2>
<table border="2" cellpadding="2" width="60%">
    <tr>
        <th>Name</th>
        <th>Group Name</th>
        <th>GROUP TEAMMATE ID</th>
        <security:authorize access="hasRole('ADMIN')">
            <th>Edit</th>
            <th>Delete</th>
        </security:authorize>
        <security:csrfInput/>

    </tr>
    <c:forEach var="groupStudentList" items="${groupStudentList}">
        <tr>
            <td><a href="../studentMarkInfo/${groupStudentList.id}">${groupStudentList.name}</a> </td>
            <td>${groupStudentList.groupName}</td>
            <td>${groupStudentList.teamMate_Name}</td>
            <security:authorize access="hasRole('ADMIN')">
                <td><a href="../editStudent/${groupStudentList.id}">Edit</a> </td>
                <td><a href="../deleteStudent/${groupStudentList.id}">Delete</a> </td>
            </security:authorize>
            <security:csrfInput/>
        </tr>
    </c:forEach>
</table>
<security:authorize access="hasRole('ADMIN')">
    <div><td><a href="../addStudent">Add new Student Button</a> </td></div>
</security:authorize>
<security:csrfInput/>
<ul>
    <li><a href="../viewAllFacultys">View all faculty's</a></li>
    <li><a href="../viewAllGroups">View all groups</a></li>
    <li><a href="../viewAllStudents">View all students</a></li>
    <li><a href="../viewAllSubjects">View all subject</a></li>
    <li><a href="../findStudent">Find Student</a></li>
    <li><a href="../logout">Logout</a></li>

</ul>
</body>
</html>
