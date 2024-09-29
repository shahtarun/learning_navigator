package com.example.learning_navigator.service;

import com.example.learning_navigator.entities.Student;
import com.example.learning_navigator.exceptions.ResourceNotFoundException;
import com.example.learning_navigator.repositories.StudentRepository;
import com.example.learning_navigator.services.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1L);
        student.setRegistrationId("STU123");
        student.setName("John Doe");
    }

    @Test
    public void testGetStudentById_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student found = studentService.getStudentById(1L);
        assertNotNull(found);
        assertEquals(student.getName(), found.getName());
    }

    @Test
    public void testGetStudentById_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            studentService.getStudentById(1L);
        });
    }

    @Test
    public void testCreateStudent_Success() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student created = studentService.createStudent(student);
        assertNotNull(created);
        assertEquals(student.getName(), created.getName());
    }
}

