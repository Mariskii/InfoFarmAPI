package com.infofarm.Field.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date planting_date;
    private Date collection_date;
    private double kilo_price;
    private double cost;
    private double kilos;

    private TypeSurface type_surface;

    private double surface;
    private double kilos_surface;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plantation_id", nullable = false)
    @JsonIgnore
    private Plantation plantation;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
    private List<CropNeeds> cropNeeds = new ArrayList<>();
}
