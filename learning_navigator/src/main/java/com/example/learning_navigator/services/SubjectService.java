package com.example.learning_navigator.services;

import com.example.learning_navigator.entities.Student;
import com.example.learning_navigator.entities.Subject;
import com.example.learning_navigator.exceptions.ResourceNotFoundException;
import com.example.learning_navigator.repositories.StudentRepository;
import com.example.learning_navigator.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id " + id));
    }

    public Subject updateSubject(Long id, Subject updatedSubject) {
        Subject subject = getSubjectById(id);
        subject.setName(updatedSubject.getName());
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        Subject subject = getSubjectById(id);
        subjectRepository.delete(subject);
    }

    public Subject enrollStudent(Long subjectId, Long studentId) {
        Subject subject = getSubjectById(subjectId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));

        subject.getStudents().add(student);
        return subjectRepository.save(subject);
    }
}

