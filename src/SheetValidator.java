import java.io.*;
import java.util.*;

import javax.swing.JTextArea;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class SheetValidator {
	static String newline = System.getProperty("line.separator");
	static CatalogTemplate template = new CatalogTemplate(); 
	XSSFSheet currentSheet;
	JTextArea log;
	char idstyle;
	static ArrayList<String> id = new ArrayList<String>();
	
	public static boolean isin(String st) {
		return id.contains(st);
	}
	
	public String IntToColIndex(int a) {
		if (a == 0) return "A";
		String result = new String("");
		while (a > 0) {
			char[] ch = Character.toChars(65+ a%26);
			String st = ((Character) ch[0]).toString();
			result = st.concat(result);
			a = a/26;
		}
		return result;
	}
	
	public SheetValidator(XSSFSheet sheet, JTextArea log) {
		this.currentSheet = sheet;
		this.log = log;
		if (currentSheet.getSheetName().equalsIgnoreCase("PointSymbolizer")) idstyle = 'P';
		if (currentSheet.getSheetName().equalsIgnoreCase("LineSymbolizer")) idstyle = 'L';
		if (currentSheet.getSheetName().equalsIgnoreCase("PolygonSymbolizer")) idstyle = 'A';
		if (currentSheet.getSheetName().equalsIgnoreCase("TextSymbolizer")) idstyle = 'T';
		if (currentSheet.getSheetName().equalsIgnoreCase("RasterSymbolizer")) idstyle = 'R';
		if (currentSheet.getSheetName().equalsIgnoreCase("Colors")) idstyle = 'C';		
	}
	
	public String checkColumnTitles(XSSFSheet tempSheet) {
		String result = "";
		int i = 0;
		Iterator<Cell> cellIterator = currentSheet.getRow(0).cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (cell.getStringCellValue().equals("") ) continue;
			result = result.concat("First row should be empty\n");
			break;
		}
		
		for (i = 1; i < 4; i ++) {
			for (int j = 0; j < 30; j ++) {
				Cell c1 = currentSheet.getRow(i).getCell(j);
				Cell c2 = tempSheet.getRow(i).getCell(j);
				
				if (c1 == null && c2 == null) continue;
				if (c1 == null) {
					result = result.concat(String.format("Cell %d%s is missing!\n",i+1,IntToColIndex(j)));
					continue;
				}
				if (c2 == null) {
					result = result.concat(String.format("Cell %d%s should be empty!\n",i+1,IntToColIndex(j)));
					continue;
				}
				
				String cur = c1.toString();
				String temp = c2.toString();
				if (cur.equals(temp)) continue;				
				result = result.concat(String.format("Cell %d%s differs from the template!\n",i+1,IntToColIndex(j)));
			}
		}
		return result;
	}
	
	public void go () {
		XSSFSheet tempSheet = template.workbook.getSheet(currentSheet.getSheetName());		
		String titleCheckResult = checkColumnTitles(tempSheet);			
		
//		if (titleCheckResult.equals("")) titleCheckResult = "Titles OK.\n";
	//	log.append("Checking sheet " + currentSheet.getSheetName() + " ...\n" + titleCheckResult );
		if (titleCheckResult.equals("") == false) {
			log.append("Error in sheet " + currentSheet.getSheetName() + ": \n" + titleCheckResult);
		}
		int rowNum = currentSheet.getLastRowNum();
		int i;
		for (i = 4; i < rowNum;  i ++ ) {
			XSSFRow row = currentSheet.getRow(i);
			if (isEmpty(row)) continue;
			log.append(proceed(row, i));
		}
		
	}
	
	private boolean isEmpty(XSSFRow row) {
		// TODO Auto-generated method stub
		int i = 0;
		for (i = 0; i < row.getLastCellNum(); i ++ ) {
			XSSFCell cell = row.getCell(i);
			if (cell != null && cell.getCellType() != XSSFCell.CELL_TYPE_BLANK) return false;
		}
		return true;
	}

	public String proceed(XSSFRow row,int x) {
		// TODO Auto-generated method stub
		// check expert style
		String st = row.getCell(8).toString();
		if (st.equals("") == false) {
			for (int y = 0; y < row.getLastCellNum(); y ++ ) {
				Cell c = row.getCell(y);
				if (c == null ) continue;
				String cell = c.toString();
				if (cell.equals("") == false) {
					return String.format("Error: Row %d has an expert style so others column must be empty.\n",x);
				}
			}
		}
		
		//checking id name
		st = row.getCell(5).toString();
		if (st.charAt(0) != idstyle) {			
			return ("ID must start with a \""+idstyle+"\"\n");
		}
		
		if (id.contains(st)) {
			return ("ID already existed ! \n");
		}
		
		// checking if cell contains new line 
		
		for (int y = 0; y < row.getLastCellNum(); y ++ ) {			
			String cell = row.getCell(y).toString();
			if (cell.contains(newline)) {
				return String.format("Cell %d%s contains newline \n",x,IntToColIndex(y));				
			}
		}		
		
		return "";
	}

}
