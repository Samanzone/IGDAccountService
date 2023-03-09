package com.igd.account.controller;

import com.igd.account.dto.AccountServiceResponse;
import com.igd.account.service.AccountService;
import com.igd.account.service.TransactionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @GetMapping(value = "/v1/user-id/{userId}/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "/v1/user-id/{userId}/accounts", description = "List All Accounts")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "1001", description = "Application specific error.")})
    AccountServiceResponse getAccountByUserId(@PathVariable final String userId,
                                              @PageableDefault(page = 0, size = 20)
                                              @SortDefault.SortDefaults({
                                                      @SortDefault(sort = "accountNumber", direction = Sort.Direction.DESC),
                                                      @SortDefault(sort = "accountName", direction = Sort.Direction.ASC)
                                              })
                                              Pageable pageable) {


        return accountService.findAllPage(userId, pageable);
    }

    @GetMapping(value = "/v1/accounts/account-num/{accountNum}/transhistory", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "/v1/accounts/account-num/{accountNum}/transhistory", description = "Particular Account Transaction History")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "404", description = "Record not found"),
            @ApiResponse(responseCode = "1001", description = "Application specific error.")})
    AccountServiceResponse getAccountsByAccountNumber(@PathVariable final String accountNum,
                                                      @PageableDefault(page = 0, size = 20)
                                                      @SortDefault.SortDefaults({
                                                              @SortDefault(sort = "valueDate", direction = Sort.Direction.DESC)
                                                      })
                                                      Pageable pageable) {
        return transactionHistoryService.findAllPage(accountNum, pageable);

    }

}
