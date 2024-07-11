package com.infofarm.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CropNeeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String needName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_crop", nullable = false)
    private Crop crop;
}
