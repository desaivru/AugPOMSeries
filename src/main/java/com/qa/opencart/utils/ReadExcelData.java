package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelData {

	 static String filePath = "./src/test/resources/testdata/practicedata.xlsx";
	 static Workbook book;
	 static Sheet sheet;
	
	public static void main(String[] args) {
		
		String sheetName = "practice";
		Object data[][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(filePath);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0; i<sheet.getLastRowNum(); i++) {
				for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
					
					data[i][j] = sheet.getRow(i).getCell(j);
					System.out.print(data[i][j].toString());
					System.out.print(" ");
				}
				System.out.println();
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
