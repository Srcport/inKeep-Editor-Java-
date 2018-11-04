package myAccount;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import config.Settings;

public class AccountView extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	private static JLabel filler = new JLabel();
	private static JLabel title = new JLabel("Account Management");
	
	private static JLabel username = new JLabel("Current Username:");
	private static JLabel changeUsername = new JLabel("Change Username To:");
	protected static JTextField usernameDisplay = new JTextField();
	protected static JTextField usernameInput = new JTextField();
	
	private static JLabel changePassword = new JLabel("New Password:");
	private static JLabel confirmPassword = new JLabel("Confirm Password:");
	protected static JPasswordField passwordInput = new JPasswordField();
	protected static JPasswordField confirmInput = new JPasswordField();
	
	private static JLabel email = new JLabel("Current Email:");
	private static JLabel changeEmail = new JLabel("Change Email To:");
	protected static JTextField emailDisplay = new JTextField();
	protected static JTextField emailInput = new JTextField();
	
	private static JLabel firstname = new JLabel("Current First name:");
	private static JLabel changeFirstname = new JLabel("Change First Name To:");
	protected static JTextField firstnameDisplay = new JTextField();
	protected static JTextField firstnameInput = new JTextField();
	
	private static JLabel surname = new JLabel("Current Surname:");
	private static JLabel changeSurname = new JLabel("Change Surname To:");
	protected static JTextField surnameDisplay = new JTextField();
	protected static JTextField surnameInput = new JTextField();
	
	protected static JButton submit = new JButton("Submit Changes");
	
	protected static JLabel loading = new JLabel();
	

	public AccountView()
	{
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(Settings.PANEL_AREA));
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Loader image
		java.net.URL imgURL = getClass().getResource("/resources/loadbar.gif");
		ImageIcon icon = new ImageIcon(imgURL);
		Image loadingIcon = icon.getImage();
		loading.setIcon(new ImageIcon(loadingIcon));
		loading.setVisible(false);
		
		// Action Listeners
		submit.addActionListener(this);
		
		// Borders
		usernameDisplay.setBorder(Settings.THIN_ORANGE_BORDER);
		usernameInput.setBorder(Settings.THIN_ORANGE_BORDER);
		passwordInput.setBorder(Settings.THIN_ORANGE_BORDER);
		confirmInput.setBorder(Settings.THIN_ORANGE_BORDER);
		emailDisplay.setBorder(Settings.THIN_ORANGE_BORDER);
		emailInput.setBorder(Settings.THIN_ORANGE_BORDER);
		firstnameDisplay.setBorder(Settings.THIN_ORANGE_BORDER);
		firstnameInput.setBorder(Settings.THIN_ORANGE_BORDER);
		surnameDisplay.setBorder(Settings.THIN_ORANGE_BORDER);
		surnameInput.setBorder(Settings.THIN_ORANGE_BORDER);
		
		// Sizes
		usernameDisplay.setPreferredSize(Settings.INPUT_AREA);
		usernameInput.setPreferredSize(Settings.INPUT_AREA);
		passwordInput.setPreferredSize(Settings.INPUT_AREA);
		confirmInput.setPreferredSize(Settings.INPUT_AREA);
		emailDisplay.setPreferredSize(Settings.INPUT_AREA);
		emailInput.setPreferredSize(Settings.INPUT_AREA);
		firstnameDisplay.setPreferredSize(Settings.INPUT_AREA);
		firstnameInput.setPreferredSize(Settings.INPUT_AREA);
		surnameDisplay.setPreferredSize(Settings.INPUT_AREA);
		surnameInput.setPreferredSize(Settings.INPUT_AREA);
		
		// Editable
		usernameDisplay.setEditable(false);
		emailDisplay.setEditable(false);
		firstnameDisplay.setEditable(false);
		surnameDisplay.setEditable(false);
		
		// Style
		title.setForeground(Settings.SITE_GREEN);
		title.setFont(Settings.TITLEFONT);
		usernameDisplay.setBackground(Color.LIGHT_GRAY);
		emailDisplay.setBackground(Color.LIGHT_GRAY);
		firstnameDisplay.setBackground(Color.LIGHT_GRAY);
		surnameDisplay.setBackground(Color.LIGHT_GRAY);
		
		// Layout
		int i = 0;
		gbc.weighty = 0.1;
		gbc.weightx = 1.0;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(10, 0, 0, 0);
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(title, gbc);
		
		i++;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(username, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 1;
		add(usernameDisplay, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 2;
		add(changeUsername, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 3;
		add(usernameInput, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(changePassword, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 1;
		add(passwordInput, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 2;
		add(confirmPassword, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 3;
		add(confirmInput, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(email, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 1;
		add(emailDisplay, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 2;
		add(changeEmail, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 3;
		add(emailInput, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(firstname, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 1;
		add(firstnameDisplay, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 2;
		add(changeFirstname, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 3;
		add(firstnameInput, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(surname, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 1;
		add(surnameDisplay, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.gridx = 2;
		add(changeSurname, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		
		gbc.gridx = 3;
		add(surnameInput, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 4;
		add(submit, gbc);
		
		i++;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(loading, gbc);
		
		i++;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.weighty = 10;
		gbc.weightx = 10;
		add(filler, gbc);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == submit)
		{
			submit.setEnabled(false);
			loading.setVisible(true);
			AccountController.UpdateDetails(); // Update user details
		}
	}
}
