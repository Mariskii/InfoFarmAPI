package com.infofarm.Field.Implementation;

import com.infofarm.Bussines.Models.Bussines;
import com.infofarm.Bussines.Repository.BussinesRepository;
import com.infofarm.Field.Dto.Request.Plantation.RequestPlantationDTO;
import com.infofarm.Field.Models.Plantation;
import com.infofarm.Field.Repository.PlantationRepository;
import com.infofarm.Field.Service.PlantationService;
import com.infofarm.Exception.Errors.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantationServiceImplementation implements PlantationService {

    @Autowired
    PlantationRepository plantationRepository;

    @Autowired
    BussinesRepository bussinesRepository;

    @Override
    public Plantation getPlantation(Long id) throws IdNotFoundException {
        return plantationRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The crop not found with id: " + id));
    }

    @Override
    public void addPlantation(RequestPlantationDTO plantation, Long bussinesId) throws IdNotFoundException {

        Bussines bussines = bussinesRepository.findById(bussinesId).orElseThrow(() -> new IdNotFoundException("The bussines not found with id: " + bussinesId));

        Plantation newPlantation = Plantation.builder()
                .name(plantation.getName())
                .description(plantation.getDescription())
                .bussines(bussines)
                .build();

        plantationRepository.save(newPlantation);
    }

    //todo: implementar actualización
    @Override
    public void updatePlantation(RequestPlantationDTO updatePlantation, Long plantationId) throws IdNotFoundException {
        Plantation plantation = plantationRepository.findById(plantationId).orElseThrow(() -> new IdNotFoundException("The plant not found with id: " + plantationId));

        plantation.setName(updatePlantation.getName());
        plantation.setDescription(updatePlantation.getDescription());

        plantationRepository.save(plantation);
    }

    @Override
    public void deletePlantation(Long id) {
        plantationRepository.deleteById(id);
    }
}
