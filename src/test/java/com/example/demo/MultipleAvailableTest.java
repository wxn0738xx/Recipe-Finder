package com.example.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.example.model.Recipe;
import com.example.recipefinder.RecipeFinder;

public class MultipleAvailableTest {
	RecipeFinder recipefinder = new RecipeFinder();
	
/**
 * if multiple recipes are legal to cook, all of them should be in recommandRecipeList
 * */
	@Test
	public void testrecommandRecipeList() throws Exception {
		ArrayList<String> testrecommandList = new ArrayList<String>();
		recipefinder.recipePath = "./recipe.json";
		recipefinder.fridgePath = "./fridge.csv";
		recipefinder.getFridgeList();
		recipefinder.getRecipeList();
		recipefinder.getReadyRecipes();
		recipefinder.getBestRecipe();
		for (Recipe r : recipefinder.recommandRecipeList) {
			testrecommandList.add(r.getName());
		}
		List<String> expectedValue = Arrays.asList("grilled cheese on toast", "salad sandwich");
		
		assertEquals(expectedValue, testrecommandList);
		
			}
	
/**
 * If multiple recipe are legal to cook, only one recipe which has cloest date ingredient should be the bestRecipe
 * */
	@Test
	public void testBestRecipe() throws Exception {
		recipefinder.recipePath = "./recipe.json";
		recipefinder.fridgePath = "./fridge.csv";
		recipefinder.getFridgeList();
		recipefinder.getRecipeList();
		recipefinder.getReadyRecipes();
		recipefinder.getBestRecipe();
		String expectedValue="salad sandwich";	
		assertEquals(expectedValue, recipefinder.bestRecipe);
	}

}
