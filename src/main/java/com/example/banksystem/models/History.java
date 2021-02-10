package com.example.banksystem.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "wallet_changes")
public class History implements Serializable {
    private int id;
    private int wallet_id;
    private int user_id;
    private int oldBalance_kzt;
    private Double oldBalance_usd;
    private Double oldBalance_eur;
    private int newBalance_kzt;
    private Double newBalance_usd;
    private Double newBalance_eur;
    private Timestamp changedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "wallet_id")
    public int getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(int wallet_id) {
        this.wallet_id = wallet_id;
    }

    @Column(name = "user_id")
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Column(name = "old_balance_kzt")
    public int getOldBalance_kzt() {
        return oldBalance_kzt;
    }

    public void setOldBalance_kzt(int oldBalance_kzt) {
        this.oldBalance_kzt = oldBalance_kzt;
    }

    @Column(name = "old_balance_usd")
    public Double getOldBalance_usd() {
        return oldBalance_usd;
    }

    public void setOldBalance_usd(Double oldBalance_usd) {
        this.oldBalance_usd = oldBalance_usd;
    }

    @Column(name = "old_balance_eur")
    public Double getOldBalance_eur() {
        return oldBalance_eur;
    }

    public void setOldBalance_eur(Double oldBalance_eur) {
        this.oldBalance_eur = oldBalance_eur;
    }

    @Column(name = "new_balance_kzt")
    public int getNewBalance_kzt() {
        return newBalance_kzt;
    }

    public void setNewBalance_kzt(int newBalance_kzt) {
        this.newBalance_kzt = newBalance_kzt;
    }

    @Column(name = "new_balance_usd")
    public Double getNewBalance_usd() {
        return newBalance_usd;
    }

    public void setNewBalance_usd(Double newBalance_usd) {
        this.newBalance_usd = newBalance_usd;
    }

    @Column(name = "new_balance_eur")
    public Double getNewBalance_eur() {
        return newBalance_eur;
    }

    public void setNewBalance_eur(Double newBalance_eur) {
        this.newBalance_eur = newBalance_eur;
    }

    @Column(name = "changed_date")
    public Timestamp getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(Timestamp changedDate) {
        this.changedDate = changedDate;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", wallet_id=" + wallet_id +
                ", user_id=" + user_id +
                ", oldBalance_kzt=" + oldBalance_kzt +
                ", oldBalance_usd=" + oldBalance_usd +
                ", oldBalance_eur=" + oldBalance_eur +
                ", newBalance_kzt=" + newBalance_kzt +
                ", newBalance_usd=" + newBalance_usd +
                ", newBalance_eur=" + newBalance_eur +
                ", changedDate=" + changedDate +
                '}';
    }
}
