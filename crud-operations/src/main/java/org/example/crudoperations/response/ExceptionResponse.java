package org.example.crudoperations.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ExceptionResponse {
    private int status;
    private String message;
    private long timeStamp;

}
