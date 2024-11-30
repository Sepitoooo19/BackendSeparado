package com.example.TotalCost.services;

import com.example.TotalCost.entities.*;
import com.example.TotalCost.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class BankExecutiveService {
    @Autowired
    private ClientRepository clientRepository;



    int fireInsurance = 20000;



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




    public int insuranceCalculationByRut(String rut) {

        ClientEntity client = clientRepository.findByRut(rut);
        double expected_amount = client.getExpected_amount();
        double credit_life_insurance = 0.0003;

        // Redondear el resultado de la multiplicación
        int insurance = (int) Math.round(expected_amount * credit_life_insurance);
        return insurance;
    }

    public int administrationCommissionByRut(String rut){

        ClientEntity client = clientRepository.findByRut(rut);
        double expected_amount = client.getExpected_amount();
        double administration_commission = 0.01;

        int commission = (int) (expected_amount * administration_commission);
        return commission;

    }

    public int monthlyCostByRut(String rut){

        ClientEntity client = clientRepository.findByRut(rut);
        int monthly_fee = getMonthlyLoanOfClientByRut(rut);
        int insurance = insuranceCalculationByRut(rut);
        int monthlyCostofClient = monthly_fee + insurance + fireInsurance;

        return monthlyCostofClient;

    }

    public int totalCostOfLoanByRut(String rut) {

        ClientEntity client = clientRepository.findByRut(rut);
        int monthlyCostofClient = monthlyCostByRut(rut);
        int commission = administrationCommissionByRut(rut);
        int totalCost = (monthlyCostofClient * (client.getTime_limit() * 12)) + commission;

        return totalCost;
    }





}
