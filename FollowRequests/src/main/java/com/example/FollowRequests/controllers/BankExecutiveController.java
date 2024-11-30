package com.example.FollowRequests.controllers;

import com.example.FollowRequests.entities.*;
import com.example.FollowRequests.services.BankExecutiveService;
import com.example.FollowRequests.services.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/executives")
@CrossOrigin("*")
public class BankExecutiveController {

    @Autowired
    private BankExecutiveService bankExecutiveService;

    @Autowired
    private ClientService clientService;



    @GetMapping
    public List<ClientEntity> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<ClientEntity> getClientByRut(@PathVariable String rut) {
        ClientEntity client = clientService.findByRut(rut);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @GetMapping("/{rut}/credit-application")
    public ResponseEntity<List<CreditApplicationEntity>> getCreditApplicationByRut(@PathVariable String rut) {

        List<CreditApplicationEntity> creditApplication = bankExecutiveService.getCreditApplicationsByRut(rut);
        return new ResponseEntity<>(creditApplication, HttpStatus.OK);

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

    @PutMapping("/{credit_application_id}/status")
    public ResponseEntity<CreditApplicationEntity> updateCreditApplicationStatus(
            @PathVariable Long credit_application_id,
            @RequestBody Map<String, String> requestBody) {
        // Verificar que el cuerpo de la solicitud contenga el campo "status"
        String status = requestBody.get("status");
        if (status == null || status.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Llamar al servicio para actualizar el estado
        try {
            CreditApplicationEntity updatedCreditApplication = bankExecutiveService.updateStatusOfCreditApplication(credit_application_id, status);
            return ResponseEntity.ok(updatedCreditApplication);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



}

