package com.igd.account.mapper;

import com.igd.account.dto.TransactionHistoryDTO;
import com.igd.account.entity.Account;
import com.igd.account.entity.TransactionHistory;
import com.igd.account.entity.TransactionType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionHistoryMapper {
    TransactionHistoryMapper INSTANCE = Mappers.getMapper(TransactionHistoryMapper.class);

    @Mapping(source = "account.accountNumber", target = "accountNumber")
    @Mapping(source = "account.accountName", target = "accountName")
    @ValueMapping(source = "account.currencyType", target = "currencyType")
    @Mapping(source = "transactionHistory.valueDate", target = "valueDate", dateFormat = "dd.MM.yyyy")
    @ValueMapping(source = "transactionHistory.transactionType", target = "transactionType")
    @Mapping(expression = "java(getCredit(transactionHistory))", target = "creditAmount", numberFormat = "#.00")
    @Mapping(expression = "java(getDebit(transactionHistory))", target = "debitAmount" , numberFormat = "#.00")

    TransactionHistoryDTO toTransactionHistoryDTO(TransactionHistory transactionHistory, Account account);

    default Double getCredit(TransactionHistory transactionHistory ) {
        if( TransactionType.CREDIT.equals(transactionHistory.getTransactionType())){
            return  transactionHistory.getAmount().doubleValue();
        }else {
            return null;
        }
    }
    default Double getDebit(TransactionHistory transactionHistory ) {
        if( TransactionType.DEBIT.equals(transactionHistory.getTransactionType())){
            return  transactionHistory.getAmount().doubleValue();
        }else {
            return null;
        }
    }

//    default String resolveName(final User model) {
//        return String.format("%s %s", model.firstName, model.lastName);
//    }
//    @AfterMapping
//    default void debitCredit(@MappingTarget final TransactionHistoryDTO transactionHistoryDTO,
//                             final TransactionHistory transactionHistory) {
//       if( TransactionType.DEBIT.equals(transactionHistory.getTransactionType())){
//           transactionHistoryDTO.setDebitAmount(transactionHistory.getAmount().doubleValue());
//       }
//        if( TransactionType.CREDIT.equals(transactionHistory.getTransactionType())){
//            transactionHistoryDTO.setCreditAmount(transactionHistory.getAmount().doubleValue());
//        }
//    }
}
