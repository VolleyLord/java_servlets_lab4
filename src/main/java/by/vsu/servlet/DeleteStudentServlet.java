package by.vsu.servlet;

import by.vsu.model.StudentManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-student")
public class DeleteStudentServlet extends HttpServlet {
    private final StudentManager studentManager = StudentManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indexParam = request.getParameter("index");
        if (indexParam == null || indexParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or empty index parameter");
            return;
        }
        int index = Integer.parseInt(indexParam);
        studentManager.deleteStudent(index);
        response.sendRedirect("list-students");
    }
}

