package com.infofarm.Crop.Implementation;

import com.infofarm.Dto.Request.Crop.UpdateCropDTO;
import com.infofarm.Dto.Request.CropNeeds.CreateCropNeedDTO;
import com.infofarm.Dto.Request.CropNeeds.UpdateCropNeedDTO;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Crop.Models.Crop;
import com.infofarm.Crop.Models.CropNeeds;
import com.infofarm.Crop.Repository.CropRepository;
import com.infofarm.Crop.Repository.CropNeedsRepository;
import com.infofarm.Crop.Service.CropService;
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

    @Override
    public void save(Crop crop) {
        cropRepository.save(crop);
    }

    @Override
    public void update(UpdateCropDTO cropRequestDTO) throws IdNotFoundException {

        Optional<Crop> crop = cropRepository.findById(cropRequestDTO.getId());

        if (crop.isPresent()) {
            Crop c = crop.get();
            c.setCropName(cropRequestDTO.getCropName());
            c.setCropDescription(cropRequestDTO.getCropDescription());
            c.setCropImage(cropRequestDTO.getCropImage());

            cropRepository.save(c);
        } else {
            throw new IdNotFoundException("Crop not found with id " + cropRequestDTO.getId());
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
}
