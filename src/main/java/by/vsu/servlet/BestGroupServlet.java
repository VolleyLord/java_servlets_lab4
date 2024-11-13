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

@WebServlet("/best-group")
public class BestGroupServlet extends HttpServlet {
    private StudentManager studentManager = StudentManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentManager.getStudents();

        String bestGroup = studentManager.findGroupWithHighestAverage();
        req.setAttribute("bestGroup", bestGroup);

        req.getRequestDispatcher("bestGroup.jsp").forward(req, resp);
    }
}
