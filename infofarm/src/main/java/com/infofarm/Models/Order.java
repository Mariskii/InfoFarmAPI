package com.infofarm.Models;

import com.infofarm.Field.Models.Crop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date date_order;
    private Date date_arrive;
    private double quantity;

    //Empresa que pide
    @ManyToOne
    @JoinColumn(name = "id_bussines", nullable = false)
    private Costumer costumer;

    //Lo que pide
    @ManyToMany
    @JoinTable(
            name = "orderedCrop",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "crop_id"))
    private Set<Crop> crops;
}
