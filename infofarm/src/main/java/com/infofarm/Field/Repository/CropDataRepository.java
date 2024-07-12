package com.infofarm.Field.Repository;

import com.infofarm.Field.Models.CropData;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CropDataRepository extends CrudRepository<CropData, Long> {
    Set<CropData> findByCrop_Id(Long id);
}
