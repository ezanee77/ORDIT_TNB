package com.tnb.ordit;

public class User {

    public String name, staffID, email;

    public User() {

    }

    public User(String name, String staffID, String email) {
        this.name = name;
        this.staffID = staffID;
        this.email = email;
    }


    public String getName() {

        return name;
    }

    public String getStaffID() {

        return staffID;
    }

    public String getEmail() {
        return email;
    }
}