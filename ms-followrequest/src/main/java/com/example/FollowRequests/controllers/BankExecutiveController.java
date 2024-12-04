package com.example.FollowRequests.controllers;

import com.example.FollowRequests.model.CreditApplicationEntity;
import com.example.FollowRequests.services.BankExecutiveService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.FollowRequests.model.ClientEntity;
import com.example.FollowRequests.clients.ClientsFeignClient;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/executives/follow-requests")
@CrossOrigin("*")
public class BankExecutiveController {

    @Autowired
    private BankExecutiveService bankExecutiveService;

    @Autowired
    private ClientsFeignClient clientsFeignClient;




    @GetMapping("/{rut}")
    public ResponseEntity<ClientEntity> getClientByRut(@PathVariable String rut) {
        ClientEntity client = clientsFeignClient.findByRut(rut).getBody();
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }


    @GetMapping("/{rut}/credit-application")
    public ResponseEntity<List<CreditApplicationEntity>> getCreditApplicationByRut(@PathVariable String rut) {

        List<CreditApplicationEntity> creditApplication = bankExecutiveService.getCreditApplicationsByRut(rut);
        return new ResponseEntity<>(creditApplication, HttpStatus.OK);

    }



    @GetMapping("/credit-application/{id}")
    public ResponseEntity<CreditApplicationEntity> getCreditApplicationById(@PathVariable("id") Long creditApplicationId) {
        try {
            CreditApplicationEntity creditApplication = bankExecutiveService.getCreditApplicationById(creditApplicationId);
            return ResponseEntity.ok(creditApplication);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update-status/{credit_application_id}")
    public ResponseEntity<CreditApplicationEntity> updateStatus(
            @PathVariable Long credit_application_id,
            @RequestParam String status) {

        try {
            // Llamamos al servicio para actualizar el estado de la solicitud
            CreditApplicationEntity updatedApplication = bankExecutiveService.updateStatusOfCreditApplication(credit_application_id, status);

            // Si todo va bien, devolvemos la respuesta con el objeto actualizado
            return ResponseEntity.ok(updatedApplication);
        } catch (RuntimeException e) {
            // Si ocurre un error, devolvemos un Internal Server Error
            return ResponseEntity.status(500).body(null);
        } catch (Exception e) {
            // Manejo de excepciones generales, por si hay algún otro tipo de error
            return ResponseEntity.status(400).body(null);
        }
    }





}

