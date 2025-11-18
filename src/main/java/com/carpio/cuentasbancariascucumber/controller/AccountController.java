package com.carpio.cuentasbancariascucumber.controller;

import com.carpio.cuentasbancariascucumber.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/balance/{client}")
    public ResponseEntity<?> getBalance(@PathVariable String client) {
        Double balance = accountService.getBalance(client);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositBalance(@RequestParam String client, @RequestParam Double amount) {
        if (client == null || client.isEmpty() || amount == null || amount <= 0) {
            return ResponseEntity.badRequest().body("Invalid client or amount");
        }
        Double newBalance = accountService.depositBalance(client, amount);
        return ResponseEntity.ok(newBalance);
    }
}
