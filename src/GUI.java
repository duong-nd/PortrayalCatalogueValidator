import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton openButton;
	JButton validateButton;
    JTextArea log;
    JFileChooser fc;
    JTextField filename;
    
    private class fcListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
            int returnVal = fc.showOpenDialog(GUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                filename.setText(file.getAbsolutePath());          	
            }			
		}
    	
    }

    private class valListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			File file = fc.getSelectedFile();
			CatalogueValidator.validate(file, log);
		}
    	
    }    
    
	public GUI () {
        super(new BorderLayout());
		filename = new JTextField(50);
		filename.setMargin(new Insets(5,5,5,5));
		filename.setEditable(false);
        
        log = new JTextArea(15,50);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        
        //Create a file chooser
        fc = new JFileChooser();
        openButton = new JButton("Browse..");
        openButton.addActionListener(new fcListener());
        validateButton = new JButton("Validate");
        validateButton.addActionListener(new valListener());        
        JPanel selectFilePanel = new JPanel();
        selectFilePanel.add(filename);
        selectFilePanel.add(openButton);
        selectFilePanel.add(validateButton, BorderLayout.SOUTH);
        this.add(selectFilePanel, BorderLayout.PAGE_START);
        
        
        this.add(logScrollPane, BorderLayout.CENTER);
	}
}
