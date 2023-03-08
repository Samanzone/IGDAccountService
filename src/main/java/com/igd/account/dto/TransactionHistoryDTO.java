package com.igd.account.dto;

import com.igd.account.entity.CurrencyType;
import com.igd.account.entity.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@NotEmpty
@Getter
@Setter
public class TransactionHistoryDTO {

    private String accountNumber;
    private String accountName;
    private String valueDate;
    private CurrencyType currencyType;
    private String creditAmount;
    private String debitAmount;
    private TransactionType transactionType;

    private String transactionNarrative;
}
