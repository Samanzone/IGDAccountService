package com.igd.account.service;

import com.igd.account.dto.AccountListDTO;
import com.igd.account.dto.AccountServiceResponse;
import com.igd.account.entity.Account;
import com.igd.account.entity.User;
import com.igd.account.exception.NoDataFoundException;
import com.igd.account.exception.UserNotExistException;
import com.igd.account.mapper.AccountListMapper;
import com.igd.account.repository.AccountRepository;
import com.igd.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;


    public AccountServiceResponse findAllPage(String userId, Pageable pageable)  {

        User user = userRepository.findByUserId(userId);

        if (Objects.isNull(user)) {
            throw new UserNotExistException(userId);
        }

        Page<Account> accounts = accountRepository.findAllByUserId(user.getId(), pageable);

        if (accounts.getContent().isEmpty()) {
            throw new NoDataFoundException();
        }
        List<AccountListDTO> content = accounts.stream()
                .map(account -> AccountListMapper.INSTANCE.toAccountListDTO(account))
                .collect(Collectors.toList());


        return AccountServiceResponse.<AccountListDTO>builder()
                .content(content)
                .pageNo(accounts.getNumber())
                .pageSize(accounts.getSize())
                .totalElements(accounts.getTotalElements())
                .totalPages(accounts.getTotalPages())
                .last(accounts.isLast())
                .build();
    }

    @Transactional
    public void saveAll(List<Account> accounts) {
        accountRepository.saveAll(accounts);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new NoDataFoundException());
    }

    public Account findByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (Objects.isNull(account)) {
            throw new NoDataFoundException();
        }
        return account;
    }
}
