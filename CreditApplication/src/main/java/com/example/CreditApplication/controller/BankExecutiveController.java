package com.example.CreditApplication.controllers;

import com.example.CreditApplication.entities.*;
import com.example.CreditApplication.services.BankExecutiveService;
import com.example.CreditApplication.services.ClientService;
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

    @GetMapping("/{rut}/amount")
    public ResponseEntity<Double> getExpectedAmountOfClientByRut(@PathVariable String rut) {
        double expectedAmount = bankExecutiveService.getExpectedAmountOfClientByRut(rut);
        return new ResponseEntity<>(expectedAmount, HttpStatus.OK);
    }

    @GetMapping("/{rut}/interest")
    public ResponseEntity<Double> getInteresRateOfClientByRut(@PathVariable String rut) {
        double interestRate = bankExecutiveService.getInteresRateOfClientByRut(rut);
        return new ResponseEntity<>(interestRate, HttpStatus.OK);
    }

    @GetMapping("/{rut}/time")
    public ResponseEntity<Integer> getTimeLimitOfClientByRut(@PathVariable String rut) {
        int timeLimit = bankExecutiveService.getTimeLimitOfClientByRut(rut);
        return new ResponseEntity<>(timeLimit, HttpStatus.OK);
    }



}
