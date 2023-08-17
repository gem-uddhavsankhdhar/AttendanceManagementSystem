package com.attendance_management_system.AttendanceManagementSystem.controller;

import com.attendance_management_system.AttendanceManagementSystem.entity.User;
import com.attendance_management_system.AttendanceManagementSystem.repository.UserRepository;
import com.attendance_management_system.AttendanceManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FacultyController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/faculty/getAllStudents")
    public ResponseEntity<List<String>> getAllStudents() {
        List<String> studentNames = adminService.getAllStudentNames();
        return ResponseEntity.ok(studentNames);
    }

}