package com.igd.account.service;

import com.igd.account.dto.AccountServiceResponse;
import com.igd.account.dto.TransactionHistoryDTO;
import com.igd.account.entity.Account;
import com.igd.account.entity.TransactionHistory;
import com.igd.account.exception.AccountNotFoundException;
import com.igd.account.exception.NoDataFoundException;
import com.igd.account.mapper.TransactionHistoryMapper;
import com.igd.account.repository.AccountRepository;
import com.igd.account.repository.TransactionHistoryRepository;
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
public class TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;
    private final AccountRepository accountRepository;


    public AccountServiceResponse findAllPage(String accountNumber, Pageable pageable) {

        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (Objects.isNull(account)) {
            throw new AccountNotFoundException(accountNumber);
        }
        Page<TransactionHistory> transactionHistories = transactionHistoryRepository
                .findAllByAccountId(account.getId(), pageable);

        if (transactionHistories.getContent().isEmpty()) {
            throw new NoDataFoundException();
        }


        List<TransactionHistoryDTO> content = transactionHistories.stream()
                .map(transactionHistory -> TransactionHistoryMapper.INSTANCE.toTransactionHistoryDTO(transactionHistory, account))
                .collect(Collectors.toList());


        return AccountServiceResponse.<TransactionHistoryDTO>builder()
                .content(content)
                .pageNo(transactionHistories.getNumber())
                .pageSize(transactionHistories.getSize())
                .totalElements(transactionHistories.getTotalElements())
                .totalPages(transactionHistories.getTotalPages())
                .last(transactionHistories.isLast())
                .build();
    }

    @Transactional
    public void saveAll(List<TransactionHistory> transactionHistories) {
        transactionHistoryRepository.saveAll(transactionHistories);
    }
}
