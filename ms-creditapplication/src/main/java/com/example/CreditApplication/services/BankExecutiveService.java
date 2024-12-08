package com.example.CreditApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.CreditApplication.clients.ClientsFeignClient;

import org.springframework.stereotype.Service;
import com.example.CreditApplication.Model.ClientEntity;



@Service
public class BankExecutiveService {

    @Autowired
    private ClientsFeignClient clientsFeignClient;


    public ClientEntity getClientByRut(String rut) {
        return clientsFeignClient.findByRut(rut).getBody();
    }


    public double getExpectedAmountOfClientByRut(String rut) {
        ClientEntity client = clientsFeignClient.findByRut(rut).getBody();
        return client.getExpected_amount();
    }

    public double getInteresRateOfClientByRut(String rut) {
        ClientEntity client = clientsFeignClient.findByRut(rut).getBody();
        return client.getInterest_rate();
    }

    public int getTimeLimitOfClientByRut(String rut) {
        ClientEntity client = clientsFeignClient.findByRut(rut).getBody();
        return client.getTime_limit();

    }

    public int getMonthlyLoanOfClientByRut(String rut) {
        ClientEntity client = clientsFeignClient.findByRut(rut).getBody();
        double interest_rate = client.getInterest_rate() / 12 / 100;
        double expected_amount = client.getExpected_amount();
        int time_limit_in_months = client.getTime_limit() * 12;
        double monthly_fee = expected_amount * ((interest_rate*(Math.pow(1+interest_rate, time_limit_in_months)))/(Math.pow(1+interest_rate, time_limit_in_months)-1));
        return (int) monthly_fee;

    }




}
