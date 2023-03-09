package com.igd.account.dto;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
    private String errorMessage;
    private String requestedURI;
}
