package com.example.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.recipefinder.RecipeFinder;

public class TakeoutTest {
	RecipeFinder recipefinder = new RecipeFinder();
	@Before public void setUp() throws Exception 
	  {
		
	  }
	 
	 @After public void tearDown()
	  {
	  }
	
	
	
	@Test
	/**
	 * Test ReadyRecipes with ingredients not out of date and enough amount 
	 */
	public void TestTakeout() throws Exception {
		recipefinder.recipePath = "./recipe.json";
		recipefinder.fridgePath = "./fridge_takeout.csv";
		recipefinder.getRecipeList();
		recipefinder.getFridgeList();
		recipefinder.getReadyRecipes();
		
		
//		legalIngredientList should be empty
		assertTrue(recipefinder.legalIngredientList != null);
		
//		no recipe is ready to be cook
		assertTrue(recipefinder.readyRecipes != null);
		
		System.out.println("recommand"+recipefinder.bestRecipe);
		ArrayList<String> testrecommandList = new ArrayList<String>();
		
	}	

}
