package com.carpio.cuentasbancariascucumber.service;

import com.carpio.cuentasbancariascucumber.model.Account;
import com.carpio.cuentasbancariascucumber.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;

    @Override
    public Double depositBalance(String client, Double amount) {
        if(client.isBlank()){
            throw new RuntimeException("Client name cannot be blank");
        }
        log.info("Starting deposit of {} for client {}", amount, client);
        try{
            Account account = accountRepository.findByClientIgnoreCase(client).orElseThrow(
                    () -> new RuntimeException("Account not found for client: " + client)
            );
            account.setBalance(account.getBalance() + amount);
            this.accountRepository.save(account);
            return account.getBalance();
        } catch(Exception e){
            log.error("Error during deposit of {} for client {}", amount, client, e);
            return 0.0;
        }
    }

    @Override
    public Double getBalance(String client) {
        return this.accountRepository.findByClientIgnoreCase(client).map(Account::getBalance).orElseThrow(
                () -> new RuntimeException("Account not found for client: " + client)
        );
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }
}
