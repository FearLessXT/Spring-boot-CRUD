package com.example.demo.authentication.controller;

import com.example.demo.authentication.entity.UserInfo;
import com.example.demo.authentication.request.AccountCreationRequest;
import com.example.demo.authentication.request.AuthenticationRequest;
import com.example.demo.authentication.response.AccountCreationResponse;
import com.example.demo.authentication.response.AuthenticationResponse;
import com.example.demo.authentication.service.AuthDataService;
import com.example.demo.authentication.service.CustomUserDetailsService;
import com.example.demo.authentication.util.JwtUtil;
import com.example.demo.authentication.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthDataService authDataService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> createAccount(@RequestBody AccountCreationRequest accountCreationRequest) throws NoSuchAlgorithmException {
        if (authDataService.findByUsername(accountCreationRequest.getUsername()) != null) {
            return ResponseEntity.ok(
                    new AccountCreationResponse("failure", "Username already exist"));
        }

        if (authDataService.findByEmail(accountCreationRequest.getEmail()) != null) {
            return ResponseEntity.ok(
                    new AccountCreationResponse("failure", "Email already exist"));
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(accountCreationRequest.getEmail());
        userInfo.setFirstName(accountCreationRequest.getFirstName());
        userInfo.setLastName(accountCreationRequest.getLastName());
        userInfo.setPassword(accountCreationRequest.getPassword());
        userInfo.setUsername(accountCreationRequest.getUsername());

        authDataService.createUserProfile(userInfo);
        return ResponseEntity.ok(new AccountCreationResponse("success", null));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestHeader(value = "Authorization") String headerData) {

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        String[] data = headerData.split(" ");
        byte[] decoded = Base64.getDecoder().decode(data[1]);
        String decodedStr = new String(decoded, StandardCharsets.UTF_8);
        data = decodedStr.split(":");

        authenticationRequest.setUsername(data[0]);
        authenticationRequest.setPassword(data[1]);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            Md5Util.getInstance().getMd5Hash(authenticationRequest.getPassword()))
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new AuthenticationResponse(null, "Incorrect username or password.",
                    null));
        } catch (Exception e) {
            return ResponseEntity.ok(new AuthenticationResponse(null, "Username does not exist.",
                    null));
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        UserInfo userInfo = authDataService.findByUsername(authenticationRequest.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt, null, userInfo.getFirstName()));
    }
}
