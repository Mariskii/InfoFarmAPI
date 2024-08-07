package com.infofarm.Field.Controllers;

import com.infofarm.Field.Dto.Request.Plantation.RequestPlantationDTO;
import com.infofarm.Field.Dto.Response.Plantation.PlantationResponseDTO;
import com.infofarm.Field.Models.Plantation;
import com.infofarm.Field.Service.PlantationService;
import com.infofarm.Exception.Errors.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("get-all-plantations")
    public Page<PlantationResponseDTO> getAllPlantations(@RequestParam(required = false, defaultValue = "0") int page,
                                                         @RequestParam(required = false, defaultValue = "10") int size) {
        return plantationService.getAllPlantations(page, size);
    }

    @PostMapping("add-plantation/bussines/{bussinesId}")
    public ResponseEntity<?> addPlantation(@RequestBody RequestPlantationDTO createPlantationDTO, @PathVariable Long bussinesId) throws IdNotFoundException {
        return ResponseEntity.ok().body(plantationService.addPlantation(createPlantationDTO, bussinesId));
    }

    @PutMapping("edit-plantation")
    public ResponseEntity<Plantation> updatePlantation(@RequestBody Plantation updatedPlantation) throws IdNotFoundException {
        return ResponseEntity.ok().body(plantationService.updatePlantation(updatedPlantation));
    }

    @DeleteMapping("delete-plantation/{plantationId}")
    public ResponseEntity<?> deletePlantation(@PathVariable Long plantationId) throws IdNotFoundException {
        plantationService.deletePlantation(plantationId);
        return ResponseEntity.ok().build();
    }

}
