package com.woofking.management.controller;

import com.woofking.management.service.NotificationService;
import com.woofking.management.util.Notification;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping(value = "/supervisors")
    public List<String> getSupervisors() {
        return notificationService.getSupervisors();
    }

    @PostMapping("/submit")
    public ResponseEntity<String> createNotificationRequest(@RequestBody @Valid Notification request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificationService.createNotificationRequest(request));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException ex) {
        // Handle the exception and return a custom error response
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : ex.getFieldErrors()) {
            errorMessage.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append("\n");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
    }

}
