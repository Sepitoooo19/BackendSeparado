package com.example.CreditApplication.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CreditApplication.clients.ClientsFeignClient;
import com.example.CreditApplication.repositories.CreditApplicationRepository;
import com.example.CreditApplication.entities.CreditApplicationEntity;
import com.example.CreditApplication.Model.ClientEntity;

import java.time.LocalDate;

import java.util.Collections;
import java.util.List;

@Service
public class CreditApplicationService {

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    @Autowired
    private ClientsFeignClient clientsFeignClient;


    public List<CreditApplicationEntity> findAll() {
        return creditApplicationRepository.findAll();
    }

    public List<CreditApplicationEntity> findByClientId(Long client_id) {
        if (client_id == null) {
            return Collections.emptyList(); // Manejar caso de cliente no válido
        }
        return creditApplicationRepository.findByClientId(client_id);
    }

    public CreditApplicationEntity findByCreditApplicationId(Long credit_application_id) {
        return creditApplicationRepository.findByCreditApplicationId(credit_application_id);
    }

    public CreditApplicationEntity findByName(String name) {
        return creditApplicationRepository.findByName(name);
    }

    public CreditApplicationEntity findByCreditDate(String credit_date) {
        return creditApplicationRepository.findByCreditDate(credit_date);
    }


    public CreditApplicationEntity findByStatus(String status) {
        return creditApplicationRepository.findByStatus(status);
    }

    public boolean existsByClientId(Long client_id) {
        return creditApplicationRepository.existsByClientId(client_id);
    }

    public CreditApplicationEntity createCreditApplicationByRut(String rut, String loan_type) {
        // Buscar cliente por rut
        ClientEntity client = clientsFeignClient.findByRut(rut).getBody();

        if (client == null) {
            throw new EntityNotFoundException("Client not found for RUT: " + rut);
        }

        // Obtener client_id del cliente encontrado
        Long client_id = client.getClient_id();

        double interest_rate = client.getInterest_rate() / 12 / 100;
        double expected_amount = client.getExpected_amount();
        int time_limit_in_months = client.getTime_limit() * 12;
        double monthly_fee = expected_amount * ((interest_rate*(Math.pow(1+interest_rate, time_limit_in_months)))/(Math.pow(1+interest_rate, time_limit_in_months)-1));
        int monthly_fee_int = (int) monthly_fee;
        // Crear nueva solicitud de crédito
        CreditApplicationEntity creditApplication = new CreditApplicationEntity();
        creditApplication.setClient_id(client_id);
        creditApplication.setName(loan_type);
        creditApplication.setCredit_date(LocalDate.now().toString());
        creditApplication.setAmount(monthly_fee_int);
        creditApplication.setStatus("PENDING");

        // Guardar la entidad en la base de datos y retornar
        return creditApplicationRepository.save(creditApplication);
    }

    public CreditApplicationEntity updateStatus(Long creditApplicationId, String status) {
        // Validar parámetros
        if (creditApplicationId == null || creditApplicationId <= 0) {
            throw new IllegalArgumentException("Invalid credit application ID");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        // Buscar la solicitud de crédito
        CreditApplicationEntity creditApplication = creditApplicationRepository.findById(creditApplicationId)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud de crédito no encontrada con el ID: " + creditApplicationId));

        // Actualizar el estado
        creditApplication.setStatus(status);

        // Guardar los cambios
        return creditApplicationRepository.save(creditApplication);
    }





}
