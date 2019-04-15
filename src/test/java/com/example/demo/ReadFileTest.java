package com.example.demo;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.model.Fridge;
import com.example.model.Ingredient;
import com.example.model.Recipe;
import com.example.recipefinder.RecipeFinder;

public class ReadFileTest {
	RecipeFinder recipefinder = new RecipeFinder();
//	 recipefinder.getFridgeList();

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() {
	}

	@Test
	/**
	 * Test fridgeList
	 */
	public void TestFridge() throws Exception {
		recipefinder.recipePath = "./recipe.json";
		recipefinder.fridgePath = "./fridge.csv";
		recipefinder.getFridgeList();
		ArrayList<String> testfridgeList = new ArrayList<String>();
		ArrayList<String> testlegalList = new ArrayList<String>();

//	    fridgeList should not be null
		assertTrue(recipefinder.fridgeList != null);
		for (Fridge i : recipefinder.fridgeList) {
			testfridgeList.add(i.getItem());
		}
		List<String> expectedValue = Arrays.asList("bread", "cheese", "butter", "peanut butter", "mixed salad");

//		fridgeList should be with correct value
		assertEquals(expectedValue, testfridgeList);

//		test list of legal ingredients which are not out of date 
		for (Fridge i : recipefinder.legalIngredientList) {
			testlegalList.add(i.getItem());

		}
		List<String> expectedLegalList = Arrays.asList("bread", "cheese", "butter", "peanut butter", "mixed salad");
		assertEquals(expectedLegalList, testlegalList);
		
	}

	@Test
	/**
	 * Test read from json file
	 */
	public void TestRecipe() throws Exception {
		ArrayList<String> testrecipeList = new ArrayList<String>();
		recipefinder.recipePath = "./recipe.json";
		recipefinder.fridgePath = "./fridge.csv";
		recipefinder.getRecipeList();

//	    recipeList should not be null
		assertTrue(recipefinder.recipeList != null);

		for (Recipe r : recipefinder.recipeList) {
			testrecipeList.add(r.getName());
		}
		List<String> expectedValue = Arrays.asList("grilled cheese on toast", "salad sandwich");

//	    recipeList should be with correct value
		assertEquals(expectedValue, testrecipeList);
		
	}

}
