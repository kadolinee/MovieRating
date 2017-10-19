package com.epam.kinorating.entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable{
    private int id;
    private String name;
    private double rating = 10.0;
    private String mail;
    private String password;
    private Date dateCreate;
    private String salt;
    private boolean banned;
    int roleId = 2;

    public User() {}

    public User(int id, String name, double rating, String mail, Date dateCreate) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.mail = mail;
        this.dateCreate = dateCreate;
    }

    public User(String name, String mail, String password, Date dateCreate) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.dateCreate = dateCreate;
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

    public double getRating() {
            return rating;
        }

    public void setRating(double rating) {
            this.rating = rating;
        }

    public String getMail() {
            return mail;
        }

    public void setMail(String mail) {
            this.mail = mail;
        }

    public String getPassword() {
            return password;
        }

    public void setPassword(String password) {
            this.password = password;
        }

    public Date getDateCreate() {
            return dateCreate;
        }

    public void setDateCreate(Date dateCreate) {
            this.dateCreate = dateCreate;
        }

    public String getSalt() {
            return salt;
        }

    public void setSalt(String salt) {
            this.salt = salt;
        }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
