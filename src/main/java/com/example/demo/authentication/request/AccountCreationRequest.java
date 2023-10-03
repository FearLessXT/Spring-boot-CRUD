package com.example.demo.authentication.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountCreationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
}
