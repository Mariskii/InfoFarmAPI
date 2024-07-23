package com.infofarm.Field.Repository;

import com.infofarm.Field.Models.Crop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends CrudRepository<Crop, Long> {
    @Query("SELECT c.image_public_id FROM Crop c WHERE c.id = :id")
    String findImagePublicIdById(@Param("id") Long id);
}
