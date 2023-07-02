package com.example.IntelliBotBackend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_details")
public class RegisteredUser {
    @Id
    @Generated
    private int id;

    private String email;


    private String password;


    private boolean active;


    private String designation;
}
