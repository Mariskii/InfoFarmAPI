package com.infofarm.Implementation;

import com.infofarm.Models.Crop;
import com.infofarm.Repository.CropRepository;
import com.infofarm.Service.CropService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CropServiceImplementation implements CropService {

    @Autowired
    CropRepository cropRepository;

    @Override
    public void save(Crop crop) {
        cropRepository.save(crop);
    }
}
