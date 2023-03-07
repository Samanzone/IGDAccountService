package com.igd.account.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String accountNumber) {

        super(String.format("Account with AccountNumber %d not found", accountNumber));
    }
}
