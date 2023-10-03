package com.example.demo.authentication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;

    public UserInfo(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
