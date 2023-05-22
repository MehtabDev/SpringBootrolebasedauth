package com.backend.unskoolify.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private boolean status;
    private String message;
    private List<?> data;
    
}
