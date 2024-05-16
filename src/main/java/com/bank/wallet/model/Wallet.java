package com.bank.wallet.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@Entity
@Table(name = "wallets")
@TypeDefs(@TypeDef(name = "uuid", typeClass = org.hibernate.type.UUIDCharType.class))
public class Wallet {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Type("org.hibernate.type.UUIDCharType")
    //@Column(name = "id", updatable = false, nullable = false)

    //@Id
    //@GeneratedValue(generator = "UUID")
    //@GenericGenerator(
      //      name = "UUID",
        //    strategy = "org.hibernate.id.UUIDGenerator"
    //)
    //@Column(name = "id", updatable = false, nullable = false)
    //private UUID id;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private BigDecimal amount;
}
