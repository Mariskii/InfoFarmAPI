package com.infofarm.Orders.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infofarm.Field.Models.CropData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_crop_data")
@Builder
public class OrderCropData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private CropOrder cropOrder;

    @ManyToOne
    //@MapsId("cropDataId")
    @JoinColumn(name = "crop_data_id")
    private CropData cropData;

    private double kilos;
    private double price;
}
