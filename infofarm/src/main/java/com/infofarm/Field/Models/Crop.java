package com.infofarm.Field.Models;

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
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cropName;

    private String cropDescription;

    private String imageURL;

    private String image_public_id;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CropNeeds> cropNeeds = new ArrayList<>();
    //El priblema era del set que habia antes TODO: investigar diferencia entre List y Set

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CropData> cropData = new ArrayList<>();
}
