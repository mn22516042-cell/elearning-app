package com.elearning.exception;

public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException(String message) {
        super(message);
    }

    public AlreadyRegisteredException(Long studentId, Long courseId) {
        super(String.format("Student with id %d is already registered for course with id %d", studentId, courseId));
    }
}
