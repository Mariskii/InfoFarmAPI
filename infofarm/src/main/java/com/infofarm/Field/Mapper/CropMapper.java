package com.infofarm.Field.Mapper;

import com.infofarm.Field.Dto.Response.Crop.CropResponseDTO;
import com.infofarm.Field.Dto.Response.CropData.CropDataResponseDTO;
import com.infofarm.Field.Dto.Response.CropNeed.CropNeedResponseDTO;
import com.infofarm.Field.Models.Crop;
import com.infofarm.Field.Models.CropData;
import com.infofarm.Field.Models.CropNeeds;

public class CropMapper {

    public static CropResponseDTO createCropResponseDTO(Crop crop) {
        return CropResponseDTO.builder()
                .id(crop.getId())
                .cropName(crop.getCropName())
                .cropDescription(crop.getCropDescription())
                .cropImage(crop.getImageURL())
                .build();
    }

    public static CropNeedResponseDTO createCropNeedResponseDTO(CropNeeds cropNeed) {
        return CropNeedResponseDTO.builder()
                .needName(cropNeed.getNeedName())
                .description(cropNeed.getDescription())
                .build();
    }

    public static CropDataResponseDTO createCropDataResponseDTO(CropData cropData) {
        return CropDataResponseDTO.builder()
                .id(cropData.getId())
                .kilo_price(cropData.getKilo_price())
                .kilos(cropData.getKilos())
                .cost(cropData.getCost())
                .collection_date(cropData.getCollection_date())
                .planting_date(cropData.getPlanting_date())
                .crop(createCropResponseDTO(cropData.getCrop()))
                .needs(cropData.getCropNeeds()
                        .stream()
                        .map(CropMapper::createCropNeedResponseDTO).toList()
                )
                .build();
    }
}
