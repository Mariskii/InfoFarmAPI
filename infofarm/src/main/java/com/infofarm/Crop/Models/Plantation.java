package com.infofarm.Crop.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Plantation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;

    @OneToMany(mappedBy = "plantation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CropData> cropData = new HashSet<>();

    //TODO: IMPLEMENTAR SIGPAC
}
