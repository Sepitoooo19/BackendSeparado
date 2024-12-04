package com.example.CreditSimulation.controllers;

import com.example.CreditSimulation.services.BankExecutiveService;
import com.example.CreditSimulation.Model.ClientEntity;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/executives")
public class BankExecutiveController {

    @Autowired
    private BankExecutiveService bankExecutiveService;

    @GetMapping("/client/{rut}")
    public ResponseEntity<ClientEntity> getClientByRut(@PathVariable String rut) {
        try {
            return ResponseEntity.ok(bankExecutiveService.getClientByRut(rut));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{rut}/expected-amount")
    public ResponseEntity<Double> getExpectedAmountOfClientByRut(@PathVariable String rut) {
        try {
            return ResponseEntity.ok(bankExecutiveService.getExpectedAmountOfClientByRut(rut));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{rut}/interest-rate")
    public ResponseEntity<Double> getInteresRateOfClientByRut(@PathVariable String rut) {
        try {
            return ResponseEntity.ok(bankExecutiveService.getInteresRateOfClientByRut(rut));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{rut}/time-limit")
    public ResponseEntity<Integer> getTimeLimitOfClientByRut(@PathVariable String rut) {
        try {
            return ResponseEntity.ok(bankExecutiveService.getTimeLimitOfClientByRut(rut));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/client/{rut}/monthly-salary")
    public ResponseEntity<Double> getMonthlySalaryOfClientByRut(@PathVariable String rut) {
        try {
            return ResponseEntity.ok(bankExecutiveService.getMonthlySalaryOfClientByRut(rut));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }




}
