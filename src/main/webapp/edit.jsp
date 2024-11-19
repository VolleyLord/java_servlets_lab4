<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Редактировать студента</title>
</head>
<body>
<h1>Редактировать студента</h1>

<c:if test="${not empty error}">
  <p style="color: red;">${error}</p>
</c:if>

<form action="edit-student" method="post">
  <!-- Используем studentId вместо index -->
  <input type="hidden" name="studentId" value="${student.studentId}"/>

  <p>ID студента: <input type="text" name="studentId" value="${student.studentId}" readonly/></p>
  <p>record book number: <input type="text" name="recordBookNumber" value="${student.recordBookNumber}"/></p>
  <p>Фамилия: <input type="text" name="surname" value="${student.surname}"/></p>
  <p>Имя: <input type="text" name="name" value="${student.name}"/></p>
  <p>Отчество: <input type="text" name="patronymic" value="${student.patronymic}"/></p>
  <p>Группа: <input type="text" name="group" value="${student.group}"/></p>
  <p>Оценка 1: <input type="number" name="mark1" min="0" max="10" value="${student.mark1}"/></p>
  <p>Оценка 2: <input type="number" name="mark2" min="0" max="10" value="${student.mark2}"/></p>
  <p>Оценка 3: <input type="number" name="mark3" min="0" max="10" value="${student.mark3}"/></p>
  <p>Оценка 4: <input type="number" name="mark4" min="0" max="10" value="${student.mark4}"/></p>

  <input type="submit" value="Сохранить"/>
</form>
</body>
</html>
