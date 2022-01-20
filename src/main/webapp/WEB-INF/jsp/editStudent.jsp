
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
        <tr>
            <td>ID:</td>
            <td><form:input path="id"  /></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><form:input path="name"  /></td>
        </tr>
        <tr>
            <td>Group:</td>
            <td><form:input path="group"/></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td><form:input path="age"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Edit Save"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
