package com.sistemaactivos.webclient.ary.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ErrorResponse {
    private String status_code;
    private String status;
    private String message;
}