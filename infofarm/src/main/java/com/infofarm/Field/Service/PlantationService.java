package com.infofarm.Field.Service;

import com.infofarm.Field.Dto.Request.Plantation.RequestPlantationDTO;
import com.infofarm.Field.Dto.Response.Plantation.PlantationResponseDTO;
import com.infofarm.Field.Models.CropData;
import com.infofarm.Field.Models.Plantation;
import com.infofarm.Exception.Errors.IdNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlantationService {

    Plantation getPlantation(Long id) throws IdNotFoundException;

    Page<PlantationResponseDTO> getAllPlantations(int page, int size);

    PlantationResponseDTO addPlantation(RequestPlantationDTO plantation, Long bussinesId) throws IdNotFoundException;

    void updatePlantation(RequestPlantationDTO plantation, Long plantationId) throws IdNotFoundException;

    void deletePlantation(Long id);
}
