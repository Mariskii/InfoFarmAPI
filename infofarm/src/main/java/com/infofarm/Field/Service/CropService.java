package com.infofarm.Field.Service;

import com.infofarm.Field.Dto.Request.Crop.CreateCropDTO;
import com.infofarm.Field.Dto.Request.CropData.CreateCropDataDTO;
import com.infofarm.Field.Dto.Request.CropData.UpdateCropDataDTO;
import com.infofarm.Field.Dto.Response.Crop.CropResponseDTO;
import com.infofarm.Field.Dto.Response.CropData.CropDataResponseDTO;
import com.infofarm.Field.Dto.Request.Crop.UpdateCropDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.CreateCropNeedDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.UpdateCropNeedDTO;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Field.Models.CropNeeds;
import com.infofarm.common.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface CropService {

    CropResponseDTO save(CreateCropDTO crop, MultipartFile file);

    CropResponseDTO update(UpdateCropDTO crop, MultipartFile file) throws IdNotFoundException;

    void delete(Long id);

    CropResponseDTO findById(Long id) throws IdNotFoundException;

    Page<CropResponseDTO> getCrops(int page, int size);

    PageResponseDTO<CropResponseDTO> getCropsByName(String name , Pageable pageable);

    Set<CropNeeds> findAllCropNeedsByCropId(Long cropId) throws IdNotFoundException;

    void addCropNeed(CreateCropNeedDTO cropNeeds, Long id) throws IdNotFoundException;

    void updateCropNeed(UpdateCropNeedDTO cropNeeds, Long id) throws IdNotFoundException;

    void deleteCropNeed(Long id) throws IdNotFoundException;

    Page<CropDataResponseDTO> getCropDataByPlantationId(Long id, int page, int size) throws IdNotFoundException;

    CropDataResponseDTO getCropDataById(Long id) throws IdNotFoundException;

    PageResponseDTO<CropDataResponseDTO> getAllCropDataByPlantationId(Long id, Pageable pageable) throws IdNotFoundException;

    CropDataResponseDTO addCropData(CreateCropDataDTO cropData, Long id, Long plantationId) throws IdNotFoundException;

    CropDataResponseDTO updateCropData(UpdateCropDataDTO cropData, Long cropId) throws IdNotFoundException;

    void deleteCropData(Long id) throws IdNotFoundException;
}
