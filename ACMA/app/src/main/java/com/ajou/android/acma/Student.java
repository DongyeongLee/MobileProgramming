package com.ajou.android.acma;

public class Student {
    private String id; // id-이메일 형식
    private String password; // 비밀번호
    private String name; // 이름
    private int grade;  // 1, 2, 3, 4
    private int sex;    // 0 - male, 1 - female

    public Student(String id, String password, String name, int grade, int sex) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public int getSex() {
        return sex;
    }
}
