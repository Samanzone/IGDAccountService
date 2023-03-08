package com.igd.account.dto;

import com.igd.account.entity.CurrencyType;
import com.igd.account.entity.TransactionType;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionHistoryDTO {

    private String accountNumber;
    private String accountName;
    private String valueDate;
    private String currencyType;
    private String creditAmount;
    private String debitAmount;
    private String transactionType;
    private String transactionNarrative;
}
