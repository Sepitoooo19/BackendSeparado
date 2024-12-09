package com.example.FollowRequests.controllers;

import com.example.FollowRequests.model.CreditApplicationEntity;
import com.example.FollowRequests.services.BankExecutiveService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.FollowRequests.model.ClientEntity;
import com.example.FollowRequests.clients.ClientsFeignClient;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/executives/follow-requests")
public class BankExecutiveController {

    @Autowired
    private BankExecutiveService bankExecutiveService;

    @Autowired
    private ClientsFeignClient clientsFeignClient;


    @GetMapping("/{rut}")
    public ResponseEntity<ClientEntity> getClientByRut(@PathVariable String rut) {
        ClientEntity client = clientsFeignClient.findByRut(rut).getBody();
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @GetMapping("/{rut}/credit-application")
    public ResponseEntity<List<CreditApplicationEntity>> getCreditApplicationByRut(@PathVariable String rut) {
        try {
            List<CreditApplicationEntity> creditApplications = bankExecutiveService.getCreditApplicationsByRut(rut);
            return new ResponseEntity<>(creditApplications, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/credit-application/{id}")
    public ResponseEntity<CreditApplicationEntity> getCreditApplicationById(@PathVariable("id") Long creditApplicationId) {
        try {
            CreditApplicationEntity creditApplication = bankExecutiveService.getCreditApplicationById(creditApplicationId);
            return ResponseEntity.ok(creditApplication);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/credit-application/update-status/{credit_application_id}")
    public ResponseEntity<CreditApplicationEntity> updateStatus(
            @PathVariable Long credit_application_id,
            @RequestBody Map<String, String> requestBody) {
        try {
            String status = requestBody.get("status");
            if (status == null || status.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            CreditApplicationEntity updatedApplication = bankExecutiveService.updateStatusOfCreditApplication(credit_application_id, status);
            return ResponseEntity.ok(updatedApplication);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }




}

