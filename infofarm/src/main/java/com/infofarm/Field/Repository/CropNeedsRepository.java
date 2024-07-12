package com.infofarm.Field.Repository;

import com.infofarm.Field.Models.CropNeeds;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CropNeedsRepository extends CrudRepository<CropNeeds, Long> {
    Set<CropNeeds> findAllByCrop_Id(Long id);
}
