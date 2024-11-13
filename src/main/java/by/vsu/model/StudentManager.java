package by.vsu.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class StudentManager {
    private static StudentManager instance;
    private List<Student> students = new ArrayList<>();
    private final String filePath = "src/main/resources/data.ser"; // Укажите путь к вашему файлу

    private StudentManager() {
        loadStudents();
    }

    public static synchronized StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Added student: " + student.getStudentId());
        saveStudents(); // Сохраняем студентов после добавления
    }

    public List<Student> getStudents() {
        return students;
    }

    public void deleteStudent(int index) {
        if (index >= 0 && index < students.size()) {
            students.remove(index);
            saveStudents(); // Сохраняем студентов после удаления
        }
    }

    public void editStudent(int index, Student student) {
        if (index >= 0 && index < students.size()) {
            students.set(index, student);
            saveStudents(); // Сохраняем студентов после редактирования
        }
    }

    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            students = (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            students = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveStudents() {
        File file = new File(filePath);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();  // Create the directory if it doesn't exist
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для поиска группы с наивысшей средней оценкой
    public String findGroupWithHighestAverage() {
        Map<String, Double> groupAverageMarks = new HashMap<>();
        Map<String, Integer> groupCounts = new HashMap<>();

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
}
