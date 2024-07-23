package com.infofarm.Bussines.Models;

import com.infofarm.Field.Models.Plantation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bussines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String logoURL;

    private Date creationDate;

    private String image_public_id;

    //TODO: Añadir relacion entre negocio y plantaciones
    @OneToMany(mappedBy = "bussines", cascade = CascadeType.ALL)
    private List<Plantation> plantations = new ArrayList<>();
}
