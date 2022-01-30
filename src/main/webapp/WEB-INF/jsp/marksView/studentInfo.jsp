<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
    <title>Student INFO: </title>
</head>
<body>

<h3>INFO MESSAGE: ${massage} </h3>
<h2>Student Info:</h2>
<form:form action="checkStudentMark/${studentId}" method="post">
    <table border="1" cellpadding="1" width="60%">

    <tr>
        <th>Choose Subject Name</th>
        <td>
            <select name="subjectId" id="subjectId">
                <c:forEach var="subjectId" items="${awailableSub}">
                    <option value="${subjectId.key}">${subjectId.value}</option>
                </c:forEach>
            </select>
        </td>

        <td><input type="submit" value="Check"></td>
        <td><a  href="addMark/${studentId}/">Add mark</a></td>
    </tr>


        <tr>
            <td>Name:</td>
            <td>Date:</td>
            <td>Value:</td>
        </tr>

        <c:forEach var="mark" items="${marklist}">
            <tr>
                <td>${mark.subjectName}</td>
                <td>${mark.date}</td>
                <td>${mark.value}</td>
                <security:authorize access="hasRole('ADMIN')">
                    <td><a href="deleteMark/${mark.id}">Delete</a> </td>
                </security:authorize>
                <security:csrfInput/>
            </tr>
        </c:forEach>

        <tr>
        </tr>

    </table>
</form:form>
<ul>
    <li><a href="../viewAllFacultys">View all faculty's</a></li>
    <li><a href="../viewAllGroups">View all groups</a></li>
    <li><a href="../viewAllStudents">View all students</a></li>
    <li><a href="../viewAllSubjects">View all subject</a></li>
    <li><a href="../findStudent">Find Student</a></li>
    <li><a href="../logout">Logout</a></li>
</ul>
</body>
</html>
