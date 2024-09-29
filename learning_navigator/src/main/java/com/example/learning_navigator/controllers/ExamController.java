package com.example.learning_navigator.controllers;

import com.example.learning_navigator.entities.Exam;
import com.example.learning_navigator.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam createdExam = examService.createExam(exam);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExam(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @RequestBody Exam updatedExam) {
        Exam exam = examService.updateExam(id, updatedExam);
        return ResponseEntity.ok(exam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{examId}/register/{studentId}")
    public ResponseEntity<Exam> registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId) {
        Exam exam = examService.registerStudentForExam(examId, studentId);
        return ResponseEntity.ok(exam);
    }
}

