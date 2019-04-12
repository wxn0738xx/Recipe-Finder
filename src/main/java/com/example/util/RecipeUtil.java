package com.example.util;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;

import com.example.model.Ingredient;
import com.example.model.Recipe;
import com.example.model.Unit;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;

import com.google.gson.JsonObject;

import com.google.gson.JsonParser;

import com.google.gson.JsonSyntaxException;

public class RecipeUtil {
	public static ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

	public static ArrayList<Recipe> getRecipes(String filePath) {
		JsonParser parser = new JsonParser();
		JsonArray array;
		try {
			array = (JsonArray) parser.parse(new FileReader(filePath));
			for (int i = 0; i < array.size(); i++) {
				JsonObject recipeitem = array.get(i).getAsJsonObject();
				Recipe recipe = new Recipe();
				recipe.setName(recipeitem.get("name").getAsString());

				JsonArray ingredientsArray = (JsonArray) recipeitem.get("ingredients");

				ArrayList<Ingredient> itemList = new ArrayList<Ingredient>();
				for (int j = 0; j < (ingredientsArray).size(); j++) {
					Ingredient ingredient = new Ingredient();
					String item = ingredientsArray.get(j).getAsJsonObject().get("item").toString();
					ingredient.setItem(item);
					ingredient.setAmount(Integer.parseInt(
							(ingredientsArray.get(j).getAsJsonObject().get("amount").toString().replace("\"", ""))));
					ingredient.setUnit(Unit.valueOf((String) ingredientsArray.get(j).getAsJsonObject().get("unit")
							.toString().replace("\"", "")));
					itemList.add(ingredient);
				}
				recipe.setIngredients(itemList);
				recipeList.add(recipe);

			}
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR] " + e.toString());
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR] " + e.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR] " + e.toString());
		}
		return recipeList;
	}
//	

	public static void main(String[] args) {
		getRecipes("recipe.json");

	}
}
