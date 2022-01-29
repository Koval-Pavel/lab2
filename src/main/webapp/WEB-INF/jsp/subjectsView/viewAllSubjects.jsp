<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
    <title>All subjects:</title>
</head>
<body>

<h3>INFO MESSAGE: ${infoMessageSubject}</h3>
<h2>All Subjects:</h2>
<table border="2" cellpadding="2" width="60%">
    <tr>
<%--        <th>ID</th>--%>
        <th>Name</th>
        <th>Teacher Name</th>
        <security:authorize access="hasRole('ADMIN')">
            <th>Edit</th>
            <th>Delete</th>
        </security:authorize>
        <security:csrfInput/>

    </tr>

    <c:forEach var="subject" items="${list}">

        <tr>
            <td>${subject.name}</td>
            <td>${subject.teacherName}</td>
            <security:authorize access="hasRole('ADMIN')">
                <td><a href="editSubject/${subject.id}">Edit</a> </td>
                <td><a href="deleteSubject/${subject.id}">Delete</a> </td>
            </security:authorize>
            <security:csrfInput/>
        </tr>
    </c:forEach>
</table>
<security:authorize access="hasRole('ADMIN')">
    <div><td><a href="addSubject">Add new Subject</a> </td></div>
</security:authorize>
<security:csrfInput/>
<ul>
    <li><a href="./viewAllFacultys">View all facultys</a></li>
    <li><a href="./viewAllGroups">View all groups</a></li>
    <li><a href="./viewAllSubjects">View all subject</a></li>
    <li><a href="./findStudent">Find Student</a></li>
    <li><a href="./logout">logout</a></li>

</ul>
</body>
</html>
