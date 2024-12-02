package com.example.CreditSimulation.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "ms-adduser", path= "/adduser")
public interface ClientsFeignClient {

    @GetMapping("/{rut}/amount")
    ResponseEntity<Double> getExpectedAmountOfClientByRut(@PathVariable String rut);

    @GetMapping("/{rut}/interest")
    ResponseEntity<Double> getInteresRateOfClientByRut(@PathVariable String rut);

    @GetMapping("/{rut}/time")
    ResponseEntity<Integer> getTimeLimitOfClientByRut(@PathVariable String rut);

    @GetMapping("/{rut}/monthly-loan")
    ResponseEntity<?> getMonthlyLoanOfClientByRut(@PathVariable String rut);


}
