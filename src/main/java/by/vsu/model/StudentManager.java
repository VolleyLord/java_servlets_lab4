package by.vsu.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class StudentManager {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/student_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "rootroot";

    private static StudentManager instance;

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getString("student_id"),
                        rs.getString("record_book_number"),
                        rs.getString("group_name"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("patronymic"),
                        rs.getInt("mark1"),
                        rs.getInt("mark2"),
                        rs.getInt("mark3"),
                        rs.getInt("mark4")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void addStudent(Student student) {
        String query = "INSERT INTO students (student_id, record_book_number, group_name, surname, name, patronymic, mark1, mark2, mark3, mark4) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getRecordBookNumber());
            stmt.setString(3, student.getGroup());
            stmt.setString(4, student.getSurname());
            stmt.setString(5, student.getName());
            stmt.setString(6, student.getPatronymic());
            stmt.setInt(7, student.getMark1());
            stmt.setInt(8, student.getMark2());
            stmt.setInt(9, student.getMark3());
            stmt.setInt(10, student.getMark4());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    public void deleteStudent(String studentId) {
        String query = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        String query = "UPDATE students SET record_book_number = ?, group_name = ?, surname = ?, name = ?, patronymic = ?, mark1 = ?, mark2 = ?, mark3 = ?, mark4 = ? " +
                "WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, student.getRecordBookNumber());
            stmt.setString(2, student.getGroup());
            stmt.setString(3, student.getSurname());
            stmt.setString(4, student.getName());
            stmt.setString(5, student.getPatronymic());
            stmt.setInt(6, student.getMark1());
            stmt.setInt(7, student.getMark2());
            stmt.setInt(8, student.getMark3());
            stmt.setInt(9, student.getMark4());
            stmt.setString(10, student.getStudentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для поиска группы с наивысшей средней оценкой
    public String findGroupWithHighestAverage() {
        Map<String, Double> groupAverageMarks = new HashMap<>();
        Map<String, Integer> groupCounts = new HashMap<>();

        List<Student> students = getStudents();
        // Рассчитываем сумму оценок и количество студентов в каждой группе
        for (Student student : students) {
            String group = student.getGroup();
            double averageMark = student.getAverageMark();

            groupAverageMarks.put(group, groupAverageMarks.getOrDefault(group, 0.0) + averageMark);
            groupCounts.put(group, groupCounts.getOrDefault(group, 0) + 1);
        }

        String bestGroup = null;
        double highestAverage = 0.0;

        // Рассчитываем среднюю оценку для каждой группы
        for (String group : groupAverageMarks.keySet()) {
            double totalMarks = groupAverageMarks.get(group);
            int count = groupCounts.get(group);
            double groupAverage = totalMarks / count;

            if (bestGroup == null || groupAverage > highestAverage) {
                bestGroup = group;
                highestAverage = groupAverage;
            }
        }

        return bestGroup;
    }

    public Student findStudentById(String studentId) {
        List<Student> students = getStudents();
        return students.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

}
