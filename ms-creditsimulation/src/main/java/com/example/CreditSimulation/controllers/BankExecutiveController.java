package com.example.CreditSimulation.controllers;

import com.example.CreditSimulation.services.BankExecutiveService;

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


    @GetMapping("/{rut}/amount")
    public ResponseEntity<Double> getExpectedAmountOfClientByRut(@PathVariable String rut) {
        return ResponseEntity.ok(bankExecutiveService.getExpectedAmountOfClientByRut(rut));
    }

    @GetMapping("/{rut}/interest")
    public ResponseEntity<Double> getInteresRateOfClientByRut(@PathVariable String rut) {
        return ResponseEntity.ok(bankExecutiveService.getInteresRateOfClientByRut(rut));
    }

    @GetMapping("/{rut}/time")
    public ResponseEntity<Integer> getTimeLimitOfClientByRut(@PathVariable String rut) {
        return ResponseEntity.ok(bankExecutiveService.getTimeLimitOfClientByRut(rut));
    }

    @GetMapping("/{rut}/monthly-loan")
    public ResponseEntity<?> getMonthlyLoanOfClientByRut(@PathVariable String rut) {
        return ResponseEntity.ok(bankExecutiveService.getMonthlyLoanOfClientByRut(rut));
    }

}
