package com.igd.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igd.account.entity.AccountType;
import com.igd.account.entity.CurrencyType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountListDTO {

    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("accountName")
    private String accountName;
    @JsonProperty("accountType")
    private String accountType;
    @JsonProperty("balanceDate")
    private String balanceDate;
    @JsonProperty("currencyType")
    private String currencyType;
    @JsonProperty("openingAvailableBalance")
    private Double openingAvailableBalance;
}
