package com.igd.account.service;

import com.igd.account.dto.AccountListDTO;
import com.igd.account.dto.AccountResponse;
import com.igd.account.entity.Account;
import com.igd.account.mapper.AccountListMapper;
import com.igd.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;


    public AccountResponse findAllPage(Pageable pageable) {

        Page<Account> accounts = accountRepository.findAllPage(pageable);

        List<AccountListDTO> content= accounts.stream()
                .map(account -> AccountListMapper.INSTANCE.toAccountListDTO(account))
                .collect(Collectors.toList());


        return AccountResponse.builder()
                .content(content)
                .pageNo(accounts.getNumber())
                .pageSize(accounts.getSize())
                .totalElements(accounts.getTotalElements())
                .totalPages(accounts.getTotalPages())
                .last(accounts.isLast())
                .build();
    }
}
