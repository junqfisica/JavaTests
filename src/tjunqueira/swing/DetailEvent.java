package tjunqueira.swing;

import java.util.EventObject;

public class DetailEvent extends EventObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4774338380152186342L;
	private String text;
	
	public DetailEvent(Object source, String text) {
		super(source);
		
		this.text = text;
	}
	
	public String getText() {
		return text;
	}

}
