package com.example.demo.authentication.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationRequest {
    private String username;
    private String password;
}
