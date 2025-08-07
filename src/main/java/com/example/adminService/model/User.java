package com.example.adminService.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"") // Escaped since "user" is a reserved word in PostgreSQL
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
/*
 * This class represents a user entity in the system.
 * It includes fields for id, name, email, and password.
 * * The id is auto-generated and serves as the primary key.
 * * The name and email fields are required, with the email being unique.
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}

