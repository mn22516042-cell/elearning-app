package com.elearning.elearning.controller;

import com.elearning.elearning.entity.Course;
import com.elearning.elearning.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }
    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseRepository.save(course);
    }
    @GetMapping
    public List<Course>getAllCourses(){
        return courseRepository.findAll();
    }
}
