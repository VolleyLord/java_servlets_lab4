<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить студента</title>
</head>
<body>
<h1>Добавить студента</h1>

<c:if test="${not empty error}">
  <p style="color: red;">${error}</p>
</c:if>

<form action="add-student" method="post">
  <p>ID студента: <input type="text" name="studentId" required/></p>
  <p>Record book number: <input type="text" name="recordBookNumber" required/></p>
  <p>Фамилия: <input type="text" name="surname" required/></p>
  <p>Имя: <input type="text" name="name" required/></p>
  <p>Отчество: <input type="text" name="patronymic" required/></p>
  <p>Группа: <input type="text" name="group" required/></p>
  <p>Оценка 1: <input type="number" name="mark1" min="0" max="10" required/></p>
  <p>Оценка 2: <input type="number" name="mark2" min="0" max="10" required/></p>
  <p>Оценка 3: <input type="number" name="mark3" min="0" max="10" required/></p>
  <p>Оценка 4: <input type="number" name="mark4" min="0" max="10" required/></p>
  <input type="submit" value="Добавить"/>
</form>

<a href="list-students">Вернуться к списку студентов</a>
</body>
</html>

