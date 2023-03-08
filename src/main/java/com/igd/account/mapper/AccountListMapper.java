package com.igd.account.mapper;

import com.igd.account.dto.AccountListDTO;
import com.igd.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Stream;
import java.time.format.DateTimeFormatter;

@Mapper
public interface AccountListMapper {

    AccountListMapper INSTANCE = Mappers.getMapper(AccountListMapper.class);

//    @Mapping(source = "balanceDate.format(DateTimeFormatter.ISO_DATE)", target = "balanceDate")
    @ValueMapping(source = "currencyType", target = "currencyType")
    @ValueMapping(source = "accountNumber", target = "accountNumber")
    @ValueMapping(source = "accountName", target = "accountName")
//    @Mapping(source = "id", ignore = true)
//    @Mapping(source = "transactionHistory", ignore = true)
    @ValueMapping(source = "accountType", target = "accountType")
    @Mapping(source = "balanceDate", target = "balanceDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "openingAvailableBalance", target = "openingAvailableBalance", numberFormat = "#.00")
    AccountListDTO toAccountListDTO(Account account);



    @InheritInverseConfiguration
    Account toAccount(AccountListDTO accountListDTO);
}

