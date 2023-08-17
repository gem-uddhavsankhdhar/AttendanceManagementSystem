package com.attendance_management_system.AttendanceManagementSystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginPageDto {

    private String email;
    private String password;
}
