package com.infofarm.Field.Mapper;

import com.infofarm.Field.Dto.Response.CropData.CropDataResponseDTO;
import com.infofarm.Field.Dto.Response.Plantation.PlantationFullResponseDTO;
import com.infofarm.Field.Dto.Response.Plantation.PlantationResponseDTO;
import com.infofarm.Field.Models.Crop;
import com.infofarm.Field.Models.Plantation;
import com.infofarm.common.utils.PageMapper;
import org.springframework.data.domain.*;

import java.util.List;

public class PlantationMapper {
    public static PlantationResponseDTO createPlantationResponseDTO(Plantation plantation) {
        return PlantationResponseDTO.builder()
                .id(plantation.getId())
                .name(plantation.getName())
                .description(plantation.getDescription())
                .location(plantation.getLocation())
                .build();
    }

    public static PlantationFullResponseDTO createFullPlantationResponseDTO(Plantation plantation, Page<CropDataResponseDTO>cropDataResponseDTOS) {

        return PlantationFullResponseDTO.builder()
                .id(plantation.getId())
                .name(plantation.getName())
                .description(plantation.getDescription())
                .location(plantation.getLocation())
                .cropData(PageMapper.createPageResponse(cropDataResponseDTOS))
                .build();
    }
}
