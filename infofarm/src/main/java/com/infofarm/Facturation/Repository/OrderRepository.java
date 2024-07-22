package com.infofarm.Facturation.Repository;

import com.infofarm.Facturation.Models.CropOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<CropOrder, Long> {

}
