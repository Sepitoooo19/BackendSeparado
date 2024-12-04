package com.example.AddUser.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.AddUser.entities.ClientEntity;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    //obtener todos los clientes
    @Query("SELECT c FROM ClientEntity c")
    public List<ClientEntity> findAll();

    public ClientEntity findByRut(String rut);

    @Query("SELECT c FROM ClientEntity c WHERE c.client_id = :client_id")
    ClientEntity findByClientId(@Param("client_id") Long client_id);


    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM ClientEntity c WHERE c.rut = :rut")
    boolean existsByRut(String rut);
}

