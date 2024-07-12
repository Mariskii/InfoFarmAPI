package com.infofarm.Bussines.Repository;

import com.infofarm.Bussines.Models.Bussines;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BussinesRepository extends CrudRepository<Bussines, Long> {
}
