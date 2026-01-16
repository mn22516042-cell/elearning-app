package com.elearning.controller;

import com.elearning.entity.Student;
import com.elearning.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    //create student
    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student){
        return studentService.createStudent(student);
    }

    //Get all students
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    //Get student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    //Update student
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    //Delete student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    //Register student to course
    @PostMapping("/{studentId}/courses/{courseId}")
    public Student registerStudentToCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId){
        return studentService.registerStudentToCourse(studentId, courseId);
    }

    //Unregister student from course
    @DeleteMapping("/{studentId}/courses/{courseId}")
    public Student unregisterStudentFromCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId){
        return studentService.unregisterStudentFromCourse(studentId, courseId);
    }
}
