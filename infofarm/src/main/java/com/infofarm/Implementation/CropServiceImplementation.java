package com.infofarm.Implementation;

import com.infofarm.Dto.Crop.UpdateCropDTO;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Models.Crop;
import com.infofarm.Repository.CropRepository;
import com.infofarm.Service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CropServiceImplementation implements CropService {

    @Autowired
    CropRepository cropRepository;

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
}
