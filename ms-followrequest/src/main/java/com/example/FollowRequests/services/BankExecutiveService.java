package com.example.FollowRequests.services;

import com.example.FollowRequests.model.ClientEntity;
import com.example.FollowRequests.model.CreditApplicationEntity;
import com.example.FollowRequests.clients.CreditApplicatonFeignClient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.FollowRequests.clients.ClientsFeignClient;

import java.util.List;
import java.util.Optional;


@Service
public class BankExecutiveService {


    @Autowired
    private CreditApplicatonFeignClient creditApplicatonFeignClient;

    @Autowired
    private ClientsFeignClient clientsFeignClient;




    public List<CreditApplicationEntity> getCreditApplicationsByRut(String rut) {
        ClientEntity client = clientsFeignClient.findByRut(rut).getBody();
        return creditApplicatonFeignClient.findByClientId(client.getClient_id()).getBody();
    }


    public CreditApplicationEntity getCreditApplicationById(Long credit_application_id) {
        Optional<CreditApplicationEntity> creditApplication = Optional.ofNullable(creditApplicatonFeignClient.findByCreditApplicationId(credit_application_id).getBody());
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

        // Obtener la solicitud de crédito utilizando Feign
        CreditApplicationEntity creditApplication = getCreditApplicationById(credit_application_id);

        if (creditApplication == null) {
            throw new EntityNotFoundException("Credit application not found with ID: " + credit_application_id);
        }

        // Actualizar el estado localmente
        creditApplication.setStatus(status);

        // Enviar la solicitud actualizada al microservicio responsable
        ResponseEntity<CreditApplicationEntity> response = creditApplicatonFeignClient.updateStatus(credit_application_id, status);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); // Retornar la solicitud actualizada desde el microservicio
        } else {
            throw new RuntimeException("Failed to update credit application status. HTTP Status: " + response.getStatusCode());
        }
    }





}
