package com.igd.account.mapper;

import com.igd.account.dto.TransactionHistoryDTO;
import com.igd.account.entity.Account;
import com.igd.account.entity.TransactionHistory;
import com.igd.account.entity.TransactionType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface TransactionHistoryMapper {
    TransactionHistoryMapper INSTANCE = Mappers.getMapper(TransactionHistoryMapper.class);


//    @Mapping(source = "java( if (TransactionType.CREDIT.equals(transactionType)) then amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString();)", target = "debitAmount")
//    @Mapping(source = "amount", target = "debitAmount")
//    @Mapping(source = "amount", target = "creditAmount")
//    @Mapping(source = "java( if (TransactionType.DEBIT.equals(transactionType)) then amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString();)", target = "creditAmount")
//    @Mapping(source = "valueDate.format(DateTimeFormatter.ISO_DATE)", target = "valueDate")
    @Mapping(source = "account.accountNumber", target = "accountNumber")
    @Mapping(source = "account.accountName", target = "accountName")
    @Mapping(source = "account.currencyType", target = "currencyType")
    TransactionHistoryDTO toTransactionHistoryDTO(TransactionHistory transactionHistory, Account account);


}
