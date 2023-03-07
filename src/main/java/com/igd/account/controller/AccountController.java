package com.igd.account.controller;

import com.igd.account.dto.AccountListDTO;
import com.igd.account.dto.AccountResponse;
import com.igd.account.service.AccountService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {


    @Autowired
    private  AccountService accountService;

    @GetMapping(path = "/page")
    AccountResponse loadAccountPage(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            })
            Pageable pageable) {
        return accountService.findAllPage(pageable);
    }
//    @GetMapping(path = "/sorted")
//    List<AccountListDTO> loadAccountSorted(Sort sort) {
//        return accountService.findAllSorted(sort);
//    }
//
//
//
//    @GetMapping(path = "/slice")
//    Slice<AccountListDTO> loadAccountSlice(Pageable pageable) {
//        return accountService.findAllSlice(pageable);
//    }
}
