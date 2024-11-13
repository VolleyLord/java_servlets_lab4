package by.vsu.model;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String studentId;
    private String recordBookNumber;
    //private String groupNumber;
    private String group;
    private String surname;
    private String name;
    private String patronymic;
    private int mark1;
    private int mark2;
    private int mark3;
    private int mark4;
    private double averageMark;

    public Student(String studentId,String recordBookNumber,String group, String surname, String name, String patronymic, int mark1, int mark2, int mark3, int mark4) {
        this.studentId = studentId;
        this.recordBookNumber = recordBookNumber;
        //this.groupNumber = groupNumber;
        this.group = group;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
        this.mark4 = mark4;
        this.averageMark = calculateAverageMark();
    }

    public Student() {}

    // Геттеры и сеттеры для полей

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    public void setRecordBookNumber(String recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
    }

//    public String getGroupNumber() {
//        return groupNumber;
//    }
//
//    public void setGroupNumber(String groupNumber) {
//        this.groupNumber = groupNumber;
//    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
        calculateAverageMark();
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
        calculateAverageMark();
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
        calculateAverageMark();
    }

    public int getMark4() {
        return mark4;
    }

    public void setMark4(int mark4) {
        this.mark4 = mark4;
        calculateAverageMark();
    }

    public double calculateAverageMark() {
        this.averageMark = (mark1 + mark2 + mark3 + mark4) / 4.0;
        return this.averageMark;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }
}
