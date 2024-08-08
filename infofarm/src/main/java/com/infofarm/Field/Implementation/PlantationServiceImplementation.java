package com.infofarm.Field.Implementation;

import com.infofarm.Bussines.Models.Bussines;
import com.infofarm.Bussines.Repository.BussinesRepository;
import com.infofarm.Field.Dto.Request.Plantation.RequestPlantationDTO;
import com.infofarm.Field.Dto.Response.Plantation.PlantationResponseDTO;
import com.infofarm.Field.Models.Crop;
import com.infofarm.Field.Models.CropData;
import com.infofarm.Field.Models.Plantation;
import com.infofarm.Field.Repository.PlantationRepository;
import com.infofarm.Field.Service.PlantationService;
import com.infofarm.Exception.Errors.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlantationServiceImplementation implements PlantationService {

    @Autowired
    PlantationRepository plantationRepository;

    @Autowired
    BussinesRepository bussinesRepository;

    @Override
    public Plantation getPlantation(Long id) throws IdNotFoundException {
        return plantationRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The plantation not found with id: " + id));
    }

    @Override
    public Page<PlantationResponseDTO> getAllPlantations(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Plantation> plantationsPage = plantationRepository.findAll(pageable);
        return plantationsPage.map(this::createPlantationResponseDTO);
    }

    @Override
    public PlantationResponseDTO addPlantation(RequestPlantationDTO plantation, Long bussinesId) throws IdNotFoundException {

        Bussines bussines = bussinesRepository.findById(bussinesId).orElseThrow(() -> new IdNotFoundException("The bussines not found with id: " + bussinesId));

        Plantation newPlantation = Plantation.builder()
                .name(plantation.getName())
                .description(plantation.getDescription())
                .location(plantation.getLocation())
                .bussines(bussines)
                .build();

        return createPlantationResponseDTO(plantationRepository.save(newPlantation));
    }

    @Override
    public Plantation updatePlantation(Plantation updatePlantation) throws IdNotFoundException {
        Plantation plantation = plantationRepository.findById(updatePlantation.getId()).orElseThrow(() -> new IdNotFoundException("The plant not found with id: " + updatePlantation.getId()));

        plantation.setName(updatePlantation.getName());
        plantation.setLocation(updatePlantation.getLocation());
        plantation.setDescription(updatePlantation.getDescription());

        return plantationRepository.save(plantation);
    }

    @Override
    public void deletePlantation(Long id) {
        plantationRepository.deleteById(id);
    }

    PlantationResponseDTO createPlantationResponseDTO(Plantation plantation) {
        return PlantationResponseDTO.builder()
                .id(plantation.getId())
                .name(plantation.getName())
                .description(plantation.getDescription())
                .location(plantation.getLocation())
                .build();
    }
}
