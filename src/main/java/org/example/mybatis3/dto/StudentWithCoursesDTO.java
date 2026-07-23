package org.example.mybatis3.dto;

import lombok.*;
import org.example.mybatis3.entity.Course;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentWithCoursesDTO {
    private Long id;
    private String name;
    private List<Course> courses = new ArrayList<>();
}
