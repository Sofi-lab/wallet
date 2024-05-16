package com.bank.wallet.controller;

import com.bank.wallet.dto.ChangeWalletRequest;
import com.bank.wallet.model.Wallet;
import com.bank.wallet.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletController {

    private final WalletService service;

    @PostMapping("/wallet")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Wallet> changeWallet(@RequestBody @Valid ChangeWalletRequest request){
        service.changeAmount(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wallets/{wallet_UUID}")
    public ResponseEntity<Wallet> getWallet(@PathVariable UUID wallet_UUID) {
        Wallet wallet = service.getWallet(wallet_UUID);
        return ResponseEntity.ok(wallet);
    }

}
