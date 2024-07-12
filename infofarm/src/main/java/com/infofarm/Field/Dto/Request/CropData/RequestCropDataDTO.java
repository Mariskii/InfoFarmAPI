package com.infofarm.Field.Dto.Request.CropData;

import lombok.Data;

import java.util.Date;

@Data
public class RequestCropDataDTO {
    //TODO: implementar restricciones
    private Date planting_date;
    private Date collection_date;
    private double sale_price;
    private double cost;
    private double kilos;
}
