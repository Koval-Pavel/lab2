
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Find Student:</title>
</head>
<body>
<h2>Find Student</h2>
<form:form action="getStudent" method="post">
    <table>
        <tr>
            <td>Enter Name:</td>
            <td><form:input path="name"/></td>
        </tr>
            <td></td>
            <td><input type="submit" value="Find"></td>
        </tr>
    </table>
</form:form>

<ul>
    <li><a href="./viewAllStudents">View all students</a></li>
    <li><a href="./viewAllGroups">View all groups</a></li>

</ul>
</body>
</html>
