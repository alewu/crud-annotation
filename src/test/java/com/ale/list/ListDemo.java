package com.ale.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListDemo {



    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Jack", 18, 88));
        students.add(new Student(1,"Rose", 29, 18));
        students.add(new Student(1,"Bob", 12, 48));
        students.add(new Student(1,"Michale", 36, 67));
        students.add(new Student(1,"Ben", 54, 89));
        students.add(new Student(1,"Jane", 7, 90));

        Collections.sort(students);
    }
}
