package org.example.mybatis3.service;

import lombok.RequiredArgsConstructor;
import org.example.mybatis3.dto.CourseWithStudentsDTO;
import org.example.mybatis3.dto.StudentWithCoursesDTO;
import org.example.mybatis3.entity.Course;
import org.example.mybatis3.entity.Student;
import org.example.mybatis3.mapper.EnrollMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollService {
    private final EnrollMapper enrollMapper;

    public void createStudent(Student student) {
        enrollMapper.insertStudent(student);
    }

    public void createCourse(Course course) {
        enrollMapper.insertCourse(course);
    }

    public List<Student> findAllStudents() {
        return enrollMapper.findAllStudents();
    }

    public List<Course> findAllCourses() {
        return enrollMapper.findAllCourses();
    }

    public void createEnrollment(Student student, Course course) {
        enrollMapper.insertEnrollment(student, course);
    }

    public List<StudentWithCoursesDTO> findAllStudentsWithCourses() {
        return enrollMapper.findAllStudentsWithCourses();
    }

    public List<CourseWithStudentsDTO> findAllCoursesWithStudents() {
        return enrollMapper.findAllCoursesWithStudents();
    }
}
