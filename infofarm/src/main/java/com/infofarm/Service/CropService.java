package com.infofarm.Service;

import com.infofarm.Dto.Crop.UpdateCropDTO;
import com.infofarm.Exception.Errors.IdNotFoundException;
import com.infofarm.Models.Crop;
import org.apache.coyote.Response;

public interface CropService {

    void save(Crop crop);

    void update(UpdateCropDTO crop) throws IdNotFoundException;

    void delete(Long id) throws IdNotFoundException;

    Crop findById(Long id) throws IdNotFoundException;
}
