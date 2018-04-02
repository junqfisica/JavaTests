package tjunqueira.swing;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CheckSumsPanel extends JPanel{
	
	public CheckSumsPanel(boolean isInHouse) {
		
		//JPanel panel = new JPanel();
		FormPanel formPanel = new FormPanel(isInHouse);
		
		JButton generateCkButton = new JButton("Generate");
		generateCkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton scanCkButton = new JButton("Scan");
		
		JProgressBar progressBar = new JProgressBar();
		
		JLabel status = new JLabel("Status");
		status.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel statusLabel = new JLabel("Show status");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(formPanel, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(91)
					.addComponent(generateCkButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(113)
					.addComponent(status)
					.addGap(99)
					.addComponent(scanCkButton, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(267)
					.addComponent(statusLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(267))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(202)
					.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addGap(203))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(formPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(generateCkButton)
								.addComponent(scanCkButton))
							.addGap(41))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(status)
							.addGap(11)
							.addComponent(statusLabel)
							.addGap(6)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
		);
		setLayout(groupLayout);
	}
}
