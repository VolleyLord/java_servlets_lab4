package by.vsu.servlet;

import by.vsu.util.DatabaseConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/best-group")
public class BestGroupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "SELECT group_name, AVG(mark1 + mark2 + mark3 + mark4) / 4 AS avg_mark FROM students GROUP BY group_name ORDER BY avg_mark DESC LIMIT 1";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    String bestGroup = resultSet.getString("group_name");
                    req.setAttribute("bestGroup", bestGroup);
                } else {
                    req.setAttribute("bestGroup", "Группы не найдены");
                }
            }
            req.getRequestDispatcher("bestGroup.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Ошибка при определении лучшей группы.", e);
        }
    }
}
