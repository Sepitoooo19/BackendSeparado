package com.example.CreditApplication.clients;

import com.example.CreditApplication.Model.ClientEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-user", path= "/user")
public interface ClientsFeignClient {

    @GetMapping("/{rut}")
    ResponseEntity<ClientEntity> findByRut(@PathVariable String rut);


}
