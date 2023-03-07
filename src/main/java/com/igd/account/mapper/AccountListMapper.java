package com.igd.account.mapper;

import com.igd.account.dto.AccountListDTO;
import com.igd.account.entity.Account;
import lombok.RequiredArgsConstructor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Stream;

@Mapper
public interface AccountListMapper {

    AccountListMapper INSTANCE = Mappers.getMapper(AccountListMapper.class);

//    @Mapping(source = "qax", target = "baz")
//    @Mapping(source = "baz", target = "qax")
    AccountListDTO toAccountListDTO(Account account);



    @InheritInverseConfiguration
    Account toAccount(AccountListDTO accountListDTO);
}

