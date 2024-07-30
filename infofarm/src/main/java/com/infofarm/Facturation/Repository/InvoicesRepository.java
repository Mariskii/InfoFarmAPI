package com.infofarm.Facturation.Repository;

import com.infofarm.Facturation.Models.Cost;
import com.infofarm.Facturation.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i WHERE EXTRACT(MONTH FROM i.creationBill) = :month AND EXTRACT(YEAR FROM i.creationBill) = :year")
    List<Invoice> findByBuyDateMonth(int month, int year);
}
