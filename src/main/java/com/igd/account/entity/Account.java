package com.igd.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="Account")
public class Account  extends AuditableEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountNumber;
    private String accountName;
    private AccountType accountType;
    private LocalDateTime balanceDate;
    private CurrencyType currencyType;
    private BigDecimal openingAvailableBalance;

    // Mapping to the Transaction Table
    @OneToMany(cascade = CascadeType.ALL)
    private Set<TransactionHistory> transactionHistory;

}
