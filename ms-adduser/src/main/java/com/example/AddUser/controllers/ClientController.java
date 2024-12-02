package com.example.AddUser.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.AddUser.entities.ClientEntity;
import com.example.AddUser.services.ClientService;
import java.util.List;





@RestController
@RequestMapping("/adduser")
public class ClientController {

    @Autowired
    private ClientService addUserService;

    @GetMapping
    public ResponseEntity<List<ClientEntity>> findAll() {
        return ResponseEntity.ok(addUserService.findAll());
    }

    @GetMapping("/{rut}")
    public ResponseEntity<ClientEntity> findByRut(@PathVariable String rut) {
        return ResponseEntity.ok(addUserService.findByRut(rut));
    }


    @GetMapping("/client_id/{client_id}")
    public ResponseEntity<ClientEntity> findByClientId(@PathVariable Long client_id) {
        return ResponseEntity.ok(addUserService.findByClientId(client_id));
    }

    //Nuevo cliente
    @PostMapping("/new")
    ClientEntity newClient(@RequestBody ClientEntity newClient) {
        return addUserService.save(newClient);


    }

    @PutMapping("/{client_id}")
    public ResponseEntity<ClientEntity> updateClient(@PathVariable Long client_id, @RequestBody ClientEntity client) {
        // Busca el cliente por ID, actualiza y retorna
        client.setClient_id(client_id);
        ClientEntity clientUpdated = addUserService.update(client);
        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClientById(@PathVariable Long clientId) {
        if (clientId == null || clientId <= 0) {
            return ResponseEntity.badRequest().body("Invalid client ID. It must be a positive number.");
        }

        addUserService.deleteById(clientId);
        return ResponseEntity.ok("Client deleted successfully.");
    }




}
