package com.infofarm.Field.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infofarm.Bussines.Models.Bussines;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plantation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;

    @OneToMany(mappedBy = "plantation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CropData> cropData = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bussines_id")
    @JsonIgnore
    private Bussines bussines;

    //TODO: IMPLEMENTAR SIGPAC
}
