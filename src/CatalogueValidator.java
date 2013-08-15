import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import org.apache.poi.xssf.usermodel.*;
public class CatalogueValidator {
	
	public static void validate(File file, JTextArea log){
		FileInputStream inputStream;
		XSSFWorkbook workbook;		
		try {
			inputStream = new FileInputStream(file);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			log.append("File not found\n"); 
			return;
		}
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.append("Wrong file type!\n");
			return ;
		}
		ColorValidator validateColorSheet = new ColorValidator(workbook.getSheet(Symbols.COLORS.toString()),log);
		validateColorSheet.go();
		
	}
	
	public static void main (String [] args) throws Throwable {
		// reads the input xlsx file
		JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new GUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
		
		String filename = new String("C:\\Users\\d1nguyen\\Downloads\\Portrayal_Catalog_TEMPLATE_v4 (1).xlsx");		
		FileInputStream file = new FileInputStream(new File(filename));
		XSSFWorkbook workbook= new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("PointSymbolizer");
		System.out.println(sheet.getSheetName());
		XSSFRow row = sheet.getRow(0);
		XSSFCell cell = row.getCell(0);
		// check the cells
		// returns 
	}
	
}
