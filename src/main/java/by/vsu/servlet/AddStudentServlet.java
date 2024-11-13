package by.vsu.servlet;

import by.vsu.model.Student;
import by.vsu.model.StudentManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
    private StudentManager studentManager = StudentManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        studentManager.addStudent(student);
        response.sendRedirect("list-students");
    }
}
