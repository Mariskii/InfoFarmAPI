package com.infofarm.Crop.Repository;

import com.infofarm.Crop.Models.CropNeeds;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CropNeedsRepository extends CrudRepository<CropNeeds, Long> {
    Set<CropNeeds> findAllByCrop_Id(Long id);
}
