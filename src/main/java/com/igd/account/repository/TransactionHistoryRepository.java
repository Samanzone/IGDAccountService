package com.igd.account.repository;

import com.igd.account.entity.Account;
import com.igd.account.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Long> {

    @Query("select t from TransactionHistory t where t.accountNumber=id")
    Page<TransactionHistory> findAllPage(@Param("id") String accountNumber, Pageable pageable);

}
