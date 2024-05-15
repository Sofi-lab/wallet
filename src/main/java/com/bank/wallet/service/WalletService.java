package com.bank.wallet.service;

import com.bank.wallet.dto.ChangeWalletRequest;
import com.bank.wallet.dto.OperationType;
import com.bank.wallet.exception.NotFoundException;
import com.bank.wallet.exception.UnknownRequestException;
import com.bank.wallet.model.Wallet;
import com.bank.wallet.repository.WalletRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository repository;

    public void changeAmount(ChangeWalletRequest request){
        Wallet wallet = repository.findById(request.getId()).orElseThrow(() ->
                new NotFoundException("Такого счета нет " + request.getId()));
        if( request.getOperationType().equals(OperationType.DEPOSIT )) {
            wallet.getAmount().add(request.getAmount());
        } else if( request.getOperationType().equals(OperationType.WITHDRAW )) {
            wallet.getAmount().subtract(request.getAmount());
        } else {
            throw new UnknownRequestException("Неизвестный тип операции " + request.getOperationType());
        }
    }
}
