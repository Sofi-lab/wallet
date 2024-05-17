package com.bank.wallet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ChangeWalletRequest {

    @NotNull(message = "Field 'id' can not be null")
    private UUID id;

    @NotNull(message = "Field 'amount' can not be null")
    private BigDecimal amount;

    @NotNull(message = "Field 'operationType' can not be null")
    private OperationType operationType;
}
