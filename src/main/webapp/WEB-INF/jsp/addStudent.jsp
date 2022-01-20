<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Add new Student:</title>
</head>
<body>
    <h2>Add new Student</h2>
    <form:form action="saveStudent" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="name"/></td>
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
                <td><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
