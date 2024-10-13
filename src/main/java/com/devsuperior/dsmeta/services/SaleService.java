package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.TotalBySellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = saleRepository.findById(id);
		return new SaleMinDTO(result.get());
	}

    public Page<SaleMinDTO> searchByDate(String sellerName, String initialDate, String finalDate, Pageable pageable) {

		return saleRepository
				.searchByDate(sellerName, LocalDate.parse(initialDate), LocalDate.parse(finalDate), pageable);
	}

	public Page<TotalBySellerDTO> searchTotalBySeller(String sellerName, String initialDate, String finalDate, Pageable pageable) {

		return saleRepository
                .searchTotalBySeller(sellerName, LocalDate.parse(initialDate), LocalDate.parse(finalDate), pageable);
	}
}
