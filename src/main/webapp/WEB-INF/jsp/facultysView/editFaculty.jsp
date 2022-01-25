
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Edit Faculty:</title>
</head>
<body>
<h2>Edit Faculty:</h2>
<form:form action="editSaveFaculty" method="post">
    <table>
        <tr>
            <td>Faculty Name:</td>
            <td><form:input path="name"  /></td>
        </tr>
        <tr>
            <td>Teacher Name:</td>
            <td><form:input path="headName"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Edit Save"></td>
        </tr>
    </table>
</form:form>

<ul>
    <li><a href="./viewAllFacultys">View all facultys</a></li>
    <li><a href="./viewAllGroups">View all groups</a></li>
    <li><a href="./viewAllStudents">View all students</a></li>
    <li><a href="./viewAllSubjects">View all subjects</a></li>
    <li><a href="./findStudent">Find Student</a></li>

</ul>
</body>
</html>
