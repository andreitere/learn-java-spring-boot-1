package com.learnspring.tere.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
