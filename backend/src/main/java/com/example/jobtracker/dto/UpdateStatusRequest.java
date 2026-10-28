package com.example.jobtracker.dto;
import com.example.jobtracker.model.ApplicationStatus;
import jakarta.validation.constraints.NotNull;
public record UpdateStatusRequest(@NotNull(message="Status is required") ApplicationStatus status) {}
