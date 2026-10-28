package com.example.jobtracker.model;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity @Table(name="job_applications")
public class JobApplication {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String companyName;
 @Column(nullable=false) private String jobRole;
 @Column(nullable=false) private LocalDate applicationDate;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private ApplicationStatus status;
 protected JobApplication() {}
 public JobApplication(String c,String r,LocalDate d,ApplicationStatus s){companyName=c;jobRole=r;applicationDate=d;status=s;}
 public Long getId(){return id;} public String getCompanyName(){return companyName;}
 public String getJobRole(){return jobRole;} public LocalDate getApplicationDate(){return applicationDate;}
 public ApplicationStatus getStatus(){return status;} public void setStatus(ApplicationStatus s){status=s;}
}
