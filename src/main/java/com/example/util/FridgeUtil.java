package com.example.util;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.example.model.Fridge;
import com.example.model.Unit;

public class FridgeUtil {
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	public static ArrayList<Fridge> fridgeList = new ArrayList<Fridge>();

	/**
	 * read from fridge.csv, create fridge objects and store in fridgeList
	 * 
	 */

	public static List<Fridge> getFridge(String fridgePath) throws Exception {

		try (Scanner scanner = new Scanner(new FileInputStream(fridgePath));) {

			while (scanner.hasNextLine()) {
				fridgeList.add(setFridge(scanner.nextLine()));
			}

		} catch (Exception e) {
			System.out.println("[ERROR] " + e.toString());
		}
		return fridgeList;
	}

	public static Fridge setFridge(String data) throws Exception {

		String[] records = data.split(",");

		Fridge fridge = new Fridge();
		
		fridge.setItem(records[0]);
		fridge.setAmount(Integer.parseInt(records[1]));
		fridge.setUnit(Unit.valueOf(records[2]));
		Date useBy = dateFormatter.parse(records[3]);
		fridge.setUseBy(useBy);

		return fridge;
	}

}
