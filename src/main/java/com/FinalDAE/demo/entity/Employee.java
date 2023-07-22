package com.FinalDAE.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    @Column(unique = true, nullable = false) // Se requiere que no se repita
    private int employee_code;
    private String image_url;
    private String job_title;
    private String name;
    private String phone;
}
