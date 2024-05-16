package com.bank.wallet.controller;

import com.bank.wallet.dto.ChangeWalletRequest;
import com.bank.wallet.dto.OperationType;
import com.bank.wallet.exception.NotFoundException;
import com.bank.wallet.exception.TooBigAmount;
import com.bank.wallet.exception.UnknownRequestException;
import com.bank.wallet.model.Wallet;
import com.bank.wallet.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest({WalletController.class, WalletService.class})
class WalletControllerTest {

    @Autowired
    WalletController controller;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    WalletService service;


    @Test
    void changeAmountDeposit_1000() throws Exception {
        UUID id = UUID.randomUUID();
        ChangeWalletRequest walletRequest = new ChangeWalletRequest(id, BigDecimal.valueOf(1000.00), OperationType.DEPOSIT);
        Wallet wallet = new Wallet(id, BigDecimal.valueOf(2000.00));

        when(service.changeAmount(any())).thenReturn(wallet);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(walletRequest)))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.id").value(id.toString()))
                .andExpect( jsonPath("$.amount").value(2000.00));
    }


    @Test
    void changeAmountWithdraw_1000() throws Exception {
        UUID id = UUID.randomUUID();
        ChangeWalletRequest walletRequest = new ChangeWalletRequest(id, BigDecimal.valueOf(5000.00), OperationType.WITHDRAW);
        Wallet wallet = new Wallet(id, BigDecimal.valueOf(4000.00));

        when(service.changeAmount(any())).thenReturn(wallet);

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(walletRequest)))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.id").value(id.toString()))
                .andExpect( jsonPath("$.amount").value(4000.00));

    }


    @Test
    void changeAmountWithNotFoundId() throws Exception {
        ChangeWalletRequest walletRequest = new ChangeWalletRequest(UUID.randomUUID(), BigDecimal.valueOf(5000.00), OperationType.WITHDRAW);
        when(service.changeAmount(any())).thenThrow(new NotFoundException("Такого счета нет " + walletRequest.getId()));

        mockMvc.perform(post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(walletRequest)))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertEquals("Такого счета нет " + walletRequest.getId(), result.getResolvedException().getMessage()));
    }


    @Test
    void changeAmountWithdraw_1000_NotEnoughMoney() throws Exception {
        UUID id = UUID.randomUUID();

        Wallet wallet = new Wallet(id, BigDecimal.valueOf(4000.00));
        ChangeWalletRequest walletRequest = new ChangeWalletRequest(id, BigDecimal.valueOf(50000.00), OperationType.WITHDRAW);

        when(service.changeAmount(any())).thenThrow(new TooBigAmount("Недостаточно средств на счете, баланс: " + wallet.getAmount()));

        mockMvc.perform(post("/api/v1/wallet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(walletRequest)))
                .andExpect(status().isNotAcceptable())
                .andExpect(result -> assertEquals("Недостаточно средств на счете, баланс: " + wallet.getAmount(), result.getResolvedException().getMessage()));
    }


    @Test
    void changeAmountWithdrawUnknownRequest() throws Exception {
        UUID id = UUID.randomUUID();
        ChangeWalletRequest walletRequest = new ChangeWalletRequest(id, BigDecimal.valueOf(50000.00), null);

        when(service.changeAmount(any())).thenThrow(new UnknownRequestException("Неизвестный тип операции " + walletRequest.getOperationType()));

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(walletRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals("Неизвестный тип операции " + walletRequest.getOperationType(), result.getResolvedException().getMessage()));
    }


    @Test
    void getWallet() {
    }
}