package com.bank.wallet.controller;

import com.bank.wallet.dto.ChangeWalletRequest;
import com.bank.wallet.model.Wallet;
import com.bank.wallet.service.WalletService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletController {

    private final WalletService service;

    @PostMapping("/wallet")
    public ResponseEntity<Wallet> changeWallet(ChangeWalletRequest request){
        service.changeAmount(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Wallet> getWallet(UUID id) {
        return null;
    }

}
