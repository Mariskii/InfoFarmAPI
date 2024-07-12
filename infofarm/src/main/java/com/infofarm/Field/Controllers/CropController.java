package com.infofarm.Field.Controllers;

import com.infofarm.Field.Dto.Request.CropData.RequestCropDataDTO;
import com.infofarm.Field.Models.CropData;
import com.infofarm.Field.Dto.Request.Crop.UpdateCropDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.CreateCropNeedDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.UpdateCropNeedDTO;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Field.Implementation.CropServiceImplementation;
import com.infofarm.Field.Models.Crop;
import com.infofarm.Field.Models.CropNeeds;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @PostMapping("add-cropNeed/{cropId}")
    public ResponseEntity<?> addCropNeed(@PathVariable Long cropId, @Valid @RequestBody CreateCropNeedDTO createCropNeedDTO) throws IdNotFoundException {
        cropServiceImpl.addCropNeed(createCropNeedDTO, cropId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("get-crop-needs/{cropId}")
    public Set<CropNeeds> getCropNeedById(@PathVariable Long cropId) throws IdNotFoundException{
        return cropServiceImpl.findAllCropNeedsByCropId(cropId);
    }

    @PutMapping("update-crop-need/{id}")
    public ResponseEntity<?> updateCropNeed(@Valid @RequestBody UpdateCropNeedDTO cropNeeds, @PathVariable Long id) throws IdNotFoundException {
        cropServiceImpl.updateCropNeed(cropNeeds, id);
        return new ResponseEntity<>(cropNeeds, HttpStatus.OK);
    }

    @DeleteMapping("delete-crop-need/{id}")
    public ResponseEntity<?>  deleteCropNeedById(@PathVariable Long id) throws IdNotFoundException {
        cropServiceImpl.deleteCropNeed(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{cropId}/crop-data")
    public Set<CropData> getCropDataByCropId(@PathVariable Long cropId) throws IdNotFoundException {
        return cropServiceImpl.getCropDataByCropId(cropId);
    }

    @PutMapping("crop-data/update/crop/{cropId}")
    public ResponseEntity<?> updateCropData(@Valid @RequestBody RequestCropDataDTO cropDataDTO, @PathVariable Long cropId) throws IdNotFoundException {
        cropServiceImpl.updateCropData(cropDataDTO, cropId);
        return new ResponseEntity<>(cropDataDTO, HttpStatus.OK);
    }

    @DeleteMapping("deleteData/{id}")
    public ResponseEntity<?> deleteData(@RequestParam Long id) throws IdNotFoundException {
        cropServiceImpl.deleteCropData(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{cropId}/add-data/plantation/{plantationId}")
    public ResponseEntity<?> addCropData(@RequestBody RequestCropDataDTO cropDataDTO,@PathVariable Long cropId,@PathVariable Long plantationId) throws IdNotFoundException {
        cropServiceImpl.addCropData(cropDataDTO,cropId,plantationId);
        return new ResponseEntity<>(cropDataDTO, HttpStatus.OK);
    }
}
