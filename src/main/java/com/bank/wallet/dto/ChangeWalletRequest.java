package com.bank.wallet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ChangeWalletRequest {

    @NotNull
    private UUID id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private OperationType operationType;
}
