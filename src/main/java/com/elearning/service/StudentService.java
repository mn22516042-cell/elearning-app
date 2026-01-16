package com.elearning.service;

import com.elearning.entity.Course;
import com.elearning.entity.Student;
import com.elearning.exception.AlreadyRegisteredException;
import com.elearning.exception.NotRegisteredException;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id));
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id));

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", id));

        studentRepository.delete(student);
    }

    @Transactional
    public Student registerStudentToCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

        // Check if student is already registered
        if (student.getCourses().contains(course)) {
            throw new AlreadyRegisteredException(studentId, courseId);
        }
        
        student.getCourses().add(course);
        return studentRepository.save(student);
    }

    @Transactional
    public Student unregisterStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseId));

        // Check if student is registered
        if (!student.getCourses().contains(course)) {
            throw new NotRegisteredException(studentId, courseId);
        }
        
        student.getCourses().remove(course);
        return studentRepository.save(student);
    }
}