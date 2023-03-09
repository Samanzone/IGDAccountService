package com.igd.account.loading;

import com.igd.account.entity.*;
import com.igd.account.service.AccountService;
import com.igd.account.service.TransactionHistoryService;
import com.igd.account.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
@RequiredArgsConstructor
public class AccountRunner implements CommandLineRunner {


    private final AccountService accountService;
    private final TransactionHistoryService transactionHistoryService;
    private final UserService userService;


    @Override
    public void run(String... args) throws Exception {

        log.info("Loading User Details.....");


        Stream<User> userStream = Stream.of(User.builder()
                .userName("Tom").userId("TOM123").build());
        userService.saveAll(userStream.collect(Collectors.toList()));

        User user = userService.findByUserId("TOM123");

        log.info("Loading Account Details.....");


        Stream<Account> accountStream = Stream.of(
                Account.builder().accountNumber("232323232").accountName("SG1122222").accountType(AccountType.SAVINGS)
                        .balanceDate(LocalDateTime.now().minusDays(10)).currencyType(CurrencyType.AUD)
                        .openingAvailableBalance(new BigDecimal("222323222.12")).user(user).build(),
                Account.builder().accountNumber("132323232").accountName("TG1122222").accountType(AccountType.SAVINGS)
                        .balanceDate(LocalDateTime.now().minusDays(10)).currencyType(CurrencyType.AUD)
                        .openingAvailableBalance(new BigDecimal("222323222.12")).user(user).build(),
                Account.builder().accountNumber("332323232").accountName("PA1122222").accountType(AccountType.SAVINGS)
                        .balanceDate(LocalDateTime.now().minusDays(10)).currencyType(CurrencyType.AUD)
                        .openingAvailableBalance(new BigDecimal("22232322.12")).user(user).build()
        );


        accountService.saveAll(accountStream.collect(Collectors.toList()));

        log.info("Loading transactionHistory Details.....");

        Account account = accountService.findByAccountNumber("232323232");
        Account account2 = accountService.findByAccountNumber("132323232");

        Stream<TransactionHistory> transactionHistoryStream = Stream.of(
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account2).build(),

                TransactionHistory.builder().amount(new BigDecimal("22252.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("22251.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account2).build(),
                TransactionHistory.builder().amount(new BigDecimal("2212.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account2).build(),

                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("22522.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account2).build(),
                TransactionHistory.builder().amount(new BigDecimal("22522.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("22252.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.DEBIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("22722.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.DEBIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(11)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.DEBIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(12)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("22252.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(13)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(14)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("222323222.12")).transactionType(TransactionType.DEBIT)
                        .transactionNarrative("Test 2 Narrative").valueDate(LocalDateTime.now().minusDays(20)).account(account).build());

        transactionHistoryService.saveAll(transactionHistoryStream.collect(Collectors.toList()));

        log.info("Data loading is done");

    }
}