package com.carload.vehiclejava21crud.controller;

import com.carload.vehiclejava21crud.models.Student;
import com.carload.vehiclejava21crud.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> showAllStudents() {
        List<Student> students = studentService.findAll();
        return students;

    }
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        studentService.save(student);
        return student;
    }
}
