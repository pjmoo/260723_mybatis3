package org.example.mybatis3.dto;

import lombok.*;
import org.example.mybatis3.entity.Student;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseWithStudentsDTO {
    private Long id;
    private String name;
    private List<Student> students = new ArrayList<>();
}
