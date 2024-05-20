package com.bank.wallet.service;

import com.bank.wallet.dto.ChangeWalletRequest;
import com.bank.wallet.model.Wallet;

import java.util.UUID;

public interface WalletService {

    Wallet changeAmount(ChangeWalletRequest request);

    Wallet getWallet(UUID id);
}
