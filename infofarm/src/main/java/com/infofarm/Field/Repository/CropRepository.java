package com.infofarm.Field.Repository;

import com.infofarm.Field.Models.Crop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    @Query("SELECT c.image_public_id FROM Crop c WHERE c.id = :id")
    String findImagePublicIdById(@Param("id") Long id);

    Page<Crop> findByCropNameContaining(String name, Pageable pageable);
}
