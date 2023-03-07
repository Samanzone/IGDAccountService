package com.igd.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igd.account.entity.AccountType;
import com.igd.account.entity.CurrencyType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.Date;
@Data
@Builder
@Getter
@Setter
public class AccountListDTO {

    @JsonProperty("accountNumber")
    private String accountNumber;
    @JsonProperty("accountName")
    private String accountName;
    @JsonProperty("accountType")
    private AccountType accountType;
    @JsonProperty("balanceDate")
    private Date balanceDate;
    @JsonProperty("accountNumber")
    private CurrencyType currencyType;
    @JsonProperty("openingAvailableBalance")
    private String openingAvailableBalance;
}
