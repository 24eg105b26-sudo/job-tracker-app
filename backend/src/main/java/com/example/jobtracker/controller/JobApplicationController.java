package com.example.jobtracker.controller;
import com.example.jobtracker.dto.*;
import com.example.jobtracker.model.*;
import com.example.jobtracker.repository.JobApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/applications")
@CrossOrigin(origins={"http://localhost:5173","http://127.0.0.1:5173"})
public class JobApplicationController {
 private final JobApplicationRepository repo;
 public JobApplicationController(JobApplicationRepository r){repo=r;}
 @GetMapping public List<JobApplication> list(@RequestParam(required=false) ApplicationStatus status){
  return status==null?repo.findAllByOrderByApplicationDateDesc():repo.findByStatusOrderByApplicationDateDesc(status);
 }
 @GetMapping("/count") public long count(){return repo.count();}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public JobApplication create(@Valid @RequestBody CreateApplicationRequest r){
  return repo.save(new JobApplication(r.companyName().trim(),r.jobRole().trim(),r.applicationDate(),r.status()==null?ApplicationStatus.Applied:r.status()));
 }
 @PatchMapping("/{id}/status") public JobApplication update(@PathVariable Long id,@Valid @RequestBody UpdateStatusRequest r){
  JobApplication a=repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Application not found"));
  a.setStatus(r.status()); return repo.save(a);
 }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){
  if(!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Application not found"); repo.deleteById(id);
 }
}
