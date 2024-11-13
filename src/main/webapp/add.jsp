<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Добавить студента</title>
</head>
<body>
<h1>Добавить студента</h1>

<form action="add-student" method="post">
  <p>ID студента: <input type="text" name="studentId"/></p>
  <p>record book number: <input type="text" name="recordBookNumber"/></p>
  <p>Фамилия: <input type="text" name="surname"/></p>
  <p>Имя: <input type="text" name="name"/></p>
  <p>Отчество: <input type="text" name="patronymic"/></p>
  <p>Группа: <input type="text" name="group"/></p>
  <p>Оценка 1: <input type="number" name="mark1" min="0" max="10"/></p>
  <p>Оценка 2: <input type="number" name="mark2" min="0" max="10"/></p>
  <p>Оценка 3: <input type="number" name="mark3" min="0" max="10"/></p>
  <p>Оценка 4: <input type="number" name="mark4" min="0" max="10"/></p>

  <input type="submit" value="Добавить"/>
</form>
</body>
</html>
