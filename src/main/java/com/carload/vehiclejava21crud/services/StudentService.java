package com.carload.vehiclejava21crud.services;

import com.carload.vehiclejava21crud.models.Student;
import com.carload.vehiclejava21crud.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class StudentService {

private final StudentRepository studentRepository;

public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
}

public void save(Student student) {
    studentRepository.save(student);
}

public List<Student> findAll() {
    return this.studentRepository.findAll();
}

public Student findById(UUID id) {
    return this.studentRepository.findById(id).orElse(null);

}

public void deleteById(UUID id) {
    this.studentRepository.deleteById(id);
}

public void update(Student student) {
    this.studentRepository.save(student);
}
}
