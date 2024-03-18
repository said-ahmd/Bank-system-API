package org.fawry.bankapisystem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "accoutns")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "card_number",nullable = false,unique = true)
    private String cardNumber;

    @Column(name = "cvv",nullable = false)
    private String CVV;

    @Column(name = "balance",nullable = false)
    private BigDecimal balance;

    @Column(name = "status",nullable = false)
    private Boolean status;

    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
}
