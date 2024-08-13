package com.infofarm.Field.Controllers;

import com.infofarm.Field.Dto.Request.Crop.CreateCropDTO;
import com.infofarm.Field.Dto.Request.CropData.CreateCropDataDTO;
import com.infofarm.Field.Dto.Request.CropData.UpdateCropDataDTO;
import com.infofarm.Field.Dto.Response.Crop.CropResponseDTO;
import com.infofarm.Field.Dto.Response.CropData.CropDataResponseDTO;
import com.infofarm.Field.Dto.Request.Crop.UpdateCropDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.CreateCropNeedDTO;
import com.infofarm.Field.Dto.Request.CropNeeds.UpdateCropNeedDTO;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Field.Implementation.CropServiceImplementation;
import com.infofarm.Field.Models.CropNeeds;
import com.infofarm.common.dto.PageResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("api/crop")
public class CropController {

    @Autowired
    CropServiceImplementation cropServiceImpl;

    @GetMapping("get-crop/{cropId}")
    public CropResponseDTO getCropById(@PathVariable Long cropId) throws IdNotFoundException {
        return cropServiceImpl.findById(cropId);
    }

    @GetMapping("get-all-crops")
    public Page<CropResponseDTO> getCrops(@RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "10") int size) {
        return cropServiceImpl.getCrops(page,size);
    }

    @PostMapping("create-crop")
    public ResponseEntity<?> saveCrop(@RequestPart(value = "file", required = false) MultipartFile file,
            @Valid @RequestPart CreateCropDTO crop) {

        return ResponseEntity.status(HttpStatus.CREATED).body(cropServiceImpl.save(crop, file));
    }

    @PutMapping("update-crop")
    public ResponseEntity<?> updateCrop(@Valid @RequestPart UpdateCropDTO cropRequestDTO,
                                        @RequestPart(value = "file", required = false) MultipartFile file) throws IdNotFoundException {

        return new ResponseEntity<>(cropServiceImpl.update(cropRequestDTO, file), HttpStatus.OK);
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

    @GetMapping("plantation/{plantationId}/crop-data")
    public Page<CropDataResponseDTO> getCropDataByPlantationId(@PathVariable Long plantationId,
                                                               @RequestParam(required = false, defaultValue = "0") int page,
                                                               @RequestParam(required = false, defaultValue = "10") int size) throws IdNotFoundException {
        return cropServiceImpl.getCropDataByPlantationId(plantationId,page,size);
    }

    @GetMapping("get-crop-by-name")
    public ResponseEntity<PageResponseDTO<CropResponseDTO>> getCropByName(@RequestParam String name,@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok().body(cropServiceImpl.getCropsByName(name,pageable));
    }

    @GetMapping("crop-data/{Id}")
    public CropDataResponseDTO getCropDataById(@PathVariable Long Id) throws IdNotFoundException {
        return cropServiceImpl.getCropDataById(Id);
    }

    @GetMapping("/plantation/{idPlantation}/get-all-crop-data")
    public ResponseEntity<?> getAllCropsByPlantationId(@PathVariable Long idPlantation,Pageable pageable) throws IdNotFoundException {
        return ResponseEntity.ok().body(cropServiceImpl.getAllCropDataByPlantationId(idPlantation, pageable));
    }

    @PutMapping("crop-data/update/{cropDataId}/crop/{cropId}")
    public ResponseEntity<CropDataResponseDTO> updateCropData(@Valid @RequestBody UpdateCropDataDTO cropDataDTO, @PathVariable Long cropId, @PathVariable Long cropDataId) throws IdNotFoundException {
        return new ResponseEntity<>(cropServiceImpl.updateCropData(cropDataDTO, cropId, cropDataId), HttpStatus.OK);
    }

    @DeleteMapping("deleteData/{id}")
    public ResponseEntity<?> deleteData(@PathVariable Long id) throws IdNotFoundException {
        System.out.println(id);
        cropServiceImpl.deleteCropData(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("{cropId}/add-data/plantation/{plantationId}")
    public ResponseEntity<CropDataResponseDTO> addCropData(@RequestBody CreateCropDataDTO cropDataDTO, @PathVariable Long cropId, @PathVariable Long plantationId) throws IdNotFoundException {
        return new ResponseEntity<>(cropServiceImpl.addCropData(cropDataDTO,cropId,plantationId), HttpStatus.OK);
    }
}
