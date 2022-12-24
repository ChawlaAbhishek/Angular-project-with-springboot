package com.hostbooks.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private boolean success;
}
