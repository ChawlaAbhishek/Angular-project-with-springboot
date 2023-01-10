package com.hostbooks.Dto;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;

    private String password;
}
