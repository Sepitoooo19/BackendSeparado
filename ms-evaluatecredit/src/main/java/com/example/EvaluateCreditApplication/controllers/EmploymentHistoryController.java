package com.example.EvaluateCreditApplication.controllers;

import com.example.EvaluateCreditApplication.entities.EmploymentHistoryEntity;
import com.example.EvaluateCreditApplication.services.EmploymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employmentHistory/evaluate")
public class EmploymentHistoryController {

    @Autowired
    private EmploymentHistoryService employmentHistoryService;

    @GetMapping
    public ResponseEntity<List<EmploymentHistoryEntity>> findAll() {
        return ResponseEntity.ok(employmentHistoryService.findAll());
    }

    @GetMapping("/{employmentHistory_id}")
    public ResponseEntity<EmploymentHistoryEntity> findByEmploymentHistoryId(@PathVariable Long employmentHistory_id) {
        return ResponseEntity.ok(employmentHistoryService.findByEmploymentHistoryId(employmentHistory_id));
    }

    @GetMapping("/job_title/{job_title}")
    public ResponseEntity<EmploymentHistoryEntity> findByJobTitle(@PathVariable String job_title) {
        return ResponseEntity.ok(employmentHistoryService.findByJobTitle(job_title));
    }

    @GetMapping("/company_name/{company_name}")
    public ResponseEntity<EmploymentHistoryEntity> findByCompanyName(@PathVariable String company_name) {
        return ResponseEntity.ok(employmentHistoryService.findByCompanyName(company_name));
    }

    @GetMapping("/salary/{salary}")
    public ResponseEntity<EmploymentHistoryEntity> findBySalary(@PathVariable double salary) {
        return ResponseEntity.ok(employmentHistoryService.findBySalary(salary));
    }

    @GetMapping("/time_of_employment/{time_of_employment}")
    public ResponseEntity<EmploymentHistoryEntity> findByTimeOfEmployment(@PathVariable int time_of_employment) {
        return ResponseEntity.ok(employmentHistoryService.findByTimeOfEmployment(time_of_employment));
    }

    @GetMapping("/client_id/{client_id}")
    public ResponseEntity<List<EmploymentHistoryEntity>> findByClientId(@PathVariable Long client_id) {
        return ResponseEntity.ok(employmentHistoryService.findByClientId(client_id));
    }
}
