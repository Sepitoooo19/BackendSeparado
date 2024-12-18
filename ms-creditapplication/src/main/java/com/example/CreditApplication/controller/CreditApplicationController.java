package com.example.CreditApplication.controller;
import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CreditApplication.entities.CreditApplicationEntity;
import com.example.CreditApplication.services.CreditApplicationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/credit-application/application")
public class CreditApplicationController {

    @Autowired
    private CreditApplicationService creditApplicationService;

    @GetMapping("/client_id/{client_id}")
    public ResponseEntity<List<CreditApplicationEntity>> findByClientId(@PathVariable Long client_id) {
        return ResponseEntity.ok(creditApplicationService.findByClientId(client_id));
    }

    @GetMapping("/credit_application_id/{credit_application_id}")
    public ResponseEntity<CreditApplicationEntity> findByCreditApplicationId(@PathVariable Long credit_application_id) {
        return ResponseEntity.ok(creditApplicationService.findByCreditApplicationId(credit_application_id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CreditApplicationEntity> findByName(@PathVariable String name) {
        return ResponseEntity.ok(creditApplicationService.findByName(name));
    }

    @GetMapping("/credit_date/{credit_date}")
    public ResponseEntity<CreditApplicationEntity> findByCreditDate(@PathVariable String credit_date) {
        return ResponseEntity.ok(creditApplicationService.findByCreditDate(credit_date));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<CreditApplicationEntity> findByStatus(@PathVariable String status) {
        return ResponseEntity.ok(creditApplicationService.findByStatus(status));
    }

    @GetMapping("/exists/client_id/{client_id}")
    public ResponseEntity<Boolean> existsByClientId(@PathVariable Long client_id) {
        return ResponseEntity.ok(creditApplicationService.existsByClientId(client_id));
    }

    @PostMapping("/create")
    public ResponseEntity<CreditApplicationEntity> createCreditApplication(
            @RequestBody Map<String, String> requestBody) {

        String rut = requestBody.get("rut");
        String loan_type = requestBody.get("loan_type");

        System.out.println("Cuerpo de la solicitud: " + requestBody);
        System.out.println("Tipo de préstamo recibido: " + loan_type);

        // Llamada al servicio para crear la solicitud de crédito
        try {
            CreditApplicationEntity createdApplication = creditApplicationService.createCreditApplicationByRut(rut, loan_type);
            // Retorna la solicitud de crédito creada con el estado HTTP 201 (Created)
            return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            // Manejar el caso donde no se encuentra el cliente con el rut dado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Manejar otros errores
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-status/{credit_application_id}")
    public ResponseEntity<CreditApplicationEntity> updateStatus(
            @PathVariable Long credit_application_id,
            @RequestBody Map<String, String> requestBody) {
        try {
            // Verificar que el cuerpo de la solicitud contenga el campo "status"
            String status = requestBody.get("status");
            if (status == null || status.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            // Llamar al servicio para actualizar el estado
            CreditApplicationEntity updatedApplication = creditApplicationService.updateStatus(credit_application_id, status);
            return ResponseEntity.ok(updatedApplication);
        } catch (EntityNotFoundException e) {
            // Manejar error de entidad no encontrada
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Opcional: Devuelve un cuerpo vacío o un mensaje JSON con detalles del error
        } catch (IllegalArgumentException e) {
            // Manejar errores de parámetros inválidos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Opcional: Puedes incluir detalles del error en el cuerpo
        } catch (Exception e) {
            // Manejar cualquier otra excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Opcional: Loggear la excepción para debugging
        }
    }

}
