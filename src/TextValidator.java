import javax.swing.JTextArea;

import org.apache.poi.xssf.usermodel.XSSFSheet;


public class TextValidator extends PointValidator {

	public TextValidator(XSSFSheet sheet, JTextArea log) {
		super(sheet, log);
		colorcell.clear();
		colorcell.add(19);
		// TODO Auto-generated constructor stub
	}

	public static void main (String [] args) {
		String res = "aaa";
		res.concat("afasfas");
		res.concat(String.format("a new string %d",2));
		System.out.println(res);
	}
}
