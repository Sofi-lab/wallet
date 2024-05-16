package com.bank.wallet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ChangeWalletRequest {

    @NotNull
    private UUID id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private OperationType operationType;

}
