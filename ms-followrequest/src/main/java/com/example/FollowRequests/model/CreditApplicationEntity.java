package com.example.FollowRequests.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationEntity {

    private Long credit_application_id;
    private Long client_id;
    private String name;
    private String credit_date;
    private String status;
    private int amount;

}
