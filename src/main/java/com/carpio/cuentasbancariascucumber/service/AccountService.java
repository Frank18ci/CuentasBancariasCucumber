package com.carpio.cuentasbancariascucumber.service;

import com.carpio.cuentasbancariascucumber.model.Account;

import java.util.List;

public interface AccountService {
    Double depositBalance(String client, Double amount);

    Double getBalance(String client);

    List<Account> getAllAccounts();
}
