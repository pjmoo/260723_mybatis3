package org.example.mybatis3.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.mybatis3.dto.CourseWithStudentsDTO;
import org.example.mybatis3.dto.StudentWithCoursesDTO;
import org.example.mybatis3.entity.Course;
import org.example.mybatis3.entity.Student;

import java.util.List;

@Mapper
public interface EnrollMapper {
    void insertStudent(Student student);

    void insertCourse(Course course);

    List<Student> findAllStudents();

    List<Course> findAllCourses();

    // step2
    void insertEnrollment(Student student, Course course);

    List<StudentWithCoursesDTO> findAllStudentsWithCourses();

    List<CourseWithStudentsDTO> findAllCoursesWithStudents();
}
