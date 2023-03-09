package com.igd.account.mapper;

import com.igd.account.dto.AccountListDTO;
import com.igd.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountListMapper {

    AccountListMapper INSTANCE = Mappers.getMapper(AccountListMapper.class);

    @ValueMapping(source = "currencyType", target = "currencyType")
    @ValueMapping(source = "accountNumber", target = "accountNumber")
    @ValueMapping(source = "accountName", target = "accountName")
    @ValueMapping(source = "accountType", target = "accountType")
    @Mapping(source = "balanceDate", target = "balanceDate", dateFormat = "dd.MM.yyyy")
    @Mapping(source = "openingAvailableBalance", target = "openingAvailableBalance", numberFormat = "#.00")
    AccountListDTO toAccountListDTO(Account account);


}

