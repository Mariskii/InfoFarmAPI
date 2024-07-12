package com.infofarm.Field.Repository;

import com.infofarm.Field.Models.Plantation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantationRepository extends CrudRepository<Plantation, Long> {
}
