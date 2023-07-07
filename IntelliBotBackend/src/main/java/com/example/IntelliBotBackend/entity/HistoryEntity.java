package com.example.IntelliBotBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "history")
public class HistoryEntity {

    @Id
    private ObjectId historyId;

    private ObjectId userId;

    private ObjectId promptId;

    private ObjectId promptResponseId;

    private Date addedDate;


}
