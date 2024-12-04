package com.example.CreditApplication.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    private Long client_id;
    private String name;
    private String rut;
    private String email;
    private String phone;
    private int age;
    private double monthly_salary;
    private Double personal_savings;
    private String job_type;
    private double expected_amount;
    private int time_limit;
    private double interest_rate;
    private String type_loan;
    private boolean independent_activity;
    private int job_seniority;
    private String actual_job;

}
