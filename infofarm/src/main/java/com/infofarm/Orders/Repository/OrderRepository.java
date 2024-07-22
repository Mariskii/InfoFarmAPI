package com.infofarm.Orders.Repository;

import com.infofarm.Orders.Models.CropOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<CropOrder, Long> {

}
