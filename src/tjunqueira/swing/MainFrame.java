package tjunqueira.swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5247017448611956293L;
	
	private JTextArea textArea;
	private DetailsPanel detailspanel;
	private FormPanel formPanel; 
	

	public MainFrame(String title) {
		super(title);
		
		createUI();
		
	}
	
	private void createUI() {
		
		this.setSize(500,400);
		// Close application when close button is pressed.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set frame visible.
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		//Set Layout manager.
		getContentPane().setLayout(new BorderLayout());
				
		// Create a Swing component.
		textArea = new JTextArea();
		
		detailspanel = new DetailsPanel();
		detailspanel.addDetailListener(e -> onAddTextClick(e));
		
		formPanel = new FormPanel(true);
		
		// Add to content pane.
		Container c = getContentPane();
					
		//c.add(textArea, BorderLayout.CENTER);
		c.add(formPanel, BorderLayout.CENTER);
		c.add(detailspanel, BorderLayout.WEST);
		
	}
	
	private void onAddTextClick(DetailEvent e) {
		textArea.append(e.getText());
	}

}
