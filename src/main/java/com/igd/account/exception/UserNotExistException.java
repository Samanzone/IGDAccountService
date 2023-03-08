package com.igd.account.exception;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(String userId) {
        super(String.format("User with userid %d does not exist", userId));

    }
}
