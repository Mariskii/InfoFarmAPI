package com.infofarm.Field.Repository;

import com.infofarm.Field.Models.CropData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CropDataRepository extends JpaRepository<CropData, Long> {
    Page<CropData> findByPlantationId(Long id, Pageable pageable);
}
