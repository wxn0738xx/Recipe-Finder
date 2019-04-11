package com.example.model;

import java.util.Date;

public class Fridge {

	private String item;
	private int amount;
	private Unit unit;
	private Date useBy;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public Date getUseBy() {
		return useBy;
	}
	public void setUseBy(Date useBy) {
		this.useBy = useBy;
	}
	
}
