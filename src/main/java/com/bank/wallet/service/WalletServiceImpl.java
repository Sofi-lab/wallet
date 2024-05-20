package com.bank.wallet.service;

import com.bank.wallet.dto.ChangeWalletRequest;
import com.bank.wallet.dto.OperationType;
import com.bank.wallet.exception.NotFoundException;
import com.bank.wallet.exception.TooBigAmount;
import com.bank.wallet.exception.UnknownRequestException;
import com.bank.wallet.model.Wallet;
import com.bank.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;


    @Transactional
    public Wallet changeAmount(ChangeWalletRequest request) {
        Wallet wallet = repository.findByIdWithLock(request.getId()).orElseThrow(() ->
                new NotFoundException("Такого счета нет " + request.getId()));
        if (OperationType.DEPOSIT.equals(request.getOperationType())) {
            BigDecimal newAmount = wallet.getAmount().add(request.getAmount());
            wallet.setAmount(newAmount);
        } else if (OperationType.WITHDRAW.equals(request.getOperationType())) {
            if (request.getAmount().compareTo(wallet.getAmount()) == 1) {
                throw new TooBigAmount("Недостаточно средств на счете, баланс: " + wallet.getAmount());
            } else {
                BigDecimal new_amount = wallet.getAmount().subtract(request.getAmount());
                wallet.setAmount(new_amount);
            }
        } else {
            throw new UnknownRequestException("Неизвестный тип операции " + request.getOperationType());
        }
        return repository.save(wallet);
    }


    public Wallet getWallet(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Такого счета нет " + id));
    }
}
