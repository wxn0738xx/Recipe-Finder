package com.example.demo;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.recipefinder.RecipeFinder;

public class SameDateIngredientTest {

	RecipeFinder recipefinder = new RecipeFinder();
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}


	@Test
	public void out() throws Exception {
		System.out.print("hello");
		assertEquals("hello", outContent.toString());
	
	}
/**
 * If all of ingredients have the same & valid use-by date, all useable recipes should be printed
 * */
	@Test
		public void test() throws Exception {
			recipefinder.recipePath = "./recipe.json";
			recipefinder.fridgePath = "./fridge_equaldate.csv";
			recipefinder.getFridgeList();
			recipefinder.getRecipeList();
			recipefinder.getReadyRecipes();
			recipefinder.getBestRecipe();


			assertTrue(outContent.toString().contains("salad sandwich\n" + 
					"grilled cheese on toast\n" + 
					"(All of these include one closest use-by ingredient.)"));
			
		}

	

}
