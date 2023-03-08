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
@Entity
@Table(name="Account")
public class Account  extends AuditableEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String accountNumber;
    private String accountName;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private LocalDateTime balanceDate;
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;
    private BigDecimal openingAvailableBalance;

    // Mapping to the Transaction History Table
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    private Set<TransactionHistory> transactionHistory;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;


}
