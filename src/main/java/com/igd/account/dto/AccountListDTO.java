package com.igd.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountListDTO {

    private String accountNumber;
    private String accountName;
    private String accountType;
    private String balanceDate;
    private String currencyType;
    private Double openingAvailableBalance;
}
