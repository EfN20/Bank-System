package com.example.banksystem.models;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private int id;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 10, max = 12, message = "Name should be between 10 and 12 numbers")
    private String phone;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    private Role role;
//    private Set<Wallet> wallets;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "phone_number")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    @OneToMany(mappedBy = "user")
//    public Set<Wallet> getWallets() {
//        return wallets;
//    }
//
//    public void setWallets(Set<Wallet> wallets) {
//        this.wallets = wallets;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
