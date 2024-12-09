package com.example.FollowRequests.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.FollowRequests.model.CreditApplicationEntity;

import java.util.List;
import java.util.Map;


@FeignClient(name = "ms-creditapplication", path= "/credit-application/application")
public interface CreditApplicatonFeignClient {


    @GetMapping("/client_id/{client_id}")
    ResponseEntity<List<CreditApplicationEntity>> findByClientId(@PathVariable Long client_id);

    @PostMapping("/create")
    ResponseEntity<CreditApplicationEntity> createCreditApplication(@RequestBody Map<String, String> requestBody);

    @GetMapping("/credit_application_id/{credit_application_id}")
    ResponseEntity<CreditApplicationEntity> findByCreditApplicationId(@PathVariable Long credit_application_id);

    @PutMapping("/update-status/{credit_application_id}")
    ResponseEntity<CreditApplicationEntity> updateStatus(
            @PathVariable Long credit_application_id,
            @RequestBody Map<String, String> requestBody);
}
