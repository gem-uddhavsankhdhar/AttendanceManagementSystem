package com.attendance_management_system.AttendanceManagementSystem.controller;

import com.attendance_management_system.AttendanceManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/fetchAllStudents")
    public ResponseEntity<List<String>> getAllStudents() {
        List<String> studentNames = adminService.getAllStudentNames();
        return ResponseEntity.ok(studentNames);
    }
    @GetMapping("/admin/fetchAllFaculties")
    public ResponseEntity<List<String>> getAllFaculties() {
        List<String> facultyNames = adminService.getAllFacultyNames();
        return ResponseEntity.ok(facultyNames);
    }

}
