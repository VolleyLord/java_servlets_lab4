package by.vsu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/student_db"; // Укажите ваш URL
    private static final String USER = "postgres"; // Укажите вашего пользователя
    private static final String PASSWORD = "rootroot"; // Укажите ваш пароль

    static {
        try {
            // Регистрируем драйвер, если он не подгружается автоматически
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Не удалось загрузить драйвер PostgreSQL", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
