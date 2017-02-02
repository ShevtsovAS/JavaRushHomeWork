package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        if (students.isEmpty()) return null;
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return Double.compare(student2.getAverageGrade(), student1.getAverageGrade());
            }
        });
        return sortedStudents.get(0);
    }

    public Student getStudentWithMinAverageGrade() {
        if (students.isEmpty()) return null;
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return Double.compare(student1.getAverageGrade(), student2.getAverageGrade());
            }
        });
        return sortedStudents.get(0);
    }

    public void expel(Student student) {
        //TODO:
        students.remove(student);
    }
}
