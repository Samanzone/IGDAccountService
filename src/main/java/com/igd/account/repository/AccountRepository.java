package com.igd.account.repository;

import com.igd.account.entity.Account;
import com.igd.account.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("select c from Account c")
    Page<Account> findAllPage(Pageable pageable);

    @Query("select c from Account c where c.accountNumber=?1")
    Account findByAccountNumber(String accountNumber);


}
