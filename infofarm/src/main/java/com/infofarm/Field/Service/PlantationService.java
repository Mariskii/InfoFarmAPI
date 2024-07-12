package com.infofarm.Field.Service;

import com.infofarm.Field.Dto.Request.Plantation.RequestPlantationDTO;
import com.infofarm.Field.Models.Plantation;
import com.infofarm.Exception.Errors.IdNotFoundException;

public interface PlantationService {

    Plantation getPlantation(Long id) throws IdNotFoundException;

    void addPlantation(RequestPlantationDTO plantation, Long bussinesId) throws IdNotFoundException;

    void updatePlantation(RequestPlantationDTO plantation, Long plantationId) throws IdNotFoundException;

    void deletePlantation(Long id);
}
