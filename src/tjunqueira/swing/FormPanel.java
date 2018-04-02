package tjunqueira.swing;

import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;

public class FormPanel extends JPanel {
	
	private JTextField nameTextField;
	private JTextField streetTextField;
	private JTextField zipTextField;
	private JTextField countryTextField;
	private JTextField cityTextField;
	private JTextField emailTextField;
	private JTextField homepageTextField;
	private JTextField phoneTextField;
	private JTextField faxTextField;
	
	public boolean isInhouse = true;
	
	public FormPanel(boolean isInhouse) {
		
		this.isInhouse = isInhouse;
		
		setBorder(BorderFactory.createTitledBorder("Personal Details"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{80, 150, 50,150};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JCheckBox productionCheckBox = new JCheckBox("In house production");
		GridBagConstraints gbc_productionCheckBox = new GridBagConstraints();
		gbc_productionCheckBox.gridwidth = 2;
		gbc_productionCheckBox.anchor = GridBagConstraints.WEST;
		gbc_productionCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_productionCheckBox.gridx = 0;
		gbc_productionCheckBox.gridy = 0;
		add(productionCheckBox, gbc_productionCheckBox);
		productionCheckBox.setSelected(isInhouse);
		productionCheckBox.addActionListener((e) -> toggleProductionBox(e, productionCheckBox));
		productionCheckBox.setEnabled(isInhouse);
		
		JLabel nameLabel = new JLabel("Name*: ");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 1;
		add(nameLabel, gbc_nameLabel);
		
		nameTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(nameTextField, gbc_textField);
		nameTextField.setColumns(10);
		nameTextField.setUI(new HintTextFieldUI("Type your company name", true));

		
		JLabel streetLabel = new JLabel("Street*:");
		GridBagConstraints gbc_streetLabel = new GridBagConstraints();
		gbc_streetLabel.anchor = GridBagConstraints.EAST;
		gbc_streetLabel.insets = new Insets(0, 0, 5, 5);
		gbc_streetLabel.gridx = 0;
		gbc_streetLabel.gridy = 2;
		add(streetLabel, gbc_streetLabel);
		
		streetTextField = new JTextField();
		GridBagConstraints gbc_streetTextField = new GridBagConstraints();
		gbc_streetTextField.insets = new Insets(0, 0, 5, 5);
		gbc_streetTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetTextField.gridx = 1;
		gbc_streetTextField.gridy = 2;
		add(streetTextField, gbc_streetTextField);
		streetTextField.setColumns(10);
		streetTextField.setUI(new HintTextFieldUI("Optinal", true));
		
		JLabel zipLabel = new JLabel("Zip:");
		GridBagConstraints gbc_zipLabel = new GridBagConstraints();
		gbc_zipLabel.anchor = GridBagConstraints.EAST;
		gbc_zipLabel.insets = new Insets(0, 0, 5, 5);
		gbc_zipLabel.gridx = 2;
		gbc_zipLabel.gridy = 2;
		add(zipLabel, gbc_zipLabel);
		
		zipTextField = new JTextField();
		GridBagConstraints gbc_zipTextField = new GridBagConstraints();
		gbc_zipTextField.insets = new Insets(0, 0, 5, 0);
		gbc_zipTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_zipTextField.gridx = 3;
		gbc_zipTextField.gridy = 2;
		add(zipTextField, gbc_zipTextField);
		zipTextField.setColumns(10);
		zipTextField.setUI(new HintTextFieldUI("Optinal", true));
		
		JLabel cityLabel = new JLabel("City:");
		GridBagConstraints gbc_cityLabel = new GridBagConstraints();
		gbc_cityLabel.anchor = GridBagConstraints.EAST;
		gbc_cityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_cityLabel.gridx = 0;
		gbc_cityLabel.gridy = 3;
		add(cityLabel, gbc_cityLabel);
		
		cityTextField = new JTextField();
		GridBagConstraints gbc_cityTextField = new GridBagConstraints();
		gbc_cityTextField.insets = new Insets(0, 0, 5, 5);
		gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityTextField.gridx = 1;
		gbc_cityTextField.gridy = 3;
		add(cityTextField, gbc_cityTextField);
		cityTextField.setColumns(10);
		cityTextField.setUI(new HintTextFieldUI("Type your city name", true));
		
		JLabel countyLabel = new JLabel("County*:");
		GridBagConstraints gbc_countyLabel = new GridBagConstraints();
		gbc_countyLabel.anchor = GridBagConstraints.EAST;
		gbc_countyLabel.insets = new Insets(0, 0, 5, 5);
		gbc_countyLabel.gridx = 2;
		gbc_countyLabel.gridy = 3;
		add(countyLabel, gbc_countyLabel);
		
		countryTextField = new JTextField();
		GridBagConstraints gbc_countryTextField = new GridBagConstraints();
		gbc_countryTextField.insets = new Insets(0, 0, 5, 0);
		gbc_countryTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_countryTextField.gridx = 3;
		gbc_countryTextField.gridy = 3;
		add(countryTextField, gbc_countryTextField);
		countryTextField.setColumns(10);
		countryTextField.setUI(new HintTextFieldUI("Type your country name", true));
		
		JLabel emailLabel = new JLabel("Email*:");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 4;
		add(emailLabel, gbc_emailLabel);
		
		emailTextField = new JTextField();
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.gridwidth = 3;
		gbc_emailTextField.insets = new Insets(0, 0, 5, 0);
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.gridx = 1;
		gbc_emailTextField.gridy = 4;
		add(emailTextField, gbc_emailTextField);
		emailTextField.setColumns(10);
		emailTextField.setUI(new HintTextFieldUI("Type your email", true));

		
		JLabel homepageLabel = new JLabel("Homepage: ");
		GridBagConstraints gbc_homepageLabel = new GridBagConstraints();
		gbc_homepageLabel.anchor = GridBagConstraints.EAST;
		gbc_homepageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_homepageLabel.gridx = 0;
		gbc_homepageLabel.gridy = 5;
		add(homepageLabel, gbc_homepageLabel);
		
		homepageTextField = new JTextField();
		GridBagConstraints gbc_homepageTextField = new GridBagConstraints();
		gbc_homepageTextField.gridwidth = 3;
		gbc_homepageTextField.insets = new Insets(0, 0, 5, 0);
		gbc_homepageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_homepageTextField.gridx = 1;
		gbc_homepageTextField.gridy = 5;
		add(homepageTextField, gbc_homepageTextField);
		homepageTextField.setColumns(10);
		homepageTextField.setUI(new HintTextFieldUI("Optinal", true));

		
		JLabel phoneLabel = new JLabel("Phone*: ");
		GridBagConstraints gbc_phoneLabel = new GridBagConstraints();
		gbc_phoneLabel.anchor = GridBagConstraints.EAST;
		gbc_phoneLabel.insets = new Insets(0, 0, 0, 5);
		gbc_phoneLabel.gridx = 0;
		gbc_phoneLabel.gridy = 6;
		add(phoneLabel, gbc_phoneLabel);
		
		phoneTextField = new JTextField();
		GridBagConstraints gbc_phoneTextField = new GridBagConstraints();
		gbc_phoneTextField.insets = new Insets(0, 0, 0, 5);
		gbc_phoneTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneTextField.gridx = 1;
		gbc_phoneTextField.gridy = 6;
		add(phoneTextField, gbc_phoneTextField);
		phoneTextField.setColumns(10);
		phoneTextField.setUI(new HintTextFieldUI("Type your phone", true));

		
		JLabel faxLabel = new JLabel("Fax: ");
		GridBagConstraints gbc_faxLabel = new GridBagConstraints();
		gbc_faxLabel.anchor = GridBagConstraints.EAST;
		gbc_faxLabel.insets = new Insets(0, 0, 0, 5);
		gbc_faxLabel.gridx = 2;
		gbc_faxLabel.gridy = 6;
		add(faxLabel, gbc_faxLabel);
		
		faxTextField = new JTextField();
		GridBagConstraints gbc_faxTextField = new GridBagConstraints();
		gbc_faxTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_faxTextField.gridx = 3;
		gbc_faxTextField.gridy = 6;
		add(faxTextField, gbc_faxTextField);
		faxTextField.setColumns(10);
		faxTextField.setUI(new HintTextFieldUI("Optinal", true));
		
		setTextFieldsEditable(!isInhouse);
	}
	
	
	public String getName() {
		return nameTextField.getText();
	}
	
	public String getStreet() {
		return streetTextField.getText();
	}
	
	public String getZip() {
		return zipTextField.getText();
	}
	
	public String getCity() {
		return cityTextField.getText();
	}
	
	public String getCountry() {
		return countryTextField.getText();
	}
	
	public String getEmail() {
		return emailTextField.getText();
	}
	
	public String getHomepage() {
		return homepageTextField.getText();
	}
	
	public String getPhone() {
		return phoneTextField.getText();
	}
	
	public String getFax() {
		return faxTextField.getText();
	}
	
	private void toggleProductionBox(ActionEvent e, JCheckBox b) {
		isInhouse = b.isSelected();
		setTextFieldsEditable(!isInhouse);
		
	}
	
	private void setTextFieldsEditable(boolean b) {
		
		nameTextField.setEditable(b);
		nameTextField.setText(null);
		
		streetTextField.setEditable(b);
		streetTextField.setText(null);
		
		zipTextField.setEditable(b);
		zipTextField.setText(null);
		
		cityTextField.setEditable(b);
		cityTextField.setText(null);
		
		countryTextField.setEditable(b);
		countryTextField.setText(null);
		
		emailTextField.setEditable(b);
		emailTextField.setText(null);
		
		homepageTextField.setEditable(b);
		homepageTextField.setText(null);
		
		phoneTextField.setEditable(b);
		phoneTextField.setText(null);
		
		faxTextField.setEditable(b);
		faxTextField.setText(null);
	}
	
}
