import javax.swing.JTextArea;

import org.apache.poi.xssf.usermodel.XSSFSheet;


public class PolygonValidator extends PointValidator {

	public PolygonValidator(XSSFSheet sheet, JTextArea log) {
		super(sheet, log);
		colorcell.clear();
		colorcell.add(10);
		colorcell.add(16);
		// TODO Auto-generated constructor stub
	}

}
