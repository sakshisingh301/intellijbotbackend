package com.example.IntelliBotBackend.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_details")
public class RegisteredUser {
    @Id
    private ObjectId id;

    private String email;


    private String password;


    private Boolean active;


    private String designation;
}
