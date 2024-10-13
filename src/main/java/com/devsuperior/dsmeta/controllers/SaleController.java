package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.TotalBySellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = saleService.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(@RequestParam(name = "name",defaultValue = "") String sellerName,
									   @RequestParam(name = "minDate",defaultValue = "") String initialDate,
									   @RequestParam(name = "maxDate",defaultValue = "") String finalDate,
									   Pageable pageable) {

        if (finalDate.isEmpty() && !initialDate.isEmpty()) finalDate = String.valueOf(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));

		if (initialDate.isEmpty() && !finalDate.isEmpty()) initialDate = String.valueOf(LocalDate.parse(finalDate).minusYears(1L));

		if (initialDate.isEmpty() && finalDate.isEmpty()) {
			finalDate = String.valueOf((LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())));
			initialDate = String.valueOf(LocalDate.parse(finalDate).minusYears(2L));
		}

		return ResponseEntity.ok(saleService.searchByDate(sellerName, initialDate, finalDate, pageable));
	}


	@GetMapping(value = "/summary")
	public ResponseEntity<Page<TotalBySellerDTO>> getSummary(@RequestParam(name = "name",defaultValue = "") String sellerName,
															 @RequestParam(name = "minDate",defaultValue = "") String initialDate,
															 @RequestParam(name = "maxDate",defaultValue = "") String finalDate,
															 Pageable pageable) {

		if (finalDate.isEmpty() && !initialDate.isEmpty()) finalDate = String.valueOf(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));

		if (initialDate.isEmpty() && !finalDate.isEmpty()) initialDate = String.valueOf(LocalDate.parse(finalDate).minusYears(1L));

		if (initialDate.isEmpty() && finalDate.isEmpty()) {
			finalDate = String.valueOf((LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())));
			initialDate = String.valueOf(LocalDate.parse(finalDate).minusYears(2L));
		}

		return ResponseEntity.ok(saleService.searchTotalBySeller(sellerName, initialDate, finalDate, pageable));
	}
}
