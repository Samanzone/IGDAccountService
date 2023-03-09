package com.igd.account.service;

import com.igd.account.dto.AccountListDTO;
import com.igd.account.dto.AccountServiceResponse;
import com.igd.account.entity.*;
import com.igd.account.exception.AccountNotFoundException;
import com.igd.account.exception.NoDataFoundException;
import com.igd.account.repository.AccountRepository;
import com.igd.account.repository.TransactionHistoryRepository;
import com.igd.account.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class TransactionHistoryServiceTest {

    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;

    @Mock
    private AccountRepository accountRepository;


    @InjectMocks
    private TransactionHistoryService transactionHistoryService;



    @Test
    public void findAllPage() {
        PageRequest pageRequest = PageRequest.of(0, 1);

        User user= User.builder().id(1L)
                .userName("Tom").userId("TOM123").build();
         Page<TransactionHistory> transactionHistoryPage = new PageImpl<>(getTransactionHistoryList(), pageRequest, getTransactionHistoryList().size());

        Mockito.when(accountRepository.findByAccountNumber(getAccount().getAccountNumber())).thenReturn(getAccount());
        Mockito.when(transactionHistoryRepository.findAllByAccountId(getAccount().getId(),pageRequest)).thenReturn(transactionHistoryPage);

        AccountServiceResponse accountServiceResponseActual= transactionHistoryService.findAllPage(getAccount().getAccountNumber(),pageRequest);

        Assertions.assertEquals(2,accountServiceResponseActual.getContent().size());

    }

    @Test
    public void findAllPage_AccountNotFoundException() {
        PageRequest pageRequest = PageRequest.of(0, 1);

        User user= User.builder().id(1L)
                .userName("Tom").userId("TOM123").build();
        Page<TransactionHistory> transactionHistoryPage = new PageImpl<>(getTransactionHistoryList(), pageRequest, getTransactionHistoryList().size());

        Mockito.when(accountRepository.findByAccountNumber(getAccount().getAccountNumber())).thenReturn(null);

        Throwable  throwable = Assertions.assertThrows( Exception.class,()-> {
            AccountServiceResponse accountServiceResponseActual = transactionHistoryService.findAllPage(getAccount().getAccountNumber(), pageRequest);
        });

        Assertions.assertTrue(throwable instanceof AccountNotFoundException);
    }
     @Test
     public void findAllPage_NoDataFoundException() {
            PageRequest pageRequest = PageRequest.of(0, 1);

            User user= User.builder().id(1L)
                    .userName("Tom").userId("TOM123").build();


            Page<TransactionHistory> transactionHistoryPage = new PageImpl<>(new ArrayList<>(), pageRequest, 0);

         Mockito.when(accountRepository.findByAccountNumber(getAccount().getAccountNumber())).thenReturn(getAccount());
         Mockito.when(transactionHistoryRepository.findAllByAccountId(getAccount().getId(),pageRequest)).thenReturn(transactionHistoryPage);

            Throwable  throwable = Assertions.assertThrows( Exception.class,()-> {
                AccountServiceResponse accountServiceResponseActual = transactionHistoryService.findAllPage(getAccount().getAccountNumber(), pageRequest);
            });

            Assertions.assertTrue(throwable instanceof NoDataFoundException);
        }


    private List<TransactionHistory> getTransactionHistoryList() {

       User user= User.builder()
                .userName("Tom").userId("TOM123").build();
       Account account= Account.builder()
                .accountNumber("232323232").accountName("SG1122222").accountType(AccountType.SAVINGS)
                .balanceDate(LocalDateTime.now().minusDays(10)).currencyType(CurrencyType.AUD)
                .openingAvailableBalance(new BigDecimal("222323222.12")).user(user).build();


        Stream<TransactionHistory> transactionHistoryStream = Stream.of(
                TransactionHistory.builder().amount(new BigDecimal("2222.22")).transactionType(TransactionType.CREDIT)
                        .transactionNarrative("Test Narrative").valueDate(LocalDateTime.now().minusDays(10)).account(account).build(),
                TransactionHistory.builder().amount(new BigDecimal("222323222.12")).transactionType(TransactionType.DEBIT)
                        .transactionNarrative("Test 2 Narrative").valueDate(LocalDateTime.now().minusDays(20)).account(account).build());

      return   transactionHistoryStream.collect(Collectors.toList());
    }

    private Account getAccount() {
        User user= User.builder().id(1L)
                .userName("Tom").userId("TOM123").build();

        return Account.builder()
                .accountNumber("232323232").accountName("SG1122222").accountType(AccountType.SAVINGS)
                .balanceDate(LocalDateTime.now().minusDays(10)).currencyType(CurrencyType.AUD)
                .openingAvailableBalance(new BigDecimal("222323222.12")).user(user).build();
    }

}
