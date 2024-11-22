package com.example.CreditSimulation.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.example.CreditSimulation.repositories.ClientRepository;
import com.example.CreditSimulation.entities.ClientEntity;

import java.util.List;


@Service
public class BankExecutiveService {

    @Autowired
    private ClientRepository clientRepository;

    public double getExpectedAmountOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getExpected_amount();

    }

    public double getInteresRateOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getInterest_rate();
    }

    public int getTimeLimitOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getTime_limit();
    }

    public int getMonthlyLoanOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        double interest_rate = client.getInterest_rate() / 12 / 100;
        double expected_amount = client.getExpected_amount();
        int time_limit_in_months = client.getTime_limit() * 12;
        double monthly_fee = expected_amount * ((interest_rate*(Math.pow(1+interest_rate, time_limit_in_months)))/(Math.pow(1+interest_rate, time_limit_in_months)-1));
        return (int) monthly_fee;

    }



}
