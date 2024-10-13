package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.TotalBySellerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("Select new com.devsuperior.dsmeta.dto.SaleMinDTO(s.id,s.amount,s.date,s.seller.name) from Sale s where s.date between :initialDate and :finalDate " +
            "and Upper(s.seller.name) like concat('%',UPPER(:sellerName),'%')")
    Page<SaleMinDTO> searchByDate(String sellerName, LocalDate initialDate, LocalDate finalDate, Pageable pageable);

    @Query("select new com.devsuperior.dsmeta.dto.TotalBySellerDTO(s.seller.name,sum(s.amount)) from Sale s " +
            "where Upper(s.seller.name) like concat('%',UPPER(:sellerName),'%') " +
            "and s.date between :initialDate and :finalDate group by s.seller.name")
    Page<TotalBySellerDTO> searchTotalBySeller(String sellerName, LocalDate initialDate, LocalDate finalDate, Pageable pageable);

}
