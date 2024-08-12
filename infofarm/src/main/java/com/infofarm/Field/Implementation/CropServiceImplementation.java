package com.infofarm.Field.Implementation;

import com.infofarm.Field.Dto.Request.Crop.CreateCropDTO;
import com.infofarm.Field.Dto.Request.CropData.CreateCropDataDTO;
import com.infofarm.Field.Dto.Request.CropData.UpdateCropDataDTO;
import com.infofarm.Field.Dto.Response.Crop.CropResponseDTO;
import com.infofarm.Field.Dto.Response.CropData.CropDataResponseDTO;
import com.infofarm.Field.Mapper.CropMapper;
import com.infofarm.Field.Models.CropData;
import com.infofarm.Field.Models.Plantation;
import com.infofarm.Field.Repository.CropDataRepository;
import com.infofarm.Field.Dto.Request.Crop.UpdateCropDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.CreateCropNeedDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.UpdateCropNeedDTO;
import com.infofarm.Field.Repository.PlantationRepository;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Field.Models.Crop;
import com.infofarm.Field.Models.CropNeeds;
import com.infofarm.Field.Repository.CropRepository;
import com.infofarm.Field.Repository.CropNeedsRepository;
import com.infofarm.Field.Service.CropService;
import com.infofarm.Images.Implementation.CloudinaryServiceImpl;
import com.infofarm.common.dto.PageResponseDTO;
import com.infofarm.common.utils.PageMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

@Service
public class CropServiceImplementation implements CropService {

    private static final String FOLDER_NAME = "crops";

    @Autowired
    CropRepository cropRepository;

    @Autowired
    CropNeedsRepository cropNeedsRepository;

    @Autowired
    CropDataRepository cropDataRepository;

    @Autowired
    PlantationRepository plantationRepository;

    @Autowired
    CloudinaryServiceImpl cloudinaryService;

    @Override
    @Transactional
    public CropResponseDTO save(CreateCropDTO cropDTO, MultipartFile file) {

        Crop crop = Crop.builder()
                .cropName(cropDTO.cropName())
                .cropDescription(cropDTO.cropDescription())
                .build();

        if(file != null) {
            String[] imageData = cloudinaryService.uploadFile(file,FOLDER_NAME);
            crop.setImageURL(imageData[0]);
            crop.setImage_public_id(imageData[1]);
        }

        crop = cropRepository.save(crop);

        return CropMapper.createCropResponseDTO(crop);
    }

    @Override
    @Transactional
    public CropResponseDTO update(UpdateCropDTO cropRequestDTO, MultipartFile file) throws IdNotFoundException {

        Crop crop = cropRepository.findById(cropRequestDTO.id())
                .orElseThrow(() -> new IdNotFoundException("Crop not found with id " + cropRequestDTO.id()));

        crop.setCropName(cropRequestDTO.cropName());
        crop.setCropDescription(cropRequestDTO.cropDescription());

        if(file != null) {
            if(crop.getImage_public_id() != null)
                cloudinaryService.deleteFile(crop.getImage_public_id());

            String[] imageData = cloudinaryService.uploadFile(file,FOLDER_NAME);
            crop.setImageURL(imageData[0]);
            crop.setImage_public_id(imageData[1]);
        }

        crop = cropRepository.save(crop);

        return CropMapper.createCropResponseDTO(crop);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        cloudinaryService.deleteFile(cropRepository.findImagePublicIdById(id));
        cropRepository.deleteById(id);

    }

    //todo: Testeando privacidad de enpoints de los usuarios
    @Override
    public CropResponseDTO findById(Long id) throws IdNotFoundException {

        Crop crop = cropRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + id));

        return CropMapper.createCropResponseDTO(crop);
    }

    @Override
    public Page<CropResponseDTO> getCrops(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Crop> cropsPage = cropRepository.findAll(pageable);
        return cropsPage.map(CropMapper::createCropResponseDTO);
    }

    @Override
    public PageResponseDTO<CropResponseDTO> getCropsByName(String name, Pageable pageable) {
        Page<CropResponseDTO> crops = cropRepository.findByCropNameContaining(name, pageable).map(CropMapper::createCropResponseDTO);;

        return PageMapper.createPageResponse(crops);
    }

    @Override
    public Set<CropNeeds> findAllCropNeedsByCropId(Long cropId) throws IdNotFoundException{

        Optional<Crop> crop = cropRepository.findById(cropId);
        if (crop.isPresent()) {
            return cropNeedsRepository.findAllByCrop_Id(cropId);
        }else {
            throw new IdNotFoundException("The crop not found with id: " + cropId);
        }
    }

    @Override
    public void addCropNeed(CreateCropNeedDTO cropNeedsDTO, Long cropId) throws IdNotFoundException {



        CropNeeds cropNeed = CropNeeds.builder()
                .needName(cropNeedsDTO.getNeedName())
                .description(cropNeedsDTO.getDescription())
                .crop(cropDataRepository.findById(cropId).orElseThrow( () ->
                        new IdNotFoundException("The crop not found with id: " + cropId)
                    )
                )
                .build();

        cropNeedsRepository.save(cropNeed);
    }

    @Override
    public void updateCropNeed(UpdateCropNeedDTO updateCropNeeds, Long id) throws IdNotFoundException {
        CropNeeds cropNeeds = cropNeedsRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The need with the id"+id+" doesnt exist"));

        cropNeeds.setNeedName(updateCropNeeds.getNeedName());
        cropNeeds.setDescription(updateCropNeeds.getDescription());

        cropNeedsRepository.save(cropNeeds);
    }

    @Override
    public void deleteCropNeed(Long id) throws IdNotFoundException {
        cropNeedsRepository.deleteById(id);
    }

    @Override
    public Page<CropDataResponseDTO> getCropDataByPlantationId(Long id, int page, int size) throws IdNotFoundException {
        Pageable pageable = PageRequest.of(page,size);

        Page<CropData> cropsPage = cropDataRepository.findByPlantationId(id,pageable);

        return cropsPage.map(CropMapper::createCropDataResponseDTO);
    }

    @Override
    public CropDataResponseDTO getCropDataById(Long id) throws IdNotFoundException {

        CropData cropData = cropDataRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Crop Data with the ID "+id+" not found"));

        return CropMapper.createCropDataResponseDTO(cropData);
    }

    @Override
    public PageResponseDTO<CropDataResponseDTO> getAllCropDataByPlantationId(Long id, Pageable pageable) throws IdNotFoundException {

        Page<CropData> cropData = cropDataRepository.findByPlantationId(id,pageable);

        return PageMapper.createPageResponse(cropData.map(CropMapper::createCropDataResponseDTO));
    }

    @Override
    public CropDataResponseDTO addCropData(CreateCropDataDTO cropDataDTO, Long cropId, Long plantationId) throws IdNotFoundException {
        Crop crop = cropRepository.findById(cropId).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + cropId));

        Plantation plantation = plantationRepository.findById(plantationId).orElseThrow(() -> new IdNotFoundException("The plant not found with id: " + plantationId));

        CropData newData = CropData.builder()
                .kilos(cropDataDTO.kilos())
                .cost(cropDataDTO.cost())
                .kilo_price(cropDataDTO.kiloPrice())
                .planting_date(cropDataDTO.planting_date())
                .collection_date(cropDataDTO.collection_date())
                .plantation(plantation)
                .crop(crop)
                .build();

        return CropMapper.createCropDataResponseDTO(cropDataRepository.save(newData));
    }

    @Override
    public CropDataResponseDTO updateCropData(UpdateCropDataDTO cropData, Long cropId) throws IdNotFoundException {
        CropData actualData =  cropDataRepository.findById(cropData.id()).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + cropData.id()));

        actualData.setCost(cropData.cost());
        actualData.setKilos(cropData.kilos());
        actualData.setKilo_price(cropData.kiloPrice());
        actualData.setPlanting_date(cropData.planting_date());
        actualData.setCollection_date(cropData.collection_date());
        actualData.setCrop(cropRepository.findById(cropId).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + cropId)));
        return CropMapper.createCropDataResponseDTO(cropDataRepository.save(actualData));
    }

    @Override
    public void deleteCropData(Long id) throws IdNotFoundException {
        cropDataRepository.deleteById(id);
    }
}
