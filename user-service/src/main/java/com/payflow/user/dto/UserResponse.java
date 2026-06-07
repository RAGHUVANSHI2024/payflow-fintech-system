package com.payflow.user.dto;

import com.payflow.user.enums.Status;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public void setStatus(Status status){
        this.status=status;
    }
    public Status getStatus(){
        return status;
    }
}
