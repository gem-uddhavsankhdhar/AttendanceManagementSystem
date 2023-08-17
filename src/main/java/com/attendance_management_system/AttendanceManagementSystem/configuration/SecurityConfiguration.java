package com.attendance_management_system.AttendanceManagementSystem.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Bean
    @Autowired
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->configurer
                        .requestMatchers("/","/home","/register","/registration","/reset-password",
                                "/forgot-password",
                                "/verify","/verificationSuccess","/verificationError","/verification?verification=true").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/faculty/**").hasRole("FACULTY")
                        .requestMatchers("/student/**").hasRole("STUDENT")
                        .anyRequest().authenticated())
                        .formLogin(form->form.loginPage("/signin")
                                .loginProcessingUrl("/do-login")
                                .defaultSuccessUrl("/").permitAll())
                .logout(logout->logout.permitAll())
                .exceptionHandling(e->e.accessDeniedHandler(accessDeniedHandler()));// Set the custom AccessDeniedHandler
        return http.build();
    }


    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/access-denied");
        };
    }
}

