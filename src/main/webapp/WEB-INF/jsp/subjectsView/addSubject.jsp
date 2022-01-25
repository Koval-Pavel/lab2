<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Add new Subject:</title>
</head>
<body>
<h2>Add new Subject</h2>
<form:form action="saveSubject" method="post">
    <table>
        <tr>
            <td>Subject Name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Teacher Name:</td>
            <td><form:input path="teacherName"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form:form>
<ul>
    <li><a href="./viewAllStudents">View all students</a></li>
    <li><a href="./viewAllGroups">View all groups</a></li>
    <li><a href="./findStudent">Find Student</a></li>

</ul>
</body>
</html>
