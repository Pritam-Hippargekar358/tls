package com.client.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
    private String userName;
    private List<String> roles;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
