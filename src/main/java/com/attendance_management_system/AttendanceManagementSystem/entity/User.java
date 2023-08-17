package com.attendance_management_system.AttendanceManagementSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User_Details")
public class User {
    @Id
    @GeneratedValue
    private long userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private String subject;
    private boolean active;
    private String verificationToken;
    private String resetToken;
}
