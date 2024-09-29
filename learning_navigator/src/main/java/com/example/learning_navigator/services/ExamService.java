package com.example.learning_navigator.services;

import com.example.learning_navigator.entities.Exam;
import com.example.learning_navigator.entities.Student;
import com.example.learning_navigator.exceptions.ResourceNotFoundException;
import com.example.learning_navigator.repositories.ExamRepository;
import com.example.learning_navigator.repositories.StudentRepository;
import com.example.learning_navigator.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id " + id));
    }

    public Exam updateExam(Long id, Exam updatedExam) {
        Exam exam = getExamById(id);
        exam.setSubject(updatedExam.getSubject());
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        Exam exam = getExamById(id);
        examRepository.delete(exam);
    }

    public Exam registerStudentForExam(Long examId, Long studentId) {
        Exam exam = getExamById(examId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));

        if (!student.getSubjects().contains(exam.getSubject())) {
            throw new IllegalStateException("Student must be enrolled in the subject before registering for the exam.");
        }

        exam.getStudents().add(student);
        return examRepository.save(exam);
    }
}


