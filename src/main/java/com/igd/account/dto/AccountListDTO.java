package com.igd.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igd.account.entity.AccountType;
import com.igd.account.entity.CurrencyType;

import java.util.Currency;
import java.util.Date;

public class AccountListDTO {

    @JsonProperty("accountNumber")
    private String accountNumber;
    private String accountName;
    private AccountType accountType;
    private Date balanceDate;
    private CurrencyType currencyType;
    private Currency openingAvailableBalance;
}
