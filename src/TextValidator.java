import javax.swing.JTextArea;

import org.apache.poi.xssf.usermodel.XSSFSheet;


public class TextValidator extends PointValidator {

	public TextValidator(XSSFSheet sheet, JTextArea log) {
		super(sheet, log);
		colorcell.clear();
		colorcell.add(19);
		// TODO Auto-generated constructor stub
	}

}
