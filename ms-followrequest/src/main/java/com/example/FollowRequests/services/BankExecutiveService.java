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
import java.util.Map;
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
        // Primero, obtenemos la solicitud de crédito por ID
        CreditApplicationEntity creditApplication = getCreditApplicationById(credit_application_id);

        // Creamos un mapa con el estado actualizado
        Map<String, String> requestBody = Map.of("status", status);

        // Llamamos al Feign Client para actualizar el estado
        ResponseEntity<CreditApplicationEntity> response = creditApplicatonFeignClient.updateStatus(credit_application_id, requestBody);

        // Verificamos la respuesta
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            // Manejo de errores si es necesario
            throw new RuntimeException("Error al actualizar el estado de la solicitud de crédito");
        }
    }





}
