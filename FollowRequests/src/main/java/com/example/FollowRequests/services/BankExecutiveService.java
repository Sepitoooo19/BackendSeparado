package com.example.FollowRequests.services;

import com.example.FollowRequests.entities.*;
import com.example.FollowRequests.repositories.*;
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

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;




    public List<CreditApplicationEntity> getCreditApplicationsByRut(String rut) {
        ClientEntity client = clientRepository.findByRut(rut);
        return creditApplicationRepository.findByClientId(client.getClient_id());
    }


    public CreditApplicationEntity getCreditApplicationById(Long credit_application_id) {
        Optional<CreditApplicationEntity> creditApplication = creditApplicationRepository.findById(credit_application_id);
        if (creditApplication.isPresent()) {
            return creditApplication.get();
        } else {
            throw new EntityNotFoundException("Solicitud de crédito no encontrada con el ID: " + credit_application_id);
        }
    }

    public CreditApplicationEntity updateStatusOfCreditApplication(Long credit_application_id, String status) {
        // Validaciones
        if (credit_application_id == null || credit_application_id <= 0) {
            throw new IllegalArgumentException("Invalid credit application ID");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        // Obtener la solicitud de crédito
        CreditApplicationEntity creditApplication = getCreditApplicationById(credit_application_id);

        // Actualizar el estado
        creditApplication.setStatus(status);
        return creditApplicationRepository.save(creditApplication);
    }




}
