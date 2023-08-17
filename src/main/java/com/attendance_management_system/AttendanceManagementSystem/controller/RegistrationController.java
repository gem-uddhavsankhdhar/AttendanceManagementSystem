package com.attendance_management_system.AttendanceManagementSystem.controller;


import com.attendance_management_system.AttendanceManagementSystem.Dto.SignUpPageDto;
import com.attendance_management_system.AttendanceManagementSystem.service.RegistrationService;
import com.attendance_management_system.AttendanceManagementSystem.utils.CustomMailSender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
    public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

        @Autowired
        private CustomMailSender mailSender;
    @PostMapping("/registration")
    public String processRegistrationForm(@ModelAttribute("user") @Valid SignUpPageDto signUpPageDto, Model model, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("ERROR: " + result.toString());
            return "SignupForm";
        }
        if (registrationService.isEmailAlreadyRegistered(signUpPageDto.getEmail())) {
            model.addAttribute("errorMessage", "Email is already registered.");
            return "SignupForm";
        }

        String verificationToken = registrationService.register(signUpPageDto);
        if (verificationToken != null) {
            model.addAttribute("successMessage", "Successfully registered. We have sent a verification link to your email. Please verify your email to activate your account.");
        } else {
            model.addAttribute("errorMessage", "An error occurred during registration.");
        }

        return "SignupForm";
    }

    @GetMapping("/verificationSuccess")
    public String verificationSuccess() {
        return "verificationSuccess";
    }

    @GetMapping("/verificationError")
    public String verificationError() {
        return "verificationError";
    }

    @GetMapping("/verify")
    public String verifyEmail(@RequestParam("token") String verificationToken) {
        boolean isVerified = registrationService.verifyEmail(verificationToken);

        if (isVerified) {

            return "redirect:/verificationSuccess";
        } else {

            return "redirect:/verificationError";
        }
    }
}


