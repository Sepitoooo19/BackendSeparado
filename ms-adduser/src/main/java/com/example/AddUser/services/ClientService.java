package com.example.AddUser.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.AddUser.repositories.ClientRepository;
    import com.example.AddUser.entities.ClientEntity;


import java.util.List;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    public ClientEntity findByRut(String rut) {
        if(rut == null){
            return null;
        }
        return clientRepository.findByRut(rut.trim().toLowerCase());
    }

    public ClientEntity findByClientId(Long client_id) {
        return clientRepository.findByClientId(client_id);
    }

    public ClientEntity save(ClientEntity client) {
        clientRepository.save(client);
        return client;
    }

    public void deleteById(Long client_id) {
        if (client_id == null || client_id <= 0) {
            // No hacer nada si el ID es null, 0 o negativo
            return;
        }
        clientRepository.deleteById(client_id);
    }

    public void deleteAll() {
        clientRepository.deleteAll();
    }

    public boolean existsById(Long client_id) {
        return clientRepository.existsById(client_id);
    }

    public boolean existsByRut(String rut) {
        return clientRepository.existsByRut(rut);
    }

    public ClientEntity update(ClientEntity client) {
        if (client == null || client.getClient_id() == null || client.getName().isEmpty()) {
            return null;
        }
        return clientRepository.save(client);
    }





}
