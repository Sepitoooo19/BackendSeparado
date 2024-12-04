package com.example.FollowRequests.services;

import com.example.FollowRequests.model.CreditApplicationEntity;
import com.example.FollowRequests.model.ClientEntity;
import com.example.FollowRequests.clients.ClientsFeignClient;
import com.example.FollowRequests.clients.CreditApplicatonFeignClient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class CreditApplicationService {

    @Autowired
    private CreditApplicatonFeignClient creditApplicatonFeignClient;

    @Autowired
    private ClientsFeignClient clientsFeignClient;



    public List<CreditApplicationEntity> findByClientId(Long client_id) {
        if (client_id == null) {
            return Collections.emptyList(); // Manejar caso de cliente no válido
        }

        return creditApplicatonFeignClient.findByClientId(client_id).getBody();
    }

    public CreditApplicationEntity findByCreditApplicationId(Long credit_application_id) {
        return creditApplicatonFeignClient.findByCreditApplicationId(credit_application_id).getBody();
    }







}
