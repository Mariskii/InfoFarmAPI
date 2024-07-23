package com.infofarm.Field.Implementation;

import com.infofarm.Field.Dto.Request.Crop.CreateCropDTO;
import com.infofarm.Field.Dto.Request.CropData.RequestCropDataDTO;
import com.infofarm.Field.Dto.Response.Crop.CropResponseDTO;
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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

        return createCropResponseDTO(crop);
    }

    @Override
    @Transactional
    public CropResponseDTO update(UpdateCropDTO cropRequestDTO, MultipartFile file) throws IdNotFoundException {

        Crop crop = cropRepository.findById(cropRequestDTO.id())
                .orElseThrow(() -> new IdNotFoundException("Crop not found with id " + cropRequestDTO.id()));

        crop.setCropName(cropRequestDTO.cropName());
        crop.setCropDescription(cropRequestDTO.cropDescription());

        if(file != null) {
            cloudinaryService.deleteFile(crop.getImage_public_id());
            String[] imageData = cloudinaryService.uploadFile(file,FOLDER_NAME);
            crop.setImageURL(imageData[0]);
            crop.setImage_public_id(imageData[1]);
        }

        crop = cropRepository.save(crop);

        return createCropResponseDTO(crop);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        cloudinaryService.deleteFile(cropRepository.findImagePublicIdById(id));
        cropRepository.deleteById(id);

    }

    //TODO: Devolver DTO sin el id publico de la imagen
    @Override
    public CropResponseDTO findById(Long id) throws IdNotFoundException {

        Crop crop = cropRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + id));

        return createCropResponseDTO(crop);
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
        Crop crop = cropRepository.findById(cropId).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + cropId));

        CropNeeds cropNeed = CropNeeds.builder()
                .needName(cropNeedsDTO.getNeedName())
                .description(cropNeedsDTO.getDescription())
                .crop(crop)
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
    public Set<CropData> getCropDataByCropId(Long id) throws IdNotFoundException {
        return cropDataRepository.findByCrop_Id(id);
    }

    @Override
    public void addCropData(RequestCropDataDTO cropDataDTO, Long cropId, Long plantationId) throws IdNotFoundException {
        Crop crop = cropRepository.findById(cropId).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + cropId));

        Plantation plantation = plantationRepository.findById(plantationId).orElseThrow(() -> new IdNotFoundException("The plant not found with id: " + plantationId));

        CropData newData = CropData.builder()
                .kilos(cropDataDTO.getKilos())
                .cost(cropDataDTO.getCost())
                .kilo_price(cropDataDTO.getKiloPrice())
                .planting_date(cropDataDTO.getPlanting_date())
                .collection_date(cropDataDTO.getCollection_date())
                .plantation(plantation)
                .crop(crop)
                .build();

        cropDataRepository.save(newData);
    }

    @Override
    public void updateCropData(RequestCropDataDTO cropData, Long id) throws IdNotFoundException {
        CropData actualData =  cropDataRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + id));

        actualData.setCost(cropData.getCost());
        actualData.setKilos(cropData.getKilos());
        actualData.setKilo_price(cropData.getKiloPrice());
        actualData.setPlanting_date(cropData.getPlanting_date());
        actualData.setCollection_date(cropData.getCollection_date());
        cropDataRepository.save(actualData);
    }

    @Override
    public void deleteCropData(Long id) throws IdNotFoundException {
        cropDataRepository.deleteById(id);
    }

    private CropResponseDTO createCropResponseDTO(Crop crop) {
        return CropResponseDTO.builder()
                .cropName(crop.getCropName())
                .cropDescription(crop.getCropDescription())
                .imageURL(crop.getImageURL())
                .build();
    }
}
