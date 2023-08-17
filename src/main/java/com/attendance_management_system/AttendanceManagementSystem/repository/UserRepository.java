package com.attendance_management_system.AttendanceManagementSystem.repository;

import com.attendance_management_system.AttendanceManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u FROM User u WHERE u.email = :email")
  public User findByEmail(@Param("email") String email);
  User findByResetToken(String resetToken);
  User findByVerificationToken(String verificationToken);
  boolean existsByEmail(String email);
  List<User> findByRole(String role);
  List<User> findByRoleAndSubject(String role, String subject);

}
