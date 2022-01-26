<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<html>
<head>
    <title>Student INFO:</title>
</head>
<body>

<h2>INFO MESSAGE: </h2>
<%--dobavit' NAME i td.--%>
<jsp:text>
    ${massage}
</jsp:text>

<h2>Subjects:</h2>
<%-- forma this is on which page we send data from form (path = getter of var) in OBJECT--%>
<form:form action="addStudentMark" method="post">
<table border="1" cellpadding="1" width="60%">
    <tr>
        <th>Subject Name</th>
        <td>
            <select name="subjectId" id="subjectId">
                <option value="0" selected="all"></option>
                <c:forEach var="subjectId" items="${name1}">
                    <option value="${subjectId.key}">${subjectId.value}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <th>"date"</th>
        <th>Mark</th>
    </tr>
    <c:forEach var="marks" items="${list}">

        <tr>
            <td>"DAte</td>
                <%--        here will be a MARK--%>
            <td>${marks.name}</td>
            <security:authorize access="hasRole('ADMIN')">
                <td><a href="editStudent/${student.id}">Edit</a> </td>
                <td><a href="deleteStudent/${student.id}">Delete</a> </td>
            </security:authorize>
            <security:csrfInput/>

        </tr>
    </c:forEach>
    <tr>
<%--        Here will be "date" input form for mark object--%>
        <td><form:input path="date"  /></td>
        <td><form:input path="value"  /></td>
    </tr>


</table>
</form:form>
<tr>
    <td></td>
    <td><input type="submit" value="Check"></td>
</tr>

<%--<security:authorize access="hasRole('ADMIN')">--%>
<%--    <div><td><a href="addStudent">Add new Student Button</a> </td></div>--%>
<%--</security:authorize>--%>
<%--<security:csrfInput/>--%>
<ul>
    <li><a href="../viewAllFacultys">View all facultys</a></li>
    <li><a href="../viewAllGroups">View all groups</a></li>
    <li><a href="../findStudent">Find Student</a></li>
    <li><a href="../logout">LogOut</a></li>

</ul>
</body>
</html>
