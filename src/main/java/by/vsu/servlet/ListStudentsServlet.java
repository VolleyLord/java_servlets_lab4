package by.vsu.servlet;

import by.vsu.model.Student;
import by.vsu.model.StudentManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/list-students")
public class ListStudentsServlet extends HttpServlet {
    private StudentManager studentManager = StudentManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentManager.getStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }
}
