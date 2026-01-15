package com.elearning.elearning.controller;

import com.elearning.elearning.entity.Course;
import com.elearning.elearning.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public Course createCourse(@Valid @RequestBody Course course){
        return courseService.createCourse(course);
    }

    @GetMapping
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    //Get course by ID
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }

    //Update course
    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody Course course){
        return courseService.updateCourse(id, course);
    }

    //Delete course
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
    }
}
