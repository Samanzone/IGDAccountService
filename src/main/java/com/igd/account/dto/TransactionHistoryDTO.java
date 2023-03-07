package com.igd.account.dto;

import com.igd.account.entity.CurrencyType;
import com.igd.account.entity.TransactionType;
import jakarta.validation.constraints.NotEmpty;

import java.util.Currency;
import java.util.Date;

@NotEmpty
public class TransactionHistoryDTO {

    private String accountNumber;
    private String accountName;
    private Date valueDate;
    private CurrencyType currencyType;
    private Currency creditAmount;
    private Currency debitAmount;
    private TransactionType transactionType;

    private String transactionNarrative;
}
