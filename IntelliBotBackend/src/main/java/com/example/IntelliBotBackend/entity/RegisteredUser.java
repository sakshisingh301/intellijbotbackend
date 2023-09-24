//package com.example.IntelliBotBackend.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Document(collection = "user_details")
//public class RegisteredUser {
//    @Id
//    private ObjectId id;
//    private String email;
//    @JsonIgnore
//    private String password;
//    private Boolean active;
//    private String designation;
//}
