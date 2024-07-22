package com.infofarm.Field.Implementation;

import com.infofarm.Field.Dto.Request.CropData.RequestCropDataDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CropServiceImplementation implements CropService {

    @Autowired
    CropRepository cropRepository;

    @Autowired
    CropNeedsRepository cropNeedsRepository;

    @Autowired
    CropDataRepository cropDataRepository;

    @Autowired
    PlantationRepository plantationRepository;

    @Override
    public void save(Crop crop) {
        cropRepository.save(crop);
    }

    @Override
    public void update(UpdateCropDTO cropRequestDTO) throws IdNotFoundException {

        Optional<Crop> crop = cropRepository.findById(cropRequestDTO.id());

        if (crop.isPresent()) {
            Crop c = crop.get();
            c.setCropName(cropRequestDTO.cropName());
            c.setCropDescription(cropRequestDTO.cropDescription());
            c.setCropImage(cropRequestDTO.cropImage());

            cropRepository.save(c);
        } else {
            throw new IdNotFoundException("Crop not found with id " + cropRequestDTO.id());
        }

    }

    @Override
    public void delete(Long id) throws IdNotFoundException {
        if (cropRepository.existsById(id)) {
            cropRepository.deleteById(id);
        } else {
            throw new IdNotFoundException("Crop not found with id " + id);
        }
    }

    @Override
    public Crop findById(Long id) throws IdNotFoundException {
        return cropRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + id));
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
}
