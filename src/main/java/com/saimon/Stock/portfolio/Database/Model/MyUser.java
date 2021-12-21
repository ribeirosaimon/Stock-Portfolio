package com.saimon.Stock.portfolio.Database.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Column(unique = true)
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    private boolean admin;

    public MyUser() {

    }

    public MyUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyUser)) return false;
        MyUser myUser = (MyUser) o;
        return isAdmin() == myUser.isAdmin() && Objects.equals(id, myUser.id) && Objects.equals(getName(), myUser.getName()) && Objects.equals(getLogin(), myUser.getLogin()) && Objects.equals(getPassword(), myUser.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getLogin(), getPassword(), isAdmin());
    }
}
