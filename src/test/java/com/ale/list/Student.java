package com.ale.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Student implements Comparable<Student>{
    private Integer studentId;
    private String name;
    private Integer age;
    private Integer score;

    @Override
    public int compareTo(Student o) {
        return 0;
    }
}
