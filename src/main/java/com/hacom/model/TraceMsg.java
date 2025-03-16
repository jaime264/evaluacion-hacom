package com.hacom.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;

@Data
@Document(collection = "trace_msgs") 
public class TraceMsg {

    @Id
    private ObjectId _id;
    private String sessionId;
    private String payload;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime ts;
    
}
