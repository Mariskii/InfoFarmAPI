package com.infofarm.Field.Repository;

import com.infofarm.Field.Models.Crop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends CrudRepository<Crop, Long> {
}
