
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Edit Student:</title>
</head>
<body>
<h2>Edit Student:</h2>
<form:form action="editSave" method="post">
    <table>
<%--        <tr>--%>
<%--            <td>ID:</td>--%>
<%--            <td><form:input path="id"  /></td>--%>
<%--        </tr>--%>
        <tr>
            <td>Name:</td>
            <td><form:input path="name"  /></td>
        </tr>
        <tr>
            <td>Group ID:</td>
            <td><form:input path="groupId"/></td>
        </tr>
        <tr>
            <td>Team mate ID:</td>
<%--            <td><form:input path="groupTeamLeadId"/></td>--%>
            <td>Функционал пока-что не доступен</td>

        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Edit Save"></td>
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
