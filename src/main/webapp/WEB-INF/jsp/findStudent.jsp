
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
<%--        <tr>--%>
<%--            <td>Id:</td>--%>
<%--            <td><form:input path="id"/></td>--%>
<%--        </tr>--%>
        <tr>
            <td>Enter Name:</td>
            <td><form:input path="name"/></td>
        </tr>
<%--        <tr>--%>
<%--            <td>Group:</td>--%>
<%--            <td><form:input path="group"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Age:</td>--%>
<%--            <td><form:input path="age"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
            <td></td>
            <td><input type="submit" value="Find"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
