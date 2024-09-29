package com.example.learning_navigator.services;

import com.example.learning_navigator.entities.Student;
import com.example.learning_navigator.exceptions.ResourceNotFoundException;
import com.example.learning_navigator.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService{

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        existingStudent.setName(studentDetails.getName());
        existingStudent.setRegistrationId(studentDetails.getRegistrationId());
        existingStudent.setSubjects(studentDetails.getSubjects());
        existingStudent.setExams(studentDetails.getExams());

        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }
}

