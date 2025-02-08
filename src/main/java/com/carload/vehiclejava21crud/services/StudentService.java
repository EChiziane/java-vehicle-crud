package com.carload.vehiclejava21crud.services;

import com.carload.vehiclejava21crud.models.StudentEntity;
import com.carload.vehiclejava21crud.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<StudentEntity> findAll() {
        return studentRepository.findAll();
    }

    public Optional<StudentEntity> findById(UUID id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public StudentEntity save(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    @Transactional
    public StudentEntity update(StudentEntity studentEntity) {
        if (!existsById(studentEntity.getId())) {
            throw new IllegalArgumentException("Student not found with ID: " + studentEntity.getId());
        }
        return studentRepository.save(studentEntity);
    }

    @Transactional
    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }

    public boolean existsById(UUID id) {
        return studentRepository.existsById(id);
    }
}
