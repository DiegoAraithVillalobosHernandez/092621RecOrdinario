package com.example.U1ExamenOrdinario_4BVillalobosHernandezDiegoAraith.database;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String dateRegistered;
    private int status;

    public User() {
    }

    public User(String name, String lastname, String email, String password, int status) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }
    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
