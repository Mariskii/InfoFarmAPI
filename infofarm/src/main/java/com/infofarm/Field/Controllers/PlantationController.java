package com.infofarm.Field.Controllers;

import com.infofarm.Field.Dto.Request.Plantation.RequestPlantationDTO;
import com.infofarm.Field.Models.Plantation;
import com.infofarm.Field.Service.PlantationService;
import com.infofarm.Exception.Errors.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/plantation")
public class PlantationController {

    @Autowired
    PlantationService plantationService;

    @GetMapping("get-plantation/{plantationId}")
    public Plantation getPlantation(@PathVariable Long plantationId) throws IdNotFoundException {
        return plantationService.getPlantation(plantationId);
    }

    @PostMapping("add-plantation/bussines/{bussinesId}")
    public ResponseEntity<?> addPlantation(@RequestBody RequestPlantationDTO createPlantationDTO, @PathVariable Long bussinesId) throws IdNotFoundException {
        plantationService.addPlantation(createPlantationDTO, bussinesId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete-plantation/{plantationId}")
    public ResponseEntity<?> deletePlantation(@PathVariable Long plantationId) throws IdNotFoundException {
        plantationService.deletePlantation(plantationId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{plantationId}/update")
    public ResponseEntity<?> updatePlantation(@RequestBody RequestPlantationDTO plantationDTO, @PathVariable Long plantationId) throws IdNotFoundException {
        plantationService.updatePlantation(plantationDTO, plantationId);
        return ResponseEntity.ok().build();
    }

}
