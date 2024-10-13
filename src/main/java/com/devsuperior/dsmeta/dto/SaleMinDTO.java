package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleMinDTO {

	private Long id;
	private Double amount;
	private LocalDate date;
	private String sellerName;

	public SaleMinDTO(Sale sale) {
		this.id = sale.getId();
		this.amount = sale.getAmount();
		this.date = sale.getDate();
		this.sellerName= sale.getSeller().getName();
	}

	public SaleMinDTO(Long id, Double amount, LocalDate date, String sellerName) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.sellerName = sellerName;
	}

	public SaleMinDTO(String id, String amount, String date, String sellerName) {
		this.id = Long.valueOf(id);
		this.amount = Double.valueOf(amount);
		this.date = LocalDate.parse(date);
		this.sellerName = sellerName;
	}


	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
