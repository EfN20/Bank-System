package com.example.banksystem.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "wallets")
public class Wallet implements Serializable {
    private int id;
    private User user;
    private String serialNumber;
    private int balance_kzt;
    private double balance_usd;
    private double balance_eur;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "serial_number", updatable = false)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Column(name = "balance_kzt")
    public int getBalance_kzt() {
        return balance_kzt;
    }

    public void setBalance_kzt(int balance_kzt) {
        this.balance_kzt = balance_kzt;
    }

    @Column(name = "balance_usd")
    public double getBalance_usd() {
        return balance_usd;
    }

    public void setBalance_usd(double balance_usd) {
        this.balance_usd = balance_usd;
    }

    @Column(name = "balance_eur")
    public double getBalance_eur() {
        return balance_eur;
    }

    public void setBalance_eur(double balance_eur) {
        this.balance_eur = balance_eur;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", user=" + user +
                ", serialNumber='" + serialNumber + '\'' +
                ", balance_kzt=" + balance_kzt +
                ", balance_usd=" + balance_usd +
                ", balance_eur=" + balance_eur +
                '}';
    }
}
