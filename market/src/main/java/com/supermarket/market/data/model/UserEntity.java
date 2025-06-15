package com.supermarket.market.data.model;

import jakarta.persistence.*;

@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String username;
    public Double credits;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getCreditos() {
        return credits;
    }

    public void setCreditos(Double creditos) {
        this.credits = creditos;
    }

}
