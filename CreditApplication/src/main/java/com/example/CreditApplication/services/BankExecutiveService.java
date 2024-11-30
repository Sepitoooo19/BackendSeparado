package com.example.CreditApplication.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.example.CreditApplication.repositories.ClientRepository;
import com.example.CreditApplication.entities.ClientEntity;

import java.util.List;


import java.util.Arrays;
import java.time.LocalDate;
import java.util.Optional;


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

    public double getMonthlySalaryOfClientByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getMonthly_salary();
    }

    public String  getLoanTypeClientByRut(String rut){
        ClientEntity client = clientRepository.findByRut(rut);
        return client.getType_loan();
    }


}
