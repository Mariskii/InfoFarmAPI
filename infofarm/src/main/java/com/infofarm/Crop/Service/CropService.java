package com.infofarm.Crop.Service;

import com.infofarm.Dto.Request.Crop.UpdateCropDTO;
import com.infofarm.Dto.Request.CropNeeds.CreateCropNeedDTO;
import com.infofarm.Dto.Request.CropNeeds.UpdateCropNeedDTO;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Crop.Models.Crop;
import com.infofarm.Crop.Models.CropNeeds;

import java.util.Set;

public interface CropService {

    void save(Crop crop);

    void update(UpdateCropDTO crop) throws IdNotFoundException;

    void delete(Long id) throws IdNotFoundException;

    Crop findById(Long id) throws IdNotFoundException;

    Set<CropNeeds> findAllCropNeedsByCropId(Long cropId) throws IdNotFoundException;

    void addCropNeed(CreateCropNeedDTO cropNeeds, Long id) throws IdNotFoundException;

    void updateCropNeed(UpdateCropNeedDTO cropNeeds, Long id) throws IdNotFoundException;

    void deleteCropNeed(Long id) throws IdNotFoundException;
}
