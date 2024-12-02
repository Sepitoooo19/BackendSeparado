package com.example.CreditSimulation.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.example.CreditSimulation.clients.ClientsFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BankExecutiveService {

    @Autowired
    private ClientsFeignClient clientsFeignClient;

    public double getExpectedAmountOfClientByRut(String rut) {
        return clientsFeignClient.getExpectedAmountOfClientByRut(rut).getBody();
    }

    public double getInteresRateOfClientByRut(String rut) {
        return clientsFeignClient.getInteresRateOfClientByRut(rut).getBody();
    }

    public int getTimeLimitOfClientByRut(String rut) {
        return clientsFeignClient.getTimeLimitOfClientByRut(rut).getBody();
    }

    public Object getMonthlyLoanOfClientByRut(String rut) {
        return clientsFeignClient.getMonthlyLoanOfClientByRut(rut).getBody();
    }






}
