package com.igd.account.repository;

import com.igd.account.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("select c from Account c")
    Page<Account> findAllPage(Pageable pageable);

}
