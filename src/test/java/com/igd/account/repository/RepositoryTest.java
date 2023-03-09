package com.igd.account.repository;

import com.igd.account.entity.Account;
import com.igd.account.entity.TransactionHistory;
import com.igd.account.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {
    @Autowired
    TestEntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(em);
    }

    @Test
    void verifyBootstrappingByPersistingUser() {
        User user  = new User();
        user.setUserName("Tom");
        user.setUserId("Tom123");

        Assertions.assertNull(user.getId());
        em.persist(user);
        Assertions.assertNotNull(user.getId());
    }
    @Test
    void verifyBootstrappingByPersistingAccount() {
        Account account  = new Account();
        account.setAccountName("Tom");
        account.setAccountNumber("Tom123");

        Assertions.assertNull(account.getId());
        em.persist(account);
        Assertions.assertNotNull(account.getId());
    }

    @Test
    void verifyBootstrappingByPersistingTransactionHistory() {
        Account account  = new Account();
        account.setAccountName("Tom");
        account.setAccountNumber("Tom123");

        em.persist(account);

        TransactionHistory transactionHistory  = new TransactionHistory();
        transactionHistory.setTransactionNarrative("test");
        transactionHistory.setAccount(account);

        Assertions.assertNull(transactionHistory.getId());
        em.persist(transactionHistory);
        Assertions.assertNotNull(transactionHistory.getId());
    }


}
