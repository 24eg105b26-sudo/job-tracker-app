package com.example.jobtracker.repository;
import com.example.jobtracker.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {
 List<JobApplication> findAllByOrderByApplicationDateDesc();
 List<JobApplication> findByStatusOrderByApplicationDateDesc(ApplicationStatus status);
}
