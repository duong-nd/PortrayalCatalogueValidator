import javax.swing.JTextArea;

import org.apache.poi.xssf.usermodel.XSSFSheet;


public class LineValidator extends PointValidator {

	public LineValidator(XSSFSheet sheet, JTextArea log) {
		super(sheet, log);
		colorcell.clear();
		colorcell.add(9);
		colorcell.add(25);
		// TODO Auto-generated constructor stub
	}

}
