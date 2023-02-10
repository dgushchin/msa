package com.example.demo1.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
    @Id
    private int id;
    @Column
    private String name;
}
