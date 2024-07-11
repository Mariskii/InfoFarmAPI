package com.infofarm.Controllers;

import com.infofarm.Implementation.CropServiceImplementation;
import com.infofarm.Models.Crop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crop")
public class CropController {

    @Autowired
    CropServiceImplementation cropServiceImpl;

    @PostMapping("create-crop")
    public void saveCrop(Crop crop) {
        cropServiceImpl.save(crop);
    }
}
