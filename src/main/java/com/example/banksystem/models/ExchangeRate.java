package com.example.banksystem.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "exchange_rate")
public class ExchangeRate implements Serializable {
    private int id;
    private String currency;
    private int value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", value=" + value +
                '}';
    }
}
