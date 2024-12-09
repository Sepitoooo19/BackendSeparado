package com.example.FollowRequests.services;

import com.example.FollowRequests.clients.CreditApplicationFeignClient;
import com.example.FollowRequests.model.ClientEntity;
import com.example.FollowRequests.model.CreditApplicationEntity;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.FollowRequests.clients.ClientsFeignClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class BankExecutiveService {


    @Autowired
    private CreditApplicationFeignClient creditApplicationFeignClient;

    @Autowired
    private ClientsFeignClient clientsFeignClient;




    public List<CreditApplicationEntity> getCreditApplicationsByRut(String rut) {
        ClientEntity client = clientsFeignClient.findByRut(rut).getBody();
        return creditApplicationFeignClient.findByClientId(client.getClient_id()).getBody();
    }


    public CreditApplicationEntity getCreditApplicationById(Long credit_application_id) {
        Optional<CreditApplicationEntity> creditApplication = Optional.ofNullable(creditApplicationFeignClient.findByCreditApplicationId(credit_application_id).getBody());
        if (creditApplication.isPresent()) {
            return creditApplication.get();
        } else {
            throw new EntityNotFoundException("Solicitud de crédito no encontrada con el ID: " + credit_application_id);
        }
    }

    public void updateCreditApplicationStatus(Long creditApplicationId, String status) {
        // Validar parámetros
        if (creditApplicationId == null || creditApplicationId <= 0) {
            throw new IllegalArgumentException("Invalid credit application ID");
        }
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        // Crear el request body
        Map<String, String> requestBody = Map.of("status", status);

        // Llamar al microservicio de origen mediante Feign Client
        ResponseEntity<Void> response = creditApplicationFeignClient.updateStatus(creditApplicationId, requestBody);

        // Verificar el resultado
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al actualizar el estado de la solicitud de crédito");
        }
    }





}
