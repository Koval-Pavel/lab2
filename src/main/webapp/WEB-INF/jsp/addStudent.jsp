<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <td>
                    <select name="groupId" id="groupId">
                        <option value="0" selected="selected"></option>
                        <c:forEach var="groupId" items="${name1}">
                            <option value="${groupId.key}">${groupId.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Team mate ID:</td>
                <td><form:input path="groupTeamLeadId"/></td>
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
