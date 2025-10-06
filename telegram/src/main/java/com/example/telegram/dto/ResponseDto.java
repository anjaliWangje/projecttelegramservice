package com.example.telegram.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ResponseDto {

    private String code;
    private String message;

    public ResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
