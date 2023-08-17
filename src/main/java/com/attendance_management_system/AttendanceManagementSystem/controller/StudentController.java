package com.attendance_management_system.AttendanceManagementSystem.controller;

import com.attendance_management_system.AttendanceManagementSystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/student/getFacultyBySubject")
    public ModelAndView getFacultyBySubject(@RequestParam(required = false) String subject, Model model) {
        if (subject != null) {
            List<String> facultyNames = adminService.getFacultyNamesBySubject(subject);
            if (!facultyNames.isEmpty()) {
                model.addAttribute("facultyNames", facultyNames);
            }
        }
        return new ModelAndView("GetFacultyBySub");
    }
}
