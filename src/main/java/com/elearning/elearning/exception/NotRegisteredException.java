package com.elearning.elearning.exception;

public class NotRegisteredException extends RuntimeException {
    public NotRegisteredException(String message) {
        super(message);
    }

    public NotRegisteredException(Long studentId, Long courseId) {
        super(String.format("Student with id %d is not registered for course with id %d", studentId, courseId));
    }
}
