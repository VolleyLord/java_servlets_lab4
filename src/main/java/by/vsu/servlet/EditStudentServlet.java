package by.vsu.servlet;

import by.vsu.model.Student;
import by.vsu.model.StudentManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/edit-student")
public class EditStudentServlet extends HttpServlet {
    private StudentManager studentManager = StudentManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer index = Integer.parseInt(request.getParameter("index"));
            Student student = studentManager.getStudents().get(index);
            request.setAttribute("student", student);
            request.setAttribute("index", index);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid index parameter");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer index = Integer.parseInt(request.getParameter("index"));
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
            studentManager.editStudent(index, student);
            response.sendRedirect("list-students");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid index parameter");
        }
    }
}
