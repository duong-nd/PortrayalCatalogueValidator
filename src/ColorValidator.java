import javax.swing.JTextArea;

import org.apache.poi.xssf.usermodel.XSSFSheet;


public class ColorValidator extends SheetValidator {
	static String hexa = "0123456789abcdef";
	
	public static boolean isRGB(String st) {
		if (st.charAt(0) != '#') return false;
		if (st.length() != 7) return false;
		
		for (int i = 1; i < st.length(); i ++) {
			if (hexa.indexOf(st.charAt(i)) == -1) return false; 
		}
		return true;		
	}
	
	public ColorValidator(XSSFSheet sheet, JTextArea log) {
		super(sheet, log);
		// TODO Auto-generated constructor stub
	}
	
}
