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
import java.sql.SQLException;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Student student = populateStudentFromRequest(request);
            String sql = "INSERT INTO students (student_id, record_book_number, group_name, surname, name, patronymic, mark1, mark2, mark3, mark4) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, student.getStudentId());
                statement.setString(2, student.getRecordBookNumber());
                statement.setString(3, student.getGroup());
                statement.setString(4, student.getSurname());
                statement.setString(5, student.getName());
                statement.setString(6, student.getPatronymic());
                statement.setInt(7, student.getMark1());
                statement.setInt(8, student.getMark2());
                statement.setInt(9, student.getMark3());
                statement.setInt(10, student.getMark4());
                statement.executeUpdate();
            }
            response.sendRedirect("list-students?success=added");
        } catch (SQLException | NumberFormatException e) {
            request.setAttribute("error", "Ошибка при добавлении студента.");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }
    }

    private Student populateStudentFromRequest(HttpServletRequest request) throws NumberFormatException {
        Student student = new Student();
        student.setStudentId(request.getParameter("studentId"));
        student.setRecordBookNumber(request.getParameter("recordBookNumber"));
        student.setGroup(request.getParameter("group"));
        student.setSurname(request.getParameter("surname"));
        student.setName(request.getParameter("name"));
        student.setPatronymic(request.getParameter("patronymic"));
        student.setMark1(Integer.parseInt(request.getParameter("mark1")));
        student.setMark2(Integer.parseInt(request.getParameter("mark2")));
        student.setMark3(Integer.parseInt(request.getParameter("mark3")));
        student.setMark4(Integer.parseInt(request.getParameter("mark4")));
        student.calculateAverageMark();
        return student;
    }
}
