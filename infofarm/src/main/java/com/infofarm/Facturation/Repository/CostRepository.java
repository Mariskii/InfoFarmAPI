package com.infofarm.Facturation.Repository;

import com.infofarm.Facturation.Models.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CostRepository extends JpaRepository<Cost, Long> {
    @Query("SELECT c FROM Cost c WHERE EXTRACT(MONTH FROM c.buyDate) = :month AND EXTRACT(YEAR FROM c.buyDate) = :year")
    List<Cost> findByBuyDateMonth(int month, int year);
}
