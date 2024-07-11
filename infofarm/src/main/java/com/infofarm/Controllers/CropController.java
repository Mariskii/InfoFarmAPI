package com.infofarm.Controllers;

import com.infofarm.Implementation.CropServiceImplementation;
import com.infofarm.Models.Crop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/crop")
public class CropController {

    @Autowired
    CropServiceImplementation cropServiceImpl;

    @PostMapping("create-crop")
    public ResponseEntity<?> saveCrop(Crop crop) {
        cropServiceImpl.save(crop);
        return new ResponseEntity<>(crop, HttpStatus.CREATED);
    }
}
