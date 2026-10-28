package com.example.jobtracker;
import com.example.jobtracker.model.*;
import com.example.jobtracker.repository.JobApplicationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class JobApplicationControllerTest {
 @Autowired MockMvc mvc; @Autowired JobApplicationRepository repo;
 @BeforeEach void clean(){repo.deleteAll();}
 @Test void createsApplication() throws Exception {
  String body="{\"companyName\":\"Acme\",\"jobRole\":\"Developer\",\"applicationDate\":\"2026-10-20\",\"status\":\"Applied\"}";
  mvc.perform(post("/api/applications").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated()).andExpect(jsonPath("$.companyName").value("Acme"));
 }
 @Test void rejectsBlankCompany() throws Exception {
  String body="{\"companyName\":\" \",\"jobRole\":\"Developer\",\"applicationDate\":\"2026-10-20\"}";
  mvc.perform(post("/api/applications").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andExpect(jsonPath("$.message").exists());
 }
 @Test void filtersByStatus() throws Exception {
  repo.save(new JobApplication("Acme","Developer",LocalDate.of(2026,10,20),ApplicationStatus.Interview));
  repo.save(new JobApplication("Beta","Engineer",LocalDate.of(2026,10,19),ApplicationStatus.Applied));
  mvc.perform(get("/api/applications").param("status","Interview")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(1));
 }
 @Test void updatesStatus() throws Exception {
  JobApplication a=repo.save(new JobApplication("Acme","Developer",LocalDate.now(),ApplicationStatus.Applied));
  mvc.perform(patch("/api/applications/"+a.getId()+"/status").contentType(MediaType.APPLICATION_JSON).content("{\"status\":\"Selected\"}")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("Selected"));
 }
}
