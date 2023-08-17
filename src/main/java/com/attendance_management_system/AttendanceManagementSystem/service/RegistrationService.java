package com.attendance_management_system.AttendanceManagementSystem.service;

import com.attendance_management_system.AttendanceManagementSystem.Dto.SignUpPageDto;
import com.attendance_management_system.AttendanceManagementSystem.entity.User;
import com.attendance_management_system.AttendanceManagementSystem.repository.UserRepository;
import com.attendance_management_system.AttendanceManagementSystem.utils.CustomMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegistrationService {
    @Autowired
    private CustomMailSender mailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public boolean isEmailAlreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }
    public String register(SignUpPageDto signUpPageDto)
    {

       User user = new User();

       user.setEmail(signUpPageDto.getEmail());
       user.setName(signUpPageDto.getName());
       user.setPassword(passwordEncoder.encode(signUpPageDto.getPassword()));
       user.setRole(signUpPageDto.getRole());
        if ("ROLE_FACULTY".equals(signUpPageDto.getRole())) {
            user.setSubject(signUpPageDto.getSubject());
        }
        signUpPageDto.setActive(false);
        String verificationToken = generateVerificationToken();
        signUpPageDto.setVerificationToken(verificationToken);
        user.setVerificationToken(signUpPageDto.getVerificationToken());
        user.setActive(signUpPageDto.isActive());

        userRepository.save(user);
        sendVerificationEmail(signUpPageDto.getEmail(), verificationToken);
        return "home";
    }

    public boolean verifyEmail(String verificationToken) {
        User user = userRepository.findByVerificationToken(verificationToken);
        if (user != null) {
            user.setActive(true);
            user.setVerificationToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    private String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
    private void sendVerificationEmail(String email, String verificationToken) {
        String subject = "Email Verification";
        String body = "Please click the link below to verify your email:\n\n"
                + "http://localhost:8080/verify?token=" + verificationToken;

        mailSender.sendEmail(email, subject, body);
    }

}




