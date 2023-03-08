package com.igd.account.repository;

import com.igd.account.entity.TransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionHistoryPagingRepository extends JpaRepository<TransactionHistory, Long> {

    @Query("SELECT t from TransactionHistory t where t.account.id=?1")
    Page<TransactionHistory> findAllById(Long id, Pageable pageable);

}
