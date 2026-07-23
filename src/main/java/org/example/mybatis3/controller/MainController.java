package org.example.mybatis3.controller;

import lombok.RequiredArgsConstructor;
import org.example.mybatis3.entity.Course;
import org.example.mybatis3.entity.Student;
import org.example.mybatis3.service.EnrollService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {
    private final EnrollService enrollService;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("students", enrollService.findAllStudents());
        model.addAttribute("courses", enrollService.findAllCourses());
        model.addAttribute("studentWithCourses", enrollService.findAllStudentsWithCourses());
        model.addAttribute("courseWithStudents", enrollService.findAllCoursesWithStudents());
        return "index";
    }

    @PostMapping("/students")
    public String addStudent(@ModelAttribute Student student) {
        enrollService.createStudent(student);
        return "redirect:/";
    }

    @PostMapping("/courses")
    public String addCourse(@ModelAttribute Course course) {
        enrollService.createCourse(course);
        return "redirect:/";
    }

    @PostMapping("/enrollments")
    public String addEnrollment(@RequestParam Long studentId, @RequestParam Long courseId) {
        Student student = new Student();
        student.setId(studentId);
        Course course = new Course();
        course.setId(courseId);
        enrollService.createEnrollment(student, course);
        return "redirect:/";
    }
}
