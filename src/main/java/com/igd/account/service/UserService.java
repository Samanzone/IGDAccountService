package com.igd.account.service;

import com.igd.account.entity.TransactionHistory;
import com.igd.account.entity.User;
import com.igd.account.repository.AccountRepository;
import com.igd.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void saveAll(List<User> users){
        userRepository.saveAll(users);
    }

    public User findByUserId(String userId) {
      return   userRepository.findByUserId(userId);
    }
}
