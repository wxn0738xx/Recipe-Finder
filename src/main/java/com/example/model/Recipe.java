package com.example.model;

import java.util.List;

public class Recipe {
	private String name;
	private List<Ingredient>ingredients;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	

}
