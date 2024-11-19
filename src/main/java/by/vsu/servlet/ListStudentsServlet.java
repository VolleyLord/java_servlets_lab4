package by.vsu.servlet;

import by.vsu.model.Student;
import by.vsu.util.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/list-students")
public class ListStudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM students";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setStudentId(resultSet.getString("student_id"));
                    student.setRecordBookNumber(resultSet.getString("record_book_number"));
                    student.setGroup(resultSet.getString("group_name"));
                    student.setSurname(resultSet.getString("surname"));
                    student.setName(resultSet.getString("name"));
                    student.setPatronymic(resultSet.getString("patronymic"));
                    student.setMark1(resultSet.getInt("mark1"));
                    student.setMark2(resultSet.getInt("mark2"));
                    student.setMark3(resultSet.getInt("mark3"));
                    student.setMark4(resultSet.getInt("mark4"));
                    student.calculateAverageMark();
                    students.add(student);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new ServletException("Ошибка при получении списка студентов.", e);
        }
        request.setAttribute("students", students);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
