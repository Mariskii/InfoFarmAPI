package com.infofarm.Controllers;

import com.infofarm.Dto.Crop.UpdateCropDTO;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Implementation.CropServiceImplementation;
import com.infofarm.Models.Crop;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/crop")
public class CropController {

    @Autowired
    CropServiceImplementation cropServiceImpl;

    @GetMapping("get-crop/{cropId}")
    public Crop getCropById(@PathVariable Long cropId) throws IdNotFoundException {
        return cropServiceImpl.findById(cropId);
    }

    @PostMapping("create-crop")
    public ResponseEntity<?> saveCrop(@RequestBody Crop crop) {
        cropServiceImpl.save(crop);
        return new ResponseEntity<>(crop, HttpStatus.CREATED);
    }

    @PutMapping("update-crop")
    public ResponseEntity<?> updateCrop(@Valid @RequestBody UpdateCropDTO cropRequestDTO) throws IdNotFoundException {
        cropServiceImpl.update(cropRequestDTO);
        return new ResponseEntity<>(cropRequestDTO, HttpStatus.OK);
    }

    @DeleteMapping("delete-crop/{cropId}")
    public ResponseEntity<?> deleteCrop(@PathVariable Long cropId) throws IdNotFoundException {
        cropServiceImpl.delete(cropId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
