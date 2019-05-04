package com.studentbuscheck_in;

import java.util.ArrayList;

import static com.studentbuscheck_in.MainActivity.myDatabase;

public class Student {


   private long listId;
    private int studentId;
    private String studentSchoolId;
    private String studentFirstLast;
    private String isPresent;

    private final static String YES = "1";
    private static final String NO = "0";

    public Student () {
        studentFirstLast = "genX";
        studentSchoolId = "12";
        isPresent = YES;
    }
    public Student (int studentId, String studentSchoolId, String studentFirstLast,
                    String isPresent, long listId) {
        this.studentId = studentId;
        this.studentSchoolId = studentSchoolId;
        this.studentFirstLast = studentFirstLast;
        this.isPresent = isPresent;
        this.listId = listId;
    }

    public Student (int studentId, String studentSchoolId, String studentFirstLast,
                    String isPresent) {
        this.studentId = studentId;
        this.studentSchoolId = studentSchoolId;
        this.studentFirstLast = studentFirstLast;
        this.isPresent = isPresent;
    }
    public Student (String studentSchoolId, String studentFirstLast, String isPresent) {

        this.studentSchoolId = studentSchoolId;
        this.studentFirstLast = studentFirstLast;
        this.isPresent = isPresent;

    }

    public long getListId() {return listId; } ;
    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentSchoolId() {
        return studentSchoolId;
    }

    public void setStudentSchoolId(String studentSchoolId) {
        this.studentSchoolId = studentSchoolId;
    }

    public String getStudentFirstLast() {
        return studentFirstLast;
    }

    public void setStudentFirstLast(String studentFirstLast) {
        this.studentFirstLast = studentFirstLast;
    }

    public String getIsPresent() {
        return isPresent;
    }

    public void setIsPresent(String isPresent) {
        this.isPresent = isPresent;
    }
}
