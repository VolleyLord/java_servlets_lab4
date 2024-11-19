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
    private final StudentManager studentManager = StudentManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String studentId = request.getParameter("studentId");
            if (studentId == null || studentId.isEmpty()) {
                throw new IllegalArgumentException("Идентификатор студента не передан.");
            }

            Student student = studentManager.findStudentById(studentId);
            if (student == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Студент с таким идентификатором не найден.");
                return;
            }

            request.setAttribute("student", student);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String studentId = request.getParameter("studentId");
            if (studentId == null || studentId.isEmpty()) {
                throw new IllegalArgumentException("Идентификатор студента не передан.");
            }

            Student student = populateStudentFromRequest(request);
            student.setStudentId(studentId); // Убедимся, что ID остаётся неизменным
            studentManager.updateStudent(student);

            response.sendRedirect("list-students?success=edited");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        }
    }

    private Student populateStudentFromRequest(HttpServletRequest request) throws NumberFormatException {
        Student student = new Student();
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
