package com.app.backend.dto;

public class LoginRequest {
    private String username;
    private String password;

    public LoginRequest(){

    }   

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPasswordEncoder(){
        this.password = password;
    }
}