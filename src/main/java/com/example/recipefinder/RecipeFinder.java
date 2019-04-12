package com.example.recipefinder;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.example.model.Fridge;
import com.example.model.Ingredient;
import com.example.model.Recipe;
import com.example.util.FridgeUtil;
import com.example.util.RecipeUtil;

public class RecipeFinder {

	public static String fridgePath;
	public static String recipePath;
	public static ArrayList<Recipe> readyRecipes = new ArrayList<Recipe>();
	public static ArrayList<Fridge> fridgeList;
	public static ArrayList<Recipe> recipeList;
//	ingredients in fridge which are not out of date
	public static ArrayList<Fridge> legalIngredientList = new ArrayList<Fridge>();
	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
//	all valid recipes with legal Ingredients
	public static ArrayList<Recipe> recommandRecipeList = new ArrayList<Recipe>();
	public static String bestRecipe;

	public static void main(String[] args) {
		
		try {
			getFilesPath();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			getFridgeList();
		getRecipeList();
		

		
		if (fridgeList.size() == 0 || recipeList.size() == 0) {
			System.out.println("Wrong file path or empty file");
		} else {
			getReadyRecipes();

			if (recommandRecipeList.size() == 0) {
				System.out.println("Order Takeout");
			} else {
				getBestRecipe();
			}

			endProcess();

		}
//		try {

//		}catch (Exception e){
//			System.out.println("[ERROR] " + e.toString());
//		}

//		end();
	}

	public static void getFilesPath() throws Exception {
		
			System.out.println("Please enter the file path of fridge.csv");
			Scanner scan = new Scanner(System.in);
			fridgePath = scan.next();

			System.out.println("Please enter the file path of recipes.json");

			recipePath = scan.next();
			scan.close();
		

	}

	private static void getFridgeList() {
		Date now = new Date();
//getFridgeList
		fridgeList = FridgeUtil.readCsvFile(fridgePath);
//		fridgeList = FridgeUtil.readCsvFile("./fridge.csv");
//		get LegalIngredientList in fridge(not out of date)
		for (Fridge i : fridgeList) {
//			if ingredient is not expired, add to the legalIngredientList
			if (i.getUseBy().after(now) || dateFormatter.format(i.getUseBy()).equals(dateFormatter.format(now))) {
				legalIngredientList.add(i);
			}
		}
	}

	private static void getRecipeList() {
//		recipeList = RecipeUtil.getRecipes("./recipe.json");
		recipeList = RecipeUtil.getRecipes(recipePath);
	}

//	private static void evaluation() {
//		getFilesPath();
//		getFridgeList();
//		getRecipeList();
//		if (fridgeList.size() == 0 || recipeList.size() == 0) {
//			System.out.println("Wrong file path or empty file");
//		} else {
//			getReadyRecipes();
//		}
//
//	}

//get recipes which could cook
	private static void getReadyRecipes() {
//		i is each recipe in the recipeList
		for (Recipe i : recipeList) {
//			total amount of ingredients each recipe needs
			int totalIngredients = 0;
//			a is each needed ingredient in one recipe
			for (Ingredient a : i.getIngredients()) {
//				j is each legal ingredient in legalIngredientList
				for (Fridge j : legalIngredientList) {

					if ((a.getItem().replace("\"", "")).equals(j.getItem()) && j.getAmount() >= a.getAmount()
							&& a.getUnit() == j.getUnit()) {
						totalIngredients = totalIngredients + 1;
					}
				}
			}

			if (totalIngredients == i.getIngredients().size()) {
				recommandRecipeList.add(i);

			}
		}
	}

	private static void getBestRecipe() {

		Map<Recipe, Date> recipeDate = new HashMap<Recipe, Date>();
		ArrayList<Recipe> bestRecipeList = new ArrayList<Recipe>();
		boolean MapHaveCloest = false;
		Date MapCloestDate = new Date();
		if (recommandRecipeList.size() == 1) {

			for (Recipe i : recommandRecipeList) {
				bestRecipe = i.getName();
			}
			System.out.println(bestRecipe);
		}

		else if (recommandRecipeList.size() > 1) {
//	each recipe in commandRecipeList
			for (Recipe i : recommandRecipeList) {
				Date cloestDate = new Date();
				boolean haveCloest = false;

//		each ingredient in one recipe
				for (Ingredient j : i.getIngredients()) {
//			each leagal ingredient in legalIngredientList
					for (Fridge item : legalIngredientList) {
						if ((j.getItem().replace("\"", "")).equals(item.getItem())) {
							if (haveCloest == false) {
								cloestDate = item.getUseBy();
								haveCloest = true;
							}
							if (item.getUseBy().before(cloestDate)) {
								cloestDate = item.getUseBy();
							}
						}
					}
				}

				recipeDate.put(i, cloestDate);

			}
//the cloest date in Hash Map

			for (Date dates : recipeDate.values()) {

				if (MapHaveCloest == false) {
					MapCloestDate = dates;
				} else {
					if (dates.before(MapCloestDate)) {
						MapCloestDate = dates;
					}
				}

			}

			for (Recipe key : recipeDate.keySet()) {
				if (recipeDate.get(key).equals(MapCloestDate)) {
					bestRecipeList.add(key);
				}

			}

			for (Recipe i : bestRecipeList) {
				System.out.println(i.getName());
			}
//		if output more than one recipe
			if (bestRecipeList.size() > 1) {
				System.out.println("(All of these include one closest use-by ingredient.)");
			}
		}
	}

//end of process notification
	private static void endProcess() {
		System.out.println("Recipe Finding Process is end");
	}

}