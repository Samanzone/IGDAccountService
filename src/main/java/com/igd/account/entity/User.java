package com.igd.account.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Account_User")
public class User extends AuditableEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String userName;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    private Set<Account> accounts;

}
