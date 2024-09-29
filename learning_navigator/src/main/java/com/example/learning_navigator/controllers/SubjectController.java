package com.example.learning_navigator.controllers;

import com.example.learning_navigator.entities.Subject;
import com.example.learning_navigator.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.createSubject(subject);
        return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        Subject subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject updatedSubject) {
        Subject subject = subjectService.updateSubject(id, updatedSubject);
        return ResponseEntity.ok(subject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{subjectId}/enroll/{studentId}")
    public ResponseEntity<Subject> enrollStudent(@PathVariable Long subjectId, @PathVariable Long studentId) {
        Subject subject = subjectService.enrollStudent(subjectId, studentId);
        return ResponseEntity.ok(subject);
    }
}
