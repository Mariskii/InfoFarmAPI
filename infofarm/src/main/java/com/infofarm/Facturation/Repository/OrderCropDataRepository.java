package com.infofarm.Facturation.Repository;

import com.infofarm.Facturation.Models.OrderCropData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderCropDataRepository extends CrudRepository<OrderCropData, Long> {
}
