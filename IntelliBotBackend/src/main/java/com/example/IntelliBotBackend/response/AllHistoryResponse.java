package com.example.IntelliBotBackend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllHistoryResponse {

    private ObjectId userId;
    private int historyTotalCount;
    private List<HistoryResponse> historyResponse;

}
