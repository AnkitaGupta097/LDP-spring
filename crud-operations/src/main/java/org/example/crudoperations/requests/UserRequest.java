package org.example.crudoperations.requests;

import lombok.Data;

@Data
public class UserRequest {
    private String userId;
    private String email;
    private String password;
}
