package com.example.online_store.dao;

import com.example.online_store.model.Account;

public interface AccountDao {
    Account findAccountByEmailOrPhone(String email, String phone);
    boolean saveAccount(Account account);
}
