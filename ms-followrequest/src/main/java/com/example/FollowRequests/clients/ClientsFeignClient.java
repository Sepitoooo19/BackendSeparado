package com.example.FollowRequests.clients;

import com.example.FollowRequests.model.ClientEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "ms-adduser", path= "/adduser")
public interface ClientsFeignClient {

    @GetMapping("/{rut}")
    ResponseEntity<ClientEntity> findByRut(@PathVariable String rut);


}
