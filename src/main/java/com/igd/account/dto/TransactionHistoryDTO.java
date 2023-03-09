package com.igd.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionHistoryDTO {

    private String accountNumber;
    private String accountName;
    private String valueDate;
    private String currencyType;
    private Double creditAmount;
    private Double debitAmount;
    private String transactionType;
    private String transactionNarrative;
}
