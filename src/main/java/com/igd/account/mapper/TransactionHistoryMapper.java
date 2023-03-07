package com.igd.account.mapper;

import com.igd.account.dto.TransactionHistoryDTO;
import com.igd.account.entity.TransactionHistory;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionHistoryMapper {
    TransactionHistoryMapper INSTANCE = Mappers.getMapper(TransactionHistoryMapper.class);
//    TransactionHistoryDTO convert(TransactionHistory  transactionHistory);


//    @Mapping(source = "qax", target = "baz")
//    @Mapping(source = "baz", target = "qax")
    TransactionHistoryDTO transactionHistoryToTransactionHistoryDTO(TransactionHistory transactionHistory);

//    @InheritInverseConfiguration
//    TransactionHistory TransactionHistoryDTOToTransactionHistory(TransactionHistoryDTO transactionHistoryDTO);
}
