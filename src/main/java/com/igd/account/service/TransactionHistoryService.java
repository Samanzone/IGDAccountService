package com.igd.account.service;

import com.igd.account.dto.TransactionHistoryDTO;
import com.igd.account.dto.AccountServiceResponse;
import com.igd.account.entity.TransactionHistory;
import com.igd.account.exception.AccountNotFoundException;
import com.igd.account.mapper.TransactionHistoryMapper;
import com.igd.account.repository.TransactionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;


    public AccountServiceResponse findAllPage(String accountNumber , Pageable pageable) {

        Page<TransactionHistory> transactionHistories = Optional.ofNullable(transactionHistoryRepository
                .findAllPage(accountNumber, pageable)).orElseThrow(() -> new AccountNotFoundException(accountNumber));

        List<TransactionHistoryDTO> content= transactionHistories.stream()
                .map(transactionHistory -> TransactionHistoryMapper.INSTANCE.toTransactionHistoryDTO(transactionHistory))
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
    public void saveAll(List<TransactionHistory> transactionHistories){
        transactionHistoryRepository.saveAll(transactionHistories);
    }
}
