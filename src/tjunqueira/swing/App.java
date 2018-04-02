package tjunqueira.swing;

import javax.swing.SwingUtilities;

public class App {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> new MainFrame("Hello world application"));
		
	}

}
