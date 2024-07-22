package com.infofarm.Orders.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CropOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date orderDate;

    private Date deliveryDate;

    private boolean paid;

    private boolean delivered;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Costumer customer;

    @OneToMany(mappedBy = "cropOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderCropData> products = new ArrayList<>();
}
