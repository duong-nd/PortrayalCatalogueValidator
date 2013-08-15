import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.*;

public final class CatalogTemplate {
	String filename;		
	FileInputStream file;
	XSSFWorkbook workbook;	 
	
	public CatalogTemplate() {
		filename = new String("C:\\Users\\d1nguyen\\Downloads\\Portrayal_Catalog_TEMPLATE_v4.xlsx");
			try {
				file = new FileInputStream(new File(filename));
				workbook= new XSSFWorkbook(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
}
