package com.example.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {
    int id;
    String username;
    String password;
    String role;
}