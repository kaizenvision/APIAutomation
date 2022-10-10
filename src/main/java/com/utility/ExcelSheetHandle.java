package com.utility;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.base.BaseClass;


public class ExcelSheetHandle extends BaseClass {
	
	public static FileInputStream fis;
	
	public ExcelSheetHandle() {
		try {
			this.fis = new FileInputStream(projectPath+"//src//test//resources//testdata//demodata.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FileInputStream getExcelFile() throws FileNotFoundException {
	
		fis = new FileInputStream(projectPath+"//src//test//resources//testdata//demodata.xlsx");
		return fis;
	}
	
	public Sheet getSheet(String sheetName) {
		Sheet sh = null;
		try {
			sh = WorkbookFactory.create(this.fis).getSheet(sheetName);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sh;
	}
	
	public Object[][] getExcelSheetData(Sheet sh) {
		
		int getRow = sh.getLastRowNum();
		Map<String, Object> finaldata = new HashMap<String, Object>();		
		
		Object obj[][] = new Object[getRow][1];
		
		for(int i=0; i<getRow; i++) {
			Map<String, Object> data = new HashMap<String, Object>();
			int col = sh.getRow(i).getLastCellNum();
			
			for(int j=0; j<col; j++) {
				
				if(sh.getRow(i+1).getCell(j).getCellType().name().equals("STRING")) {
					data.put(sh.getRow(0).getCell(j).getStringCellValue(), 
							sh.getRow(i+1).getCell(j).getStringCellValue());
				}
				else if(sh.getRow(i+1).getCell(j).getCellType().name().equals("NUMERIC")) {
					data.put(sh.getRow(0).getCell(j).getStringCellValue(), 
							sh.getRow(i+1).getCell(j).getNumericCellValue());
				}
				obj[i][0]=data;
			}
			data.forEach(finaldata::put);
		}
		
		return obj;
	}
	
	public String getSingleStringVale(Sheet sh,int row, int col) {
		return sh.getRow(row).getCell(col).getStringCellValue();
	}
	
	public Object[][] getTestData(Sheet sh) {
		int getRow = sh.getLastRowNum();
		
		Object obj[][] = new Object[getRow][2];
		for(int i=0; i<getRow; i++) {
			int col = sh.getRow(i).getLastCellNum();
			
			for(int j=0; j<col;j++) {
				System.out.println(i+" "+j+" "+sh.getRow(i+1).getCell(j).getStringCellValue());
				obj[i][j] = sh.getRow(i+1).getCell(j).getStringCellValue();				
			}
				
		}
	//	System.out.println(data);
		return obj;
	}
}
