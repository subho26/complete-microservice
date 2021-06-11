package com.auth.controller;

import com.auth.model.AuthRequest;
import com.auth.model.AuthResponse;
import com.auth.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest, HttpServletResponse response, HttpServletRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        Cookie cookie = new Cookie("username", "Jovan");
        cookie.setSecure(false);
        cookie.setHttpOnly(true);

        //add cookie to response
        response.addCookie(cookie);
        return new ResponseEntity<>(jwtUtil.prepareAuthResponse(authRequest), HttpStatus.OK);
    }
}
