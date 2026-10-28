package com.example.jobtracker.dto;
import com.example.jobtracker.model.ApplicationStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
public record CreateApplicationRequest(@NotBlank(message="Company name is required") String companyName,
 @NotBlank(message="Job role is required") String jobRole,
 @NotNull(message="Application date is required") @PastOrPresent(message="Application date cannot be in the future") LocalDate applicationDate,
 ApplicationStatus status) {}
