package com.example.util;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csvreader.CsvReader;
import com.example.model.Fridge;
import com.example.model.Unit;

public class FridgeUtil {
	public static ArrayList<Fridge> fridgeList = new ArrayList<Fridge>();

	/**
	 * read from fridge.csv, create fridge objects and store in fridgeList
	 */
	public static void readCsvFile(String filePath) {
		try {
			ArrayList<String[]> csvList = new ArrayList<String[]>();
			CsvReader reader = new CsvReader(filePath, ',', Charset.forName("GBK"));

			while (reader.readRecord()) {
				csvList.add(reader.getValues()); // 按行读取，并把每一行的数据添加到list集合
			}
			reader.close();
			for (int row = 0; row < csvList.size(); row++) {

				Fridge fridge = new Fridge();

				fridge.setItem(csvList.get(row)[0]);
				fridge.setAmount(Integer.parseInt(csvList.get(row)[1]));
				fridge.setUnit(Unit.valueOf(csvList.get(row)[2]));
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
				Date useBy = dateFormatter.parse(csvList.get(row)[3]);
				fridge.setUseBy(useBy);
				fridgeList.add(fridge);

			}
//			System.out.println(fridgeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
