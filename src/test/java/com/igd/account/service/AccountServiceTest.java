package com.igd.account.service;

import com.igd.account.dto.AccountListDTO;
import com.igd.account.dto.AccountServiceResponse;
import com.igd.account.entity.Account;
import com.igd.account.entity.AccountType;
import com.igd.account.entity.CurrencyType;
import com.igd.account.entity.User;
import com.igd.account.exception.NoDataFoundException;
import com.igd.account.exception.UserNotExistException;
import com.igd.account.repository.AccountRepository;
import com.igd.account.repository.UserRepository;
import jakarta.validation.constraints.AssertTrue;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private AccountService accountService;

    @Test
    public void findByAccountNumber() {
        String accountNum = "232323232";
        Mockito.when(accountRepository.findByAccountNumber(accountNum)).thenReturn(getAccount());
        Account accountActual= accountService.findByAccountNumber(accountNum);
        Assertions.assertEquals(getAccount().getAccountName(),accountActual.getAccountName());

    }


    @Test
    public void findAllPage() {
        PageRequest pageRequest = PageRequest.of(0, 1);

        User user= User.builder().id(1L)
                .userName("Tom").userId("TOM123").build();
        List<Account> accountList = Arrays.asList(getAccount());
        Page<Account> accountPage = new PageImpl<>(accountList, pageRequest, accountList.size());



        Mockito.when(accountRepository.findAllByUserId(user.getId(),pageRequest)).thenReturn(accountPage);
        Mockito.when(userRepository.findByUserId(user.getUserId())).thenReturn(user);

        AccountServiceResponse accountServiceResponseActual= accountService.findAllPage(user.getUserId(),pageRequest);

        Assertions.assertEquals(1,accountServiceResponseActual.getContent().size());

    }

    @Test
    public void findAllPage_NoDataFoundException() {
        PageRequest pageRequest = PageRequest.of(0, 1);

        User user= User.builder().id(1L)
                .userName("Tom").userId("TOM123").build();

        Page<Account> accountPage = new PageImpl<>(new ArrayList<>(), pageRequest, 0);

        Mockito.when(accountRepository.findAllByUserId(user.getId(),pageRequest)).thenReturn(accountPage);
        Mockito.when(userRepository.findByUserId(user.getUserId())).thenReturn(user);

        Throwable  throwable = Assertions.assertThrows( Exception.class,()-> {
            AccountServiceResponse accountServiceResponseActual= accountService.findAllPage(user.getUserId(),pageRequest);
        });

        Assertions.assertTrue(throwable instanceof NoDataFoundException);
    }

    @Test
    public void findAllPage_UserNotExistException() {
        PageRequest pageRequest = PageRequest.of(0, 1);

        User user= User.builder().id(1L)
                .userName("Tom").userId("TOM123").build();
        List<Account> accountList = Arrays.asList(getAccount());
        Page<Account> accountPage = new PageImpl<>(accountList, pageRequest, accountList.size());

        Mockito.when(userRepository.findByUserId(user.getUserId())).thenReturn(null);


        Throwable  throwable = Assertions.assertThrows( Exception.class,()-> {
            AccountServiceResponse accountServiceResponseActual= accountService.findAllPage(user.getUserId(),pageRequest);
        });

        Assertions.assertTrue(throwable instanceof UserNotExistException);
    }


    private AccountListDTO getAccountListDTO() {
        return AccountListDTO.builder()
                .accountNumber("232323232").accountName("SG1122222").accountType(AccountType.SAVINGS.name())
                .balanceDate("ssds").currencyType(CurrencyType.AUD.name())
                .openingAvailableBalance(Double.valueOf("222323222.12")).build();
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
