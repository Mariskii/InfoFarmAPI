package com.infofarm.Field.Repository;

import com.infofarm.Field.Models.CropData;
import com.infofarm.Field.Models.Plantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantationRepository extends JpaRepository<Plantation, Long> {
}
