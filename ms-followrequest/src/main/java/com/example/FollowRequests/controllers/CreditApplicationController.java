package com.example.FollowRequests.controllers;

import com.example.FollowRequests.model.CreditApplicationEntity;
import com.example.FollowRequests.services.CreditApplicationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/credit_application")
@CrossOrigin("*")
public class CreditApplicationController {

    @Autowired
    private CreditApplicationService creditApplicationService;

    @GetMapping("/client_id/{client_id}")
    public ResponseEntity<List<CreditApplicationEntity>> findByClientId(@PathVariable Long client_id) {
        return ResponseEntity.ok(creditApplicationService.findByClientId(client_id));
    }

    @GetMapping("/credit_application_id/{credit_application_id}")
    public ResponseEntity<CreditApplicationEntity> findByCreditApplicationId(@PathVariable Long credit_application_id) {
        return ResponseEntity.ok(creditApplicationService.findByCreditApplicationId(credit_application_id));
    }




}
