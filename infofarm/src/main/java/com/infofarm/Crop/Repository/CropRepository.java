package com.infofarm.Crop.Repository;

import com.infofarm.Crop.Models.Crop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends CrudRepository<Crop, Long> {
}
