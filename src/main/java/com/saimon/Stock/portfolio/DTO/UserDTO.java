package com.saimon.Stock.portfolio.DTO;

public class UserDTO {
    private String name;
    private String username;
    private boolean admin;

    public UserDTO(String name, String username, boolean admin) {
        this.name = name;
        this.username = username;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return admin;
    }
}
