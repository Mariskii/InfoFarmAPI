package com.infofarm.Orders.Repository;

import com.infofarm.Orders.Models.Costumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CostumerRepository extends CrudRepository<Costumer, Long> {
    Page<Costumer> findByCostumerNameStartingWith(String name ,Pageable pageable);
}
