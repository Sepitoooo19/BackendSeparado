package com.example.TotalCost.controllers;

import com.example.TotalCost.model.ClientEntity;

import com.example.TotalCost.services.BankExecutiveService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.TotalCost.clients.ClientsFeignClient;

import java.util.List;

@RestController
@RequestMapping("/executives/total-cost")
@CrossOrigin("*")
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

    @GetMapping("/{rut}/monthly-loan")
    public ResponseEntity<?> getMonthlyLoanOfClientByRut(@PathVariable String rut) {
        try {
            double monthlyLoan = bankExecutiveService.getMonthlyLoanOfClientByRut(rut);
            return ResponseEntity.ok(monthlyLoan);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Client with RUT " + rut + " not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while calculating the monthly loan.");
        }
    }



    @GetMapping("/insurance/{rut}")
    public ResponseEntity<Integer> calculateInsurance(@PathVariable String rut) {
        int insurance = bankExecutiveService.insuranceCalculationByRut(rut);
        return ResponseEntity.ok(insurance);
    }

    @GetMapping("/total-cost/{rut}")
    public ResponseEntity<String> calculateTotalCost(@PathVariable String rut) {

        // Obtener el costo total del préstamo
        int totalCost = bankExecutiveService.totalCostOfLoanByRut(rut);

        // Crear el mensaje que incluye el total y una explicación
        String message = String.format("El costo total del préstamo, considerando todas las cuotas, seguros y la comisión inicial, es de $%,d.", totalCost);

        // Devolver el mensaje como respuesta
        return ResponseEntity.ok(message);
    }

    @GetMapping("/administration-commission/{rut}")
    public ResponseEntity<Integer> calculateAdministrationCommission(@PathVariable String rut) {
        int commission = bankExecutiveService.administrationCommissionByRut(rut);
        return ResponseEntity.ok(commission);
    }

    @GetMapping("/monthly-cost/{rut}")
    public ResponseEntity<Integer> calculateMonthlyCost(@PathVariable String rut) {
        int monthlyCost = bankExecutiveService.monthlyCostByRut(rut);
        return ResponseEntity.ok(monthlyCost);
    }




}

