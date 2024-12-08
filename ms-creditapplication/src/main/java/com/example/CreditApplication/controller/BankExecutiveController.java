package com.example.CreditApplication.controller;

import com.example.CreditApplication.Model.ClientEntity;
import com.example.CreditApplication.services.BankExecutiveService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/executives/application")
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

    @GetMapping("/client/{rut}/monthly-loan")
    public ResponseEntity<Integer> getMonthlyLoanOfClientByRut(@PathVariable String rut) {
        try {
            return ResponseEntity.ok(bankExecutiveService.getMonthlyLoanOfClientByRut(rut));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
