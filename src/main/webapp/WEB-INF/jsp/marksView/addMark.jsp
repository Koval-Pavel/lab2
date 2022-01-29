<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Add new Mark:</title>
</head>
<body>
<h2>Add new Mark</h2>
<form:form action="../saveMark/${studentId}" method="post">
    <table>
        <tr>
            <td>Mark Date (10-10-2021)</td>
            <td><form:input path="date"/></td>
            <td>Mark Value</td>
            <td>
            <select name="value" id="value">
                    <option value=1>1</option>
                    <option value=2>2</option>
                    <option value=3>3</option>
                    <option value=4>4</option>
                    <option value=5>5</option>
            </select>
            </td>>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form:form>
</body>
</html>
