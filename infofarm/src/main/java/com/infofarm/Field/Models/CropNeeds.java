package com.infofarm.Field.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CropNeeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String needName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_crop_data", nullable = false)
    @JsonIgnore
    private CropData crop;
}
