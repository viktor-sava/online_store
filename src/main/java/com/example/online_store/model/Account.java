package com.example.online_store.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Account implements Serializable {

    private int id;

    private String surname;

    private String name;

    private String patronymic;

    private String email;

    private String phone;

    private String password;

    private Timestamp createDate;

    private boolean blocked;

    private boolean admin;

    public Account() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public static final class AccountBuilder {

        private final Account account;

        private AccountBuilder() {
            account = new Account();
        }

        public AccountBuilder id(int id) {
            account.setId(id);
            return this;
        }

        public AccountBuilder surname(String surname) {
            account.setSurname(surname);
            return this;
        }

        public AccountBuilder name(String name) {
            account.setName(name);
            return this;
        }

        public AccountBuilder patronymic(String patronymic) {
            account.setPatronymic(patronymic);
            return this;
        }

        public AccountBuilder email(String email) {
            account.setEmail(email);
            return this;
        }

        public AccountBuilder phone(String phone) {
            account.setPhone(phone);
            return this;
        }

        public AccountBuilder password(String password) {
            account.setPassword(password);
            return this;
        }

        public AccountBuilder createDate(Timestamp createDate) {
            account.setCreateDate(createDate);
            return this;
        }

        public AccountBuilder blocked(boolean blocked) {
            account.setBlocked(blocked);
            return this;
        }

        public AccountBuilder admin(boolean admin) {
            account.setAdmin(admin);
            return this;
        }

        public Account build() {
            return account;
        }
    }
}
