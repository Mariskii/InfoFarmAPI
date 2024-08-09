package com.infofarm.Field.Dto.Response.Plantation;

import com.infofarm.Field.Dto.Response.CropData.CropDataResponseDTO;
import com.infofarm.common.dto.PageResponseDTO;
import lombok.Builder;
import org.springframework.data.domain.Page;

@Builder
public record PlantationFullResponseDTO(
        Long id,
        String name,
        String description,
        String location,
        PageResponseDTO cropData
) {

}
