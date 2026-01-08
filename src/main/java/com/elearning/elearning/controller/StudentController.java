package com.elearning.elearning.controller;

import com.elearning.elearning.entity.Course;
import com.elearning.elearning.entity.Student;
import com.elearning.elearning.repository.CourseRepository;
import com.elearning.elearning.repository.StudentRepository;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/student")


public class StudentController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentController(StudentRepository studentRepository,CourseRepository courseRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    //create student
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }
    //Get all students
    @GetMapping
    public List<Student>getAllStudents(){
        return studentRepository.findAll();
    }
    @PutMapping("/{studentId}/courses/{courseId}")
    public Student registerStudentToCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(()-> new RuntimeException("course not found"));

        student.getCourses().add(course);
        return studentRepository.save(student);
    }

}
