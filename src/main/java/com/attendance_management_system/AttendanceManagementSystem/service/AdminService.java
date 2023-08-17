package com.attendance_management_system.AttendanceManagementSystem.service;

import com.attendance_management_system.AttendanceManagementSystem.entity.User;
import com.attendance_management_system.AttendanceManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    public List<String> getAllStudentNames() {
        List<User> students = userRepository.findByRole("ROLE_STUDENT");
        return students.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
    public List<String> getAllFacultyNames() {
        List<User> faculties = userRepository.findByRole("ROLE_FACULTY");
        return faculties.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
    public List<String> getFacultyNamesBySubject(String subject) {
        List<User> faculties = userRepository.findByRoleAndSubject("ROLE_FACULTY", subject);
        return faculties.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

}
