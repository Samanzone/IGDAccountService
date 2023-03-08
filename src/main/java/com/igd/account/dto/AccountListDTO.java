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

     private String accountNumber;
     private String accountName;
     private String accountType;
     private String balanceDate;
     private String currencyType;
     private Double openingAvailableBalance;
}
