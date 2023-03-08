package com.igd.account.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="TransactionHistory")
public class TransactionHistory  extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
//    private String accountNumber;
//    private String accountName;
    private LocalDateTime valueDate;
//    @Enumerated(EnumType.STRING)
//    private CurrencyType currencyType;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private String transactionNarrative;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    Account account;


}
