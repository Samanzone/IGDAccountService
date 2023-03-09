package com.igd.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.igd.account.dto.AccountListDTO;
import com.igd.account.dto.AccountServiceResponse;
import com.igd.account.dto.TransactionHistoryDTO;
import com.igd.account.entity.*;
import com.igd.account.exception.UserNotExistException;
import com.igd.account.mapper.AccountListMapper;
import com.igd.account.service.AccountService;
import com.igd.account.service.TransactionHistoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;
    @MockBean
    private TransactionHistoryService transactionHistoryService;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllAccounts() throws Exception {

        String userId = "Tom121";
        PageRequest pageRequest = PageRequest.of(0, 1,
                Sort.by("accountNumber").descending().and(Sort.by("accountName").ascending()));

        when(accountService.findAllPage(userId,pageRequest))
                .thenReturn(getAccountServiceResponse());


        mockMvc.perform(get("/api/v1/user-id/{userId}/accounts", userId)
                        .param("page", "0")
                        .param("size", "1")
                        .param("sort", "accountNumber,desc")
                        .param("sort", "accountName,asc"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{\"accountNumber\":\"232323232\",\"accountName\":\"SG1122222\",\"accountType\":\"SAVINGS\",\"balanceDate\":\"ssds\",\"currencyType\":\"AUD\",\"openingAvailableBalance\":2.2232322212E8}")))
                .andDo(print());

    }


    @Test
    public void getAccountsByAccountNumber() throws Exception {

        String accountNum = "232323232";
        PageRequest pageRequest = PageRequest.of(0, 1,
                Sort.by("valueDate").ascending());

        when(transactionHistoryService.findAllPage(accountNum,pageRequest))
                .thenReturn(getTransactionHistoryServiceResponse());


        mockMvc.perform(get("/api/v1/accounts/account-num/{accountNum}/transhistory", accountNum)
                        .param("page", "0")
                        .param("size", "1")
                        .param("sort", "valueDate,asc"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{\"accountNumber\":\"232323232\",\"accountName\":\"SG1122222\",\"valueDate\":\"sdsds\",\"currencyType\":\"AUD\",\"creditAmount\":\"2222.22\",\"debitAmount\":\"222.44\",\"transactionType\":\"CREDIT\",\"transactionNarrative\":\"test\"}")))

                .andDo(print());

    }

    private AccountListDTO getAccountListDTO() {
        return AccountListDTO.builder()
                .accountNumber("232323232").accountName("SG1122222").accountType(AccountType.SAVINGS.name())
                .balanceDate("ssds").currencyType(CurrencyType.AUD.name())
                .openingAvailableBalance(Double.valueOf("222323222.12")).build();
    }

    private AccountServiceResponse getAccountServiceResponse() {
        Stream<AccountListDTO> accountStream = Stream.of(getAccountListDTO());

        return AccountServiceResponse.<AccountListDTO>builder()
                .content(accountStream.collect(Collectors.toList()))
                .pageNo(0)
                .pageSize(1)
                .totalElements(1)
                .totalPages(1)
                .last(true)
                .build();

    }

    private TransactionHistoryDTO getTransactionHistoryDTO() {
        return TransactionHistoryDTO.builder()
                .accountNumber("232323232").accountName("SG1122222").transactionType(TransactionType.CREDIT.name())
                .valueDate("sdsds").creditAmount("2222.22").debitAmount("222.44").currencyType(CurrencyType.AUD.name())
                .transactionNarrative("test").build();
    }

    private AccountServiceResponse getTransactionHistoryServiceResponse() {
        Stream<TransactionHistoryDTO> transactionHistoryDTOStream = Stream.of(getTransactionHistoryDTO());

        return AccountServiceResponse.<TransactionHistoryDTO>builder()
                .content(transactionHistoryDTOStream.collect(Collectors.toList()))
                .pageNo(0)
                .pageSize(1)
                .totalElements(2)
                .totalPages(1)
                .last(true)
                .build();

    }
}
