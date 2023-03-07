package com.igd.account.loading;

import com.igd.account.dto.AccountListDTO;
import com.igd.account.entity.*;
import com.igd.account.service.AccountService;
import com.igd.account.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountRunner implements CommandLineRunner {


    private final AccountService accountService;
    private final TransactionHistoryService transactionHistoryService ;



    @Override
    public void run(String... args) throws Exception {

        log.info("Loading Account Details.....");


       Stream<Account> accountStream = Stream.of(Account.builder()
                .accountNumber("232323232").accountName("SG1122222").accountType(AccountType.SAVINGS)
                .balanceDate(LocalDateTime.now().minusDays(10)).currencyType(CurrencyType.AUD)
               .openingAvailableBalance(new BigDecimal("2222.22")).build());
        accountService.saveAll(accountStream.collect(Collectors.toList()));

        log.info("Loading transactionHistory Details.....");


        Stream<TransactionHistory> transactionHistoryStream = Stream.of(TransactionHistory.builder()
                .accountName("232323232").accountName("SG1122222").amount(new BigDecimal("2222.22"))
                .currencyType(CurrencyType.AUD).transactionType(TransactionType.CREDIT).transactionNarrative("Test Narrative")
                .valueDate(LocalDateTime.now().minusDays(10)).build());
        transactionHistoryService.saveAll(transactionHistoryStream.collect(Collectors.toList()));

        log.info("Data loading is done");

    }
}