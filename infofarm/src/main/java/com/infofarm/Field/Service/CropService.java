package com.infofarm.Field.Service;

import com.infofarm.Field.Dto.Request.Crop.CreateCropDTO;
import com.infofarm.Field.Dto.Request.CropData.RequestCropDataDTO;
import com.infofarm.Field.Dto.Response.Crop.CropResponseDTO;
import com.infofarm.Field.Models.CropData;
import com.infofarm.Field.Dto.Request.Crop.UpdateCropDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.CreateCropNeedDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.UpdateCropNeedDTO;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Field.Models.Crop;
import com.infofarm.Field.Models.CropNeeds;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface CropService {

    CropResponseDTO save(CreateCropDTO crop, MultipartFile file);

    CropResponseDTO update(UpdateCropDTO crop, MultipartFile file) throws IdNotFoundException;

    void delete(Long id);

    CropResponseDTO findById(Long id) throws IdNotFoundException;

    Set<CropNeeds> findAllCropNeedsByCropId(Long cropId) throws IdNotFoundException;

    void addCropNeed(CreateCropNeedDTO cropNeeds, Long id) throws IdNotFoundException;

    void updateCropNeed(UpdateCropNeedDTO cropNeeds, Long id) throws IdNotFoundException;

    void deleteCropNeed(Long id) throws IdNotFoundException;

    Set<CropData> getCropDataByCropId(Long id) throws IdNotFoundException;

    void addCropData(RequestCropDataDTO cropData, Long id, Long plantationId) throws IdNotFoundException;

    void updateCropData(RequestCropDataDTO cropData, Long id) throws IdNotFoundException;

    void deleteCropData(Long id) throws IdNotFoundException;
}
