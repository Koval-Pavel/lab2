<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Add new Group:</title>
</head>
<body>
<h2>Add new Group</h2>
<form:form action="saveGroup" method="post">
    <table>
        <tr>
            <td>Group Name:</td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td>Faculty:</td>
            <td>
                <select name="facultyId" id="facultyId">
                    <option value="0" selected="selected"></option>
                    <c:forEach var="facultyId" items="${name1}">
                        <option value="${facultyId.key}">${facultyId.value}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Group Head ID:</td>
            <td>Функционал пока-что не доступен</td>

        <%--            <td><form:input path="headId"/></td>--%>
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
