<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
    <title>All students:</title>
</head>
<body>
    <h3>INFO MESSAGE: ${massage} </h3>
    <h2>All Students:</h2>
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
        <c:forEach var="student" items="${list}">
            <tr>
                <td><a href="studentMarkInfo/${student.id}">${student.name}</a> </td>
                <td>${student.groupName}</td>
                <td>${student.teamMate_Name}</td>
                <security:authorize access="hasRole('ADMIN')">
                    <td><a href="editStudent/${student.id}">Edit</a> </td>
                    <td><a href="deleteStudent/${student.id}">Delete</a> </td>
                </security:authorize>
                <security:csrfInput/>
            </tr>
        </c:forEach>
    </table>
    <security:authorize access="hasRole('ADMIN')">
        <div><td><a href="addStudent">Add new Student Button</a> </td></div>
    </security:authorize>
    <security:csrfInput/>
    <ul>
        <li><a href="./viewAllFacultys">View all faculty's</a></li>
        <li><a href="./viewAllGroups">View all groups</a></li>
        <li><a href="./viewAllStudents">View all students</a></li>
        <li><a href="./viewAllSubjects">View all subject</a></li>
        <li><a href="./findStudent">Find Student</a></li>
        <li><a href="./logout">Logout</a></li>

    </ul>
</body>
</html>
