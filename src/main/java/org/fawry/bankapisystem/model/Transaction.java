package org.fawry.bankapisystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.fawry.bankapisystem.model.enumTypes.TransactionType;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "transaction_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column(name = "amount",nullable = false)
    private double amount;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "account_id")
    Account account;


}
