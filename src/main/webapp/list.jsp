<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список студентов</title>
</head>
<body>
<h1>Список студентов</h1>

<table border="1">
    <tr>
        <th>ID студента</th>
        <th>Record book number</th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>Отчество</th>
        <th>Группа</th>
        <th>Оценка 1</th>
        <th>Оценка 2</th>
        <th>Оценка 3</th>
        <th>Оценка 4</th>
        <th>Средняя оценка</th>
        <th>Действия</th>
    </tr>
    <jsp:useBean id="students" scope="request" type="java.util.List"/>
    <c:forEach var="student" items="${students}">
        <tr>
            <td>${student.studentId}</td>
            <td>${student.recordBookNumber}</td>
            <td>${student.surname}</td>
            <td>${student.name}</td>
            <td>${student.patronymic}</td>
            <td>${student.group}</td>
            <td>${student.mark1}</td>
            <td>${student.mark2}</td>
            <td>${student.mark3}</td>
            <td>${student.mark4}</td>
            <td>${student.averageMark}</td>
            <td>
                <a href="edit-student?studentId=${student.studentId}">Редактировать</a> |
                <a href="delete-student?studentId=${student.studentId}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="add-student">Добавить студента</a>
<br>
<a href="best-group">Лучшая группа</a>
</body>
</html>
