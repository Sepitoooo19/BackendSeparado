package com.example.FollowRequests.services;

import com.example.FollowRequests.clients.CreditApplicationFeignClient;
import com.example.FollowRequests.model.CreditApplicationEntity;
import com.example.FollowRequests.model.ClientEntity;
import com.example.FollowRequests.clients.ClientsFeignClient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class CreditApplicationService {

    @Autowired
    private CreditApplicationFeignClient creditApplicationFeignClient;

    @Autowired
    private ClientsFeignClient clientsFeignClient;



    public List<CreditApplicationEntity> findByClientId(Long client_id) {
        if (client_id == null) {
            return Collections.emptyList(); // Manejar caso de cliente no válido
        }

        return creditApplicationFeignClient.findByClientId(client_id).getBody();
    }

    public CreditApplicationEntity findByCreditApplicationId(Long credit_application_id) {
        return creditApplicationFeignClient.findByCreditApplicationId(credit_application_id).getBody();
    }







}
