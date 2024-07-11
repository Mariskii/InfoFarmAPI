package com.infofarm.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Bussines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String logoURL;

    private Date creationDate;

    @OneToMany(mappedBy = "bussines", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tasks> tasks = new HashSet<>();
}
