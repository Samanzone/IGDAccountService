package com.igd.account.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igd.account.dto.AccountListDTO;
import com.igd.account.dto.AccountResponse;
import com.igd.account.service.AccountService;
import com.igd.account.service.TransactionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AccountController {

    @Autowired
    private  AccountService accountService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

     @GetMapping(value = "/v{version}accounts", produces = MediaType.APPLICATION_JSON_VALUE)
     @Operation(summary = "/v{version}/accounts", description = "List All Accounts")
     @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful"),
             @ApiResponse(responseCode = "500", description = "Internal server error"),
             @ApiResponse(responseCode = "1001", description = "Application specific error.") })
     AccountResponse getAccount(@PathVariable final int version,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "accountNumber", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "accountName", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {
        return accountService.findAllPage(pageable);
    }

    @GetMapping(value = "/v{version}/accounts/account-num/{account-num}/transhistory", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "/v{version}/accounts/account-num/{account-num}/transhistory", description = "Particular Account Transaction History")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "1001", description = "Application specific error.") })
    AccountResponse getAccountsByAccountNumber(@PathVariable final int version,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "accountNumber", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "accountName", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {
        return accountService.findAllPage(pageable);
    }

}
