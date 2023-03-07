package com.igd.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Currency;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="AccountNew")
public class Account  extends AuditableEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountNumber;
    private String accountName;
    private AccountType accountType;
    private Date balanceDate;
    private CurrencyType currencyType;
    private Currency openingAvailableBalance;

    // Mapping to the Transaction Table
    @OneToMany(cascade = CascadeType.ALL)
    private Set<TransactionHistory> transactionHistory;

}
