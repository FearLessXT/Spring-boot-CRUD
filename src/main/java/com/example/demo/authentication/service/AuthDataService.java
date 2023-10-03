package com.example.demo.authentication.service;

import com.example.demo.authentication.entity.UserInfo;

import java.security.NoSuchAlgorithmException;

public interface AuthDataService {
    UserInfo findByUsername(String username);
    UserInfo findByEmail(String email);
    void deleteByUsernamePassword(String username, String password) throws NoSuchAlgorithmException;

    void createUserProfile(UserInfo userInfo) throws NoSuchAlgorithmException;
}
