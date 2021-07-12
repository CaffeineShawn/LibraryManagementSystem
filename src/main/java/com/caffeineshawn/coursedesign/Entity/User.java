package com.caffeineshawn.coursedesign.Entity;


import javax.persistence.*;

@Entity(name="user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String role = "defaultUser";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}
