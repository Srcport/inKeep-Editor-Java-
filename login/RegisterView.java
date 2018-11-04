package login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import startup.Home;
import config.Settings;

public class RegisterView extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;
    
    private static JLabel title = new JLabel("Register Today");
    private static JButton back = new JButton("Back");
    private static JLabel filler = new JLabel();
    private static JTextArea information = new JTextArea("As an inKeep author you will have access to a publishing and promotion platform to reach and engage users while promoting your products and boosting sales. After you signup you'll have full access to the inKeep editor application, developer support and publishing community.\n\nIt's really easy to become an inKeep author and have your articles hosted on the front page of the website, you just register your details and download the lightweight software. After you've downloaded the software you can log in and write an article straight away.");
    
    private static JLabel username = new JLabel("Username:");
    protected static JTextField usernameInput = new JTextField();
    private static JLabel firstname = new JLabel("First Name:");
    protected static JTextField firstnameInput = new JTextField();
    private static JLabel surname = new JLabel("Surname:");
    protected static JTextField surnameInput = new JTextField();
    private static JLabel email = new JLabel("Email Address:");
    protected static JTextField emailInput = new JTextField();
    private static JLabel password = new JLabel("Password:");
    protected static JPasswordField passwordInput = new JPasswordField();
    private static JLabel confirm = new JLabel("Confirm Password:");
    protected static JPasswordField confirmInput = new JPasswordField();
    private static JLabel dob = new JLabel("Date of Birth:");
    protected static JComboBox<String> dob_day = new JComboBox<String>();
    protected static JComboBox<String> dob_month = new JComboBox<String>();
    protected static JComboBox<String> dob_year = new JComboBox<String>();
    private static String[] monthsList = {"January","February","March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static JLabel cross1 = new JLabel(); private static JLabel tick1 = new JLabel();
    private static JLabel cross2 = new JLabel(); private static JLabel tick2 = new JLabel();
    private static JLabel cross3 = new JLabel(); private static JLabel tick3 = new JLabel();
    private static JLabel cross4 = new JLabel(); private static JLabel tick4 = new JLabel();
    private static JLabel cross5 = new JLabel(); private static JLabel tick5 = new JLabel();
    private static JLabel cross6 = new JLabel(); private static JLabel tick6 = new JLabel();
    private static JButton submit = new JButton("Register Now");
    
    public RegisterView() {
	
	setLayout(new GridBagLayout());
	setPreferredSize(Settings.PANEL_AREA);
	GridBagConstraints gbc = new GridBagConstraints();
	int i = 0;
	
	// Image Icons
	java.net.URL imgURL = getClass().getResource("/resources/smalltick.png");
	ImageIcon tickicon = new ImageIcon(imgURL);
	Image tickimage = tickicon.getImage();
	Image tickIcon = tickimage.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
	java.net.URL imgURL2 = getClass().getResource("/resources/smallcross.png");
	ImageIcon crossicon = new ImageIcon(imgURL2);
	Image crossimage = crossicon.getImage();
	Image crossIcon = crossimage.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
	tick1.setIcon(new ImageIcon(tickIcon)); cross1.setIcon(new ImageIcon(crossIcon));
	tick2.setIcon(new ImageIcon(tickIcon)); cross2.setIcon(new ImageIcon(crossIcon));
	tick3.setIcon(new ImageIcon(tickIcon)); cross3.setIcon(new ImageIcon(crossIcon));
	tick4.setIcon(new ImageIcon(tickIcon)); cross4.setIcon(new ImageIcon(crossIcon));
	tick5.setIcon(new ImageIcon(tickIcon)); cross5.setIcon(new ImageIcon(crossIcon));
	tick6.setIcon(new ImageIcon(tickIcon)); cross6.setIcon(new ImageIcon(crossIcon));
	
	tick1.setVisible(false);
	tick2.setVisible(false);
	tick3.setVisible(false);
	tick4.setVisible(false);
	tick5.setVisible(false);
	tick6.setVisible(false);
	
	// Populate JComboBoxes
	dob_day.addItem("- Day -");
	dob_month.addItem("- Month -");
	dob_year.addItem("- Year -");
	for (int j = 1; j <= 31; j++) {
	    dob_day.addItem(String.valueOf(j));
	}
	
	for (String s : monthsList) {
	    dob_month.addItem(s);
	}
	
	for (int k = 1900; k < 2015; k++) {
	    dob_year.addItem(String.valueOf(k));
	}
	
	// Action Listener
	back.addActionListener(this);
	usernameInput.addKeyListener(this);
	firstnameInput.addKeyListener(this);
	surnameInput.addKeyListener(this);
	emailInput.addKeyListener(this);
	passwordInput.addKeyListener(this);
	confirmInput.addKeyListener(this);
	submit.addActionListener(this);
	
	// Size
	usernameInput.setPreferredSize(Settings.INPUT_AREA);
	firstnameInput.setPreferredSize(Settings.INPUT_AREA);
	surnameInput.setPreferredSize(Settings.INPUT_AREA);
	emailInput.setPreferredSize(Settings.INPUT_AREA);
	passwordInput.setPreferredSize(Settings.INPUT_AREA);
	confirmInput.setPreferredSize(Settings.INPUT_AREA);

	// Special
	information.setPreferredSize(new Dimension(780, 125));
	information.setEditable(false);
	information.setLineWrap(true);
	information.setWrapStyleWord(true);
	
	// Style
	title.setFont(Settings.TITLEFONT);
	title.setForeground(Settings.SITE_GREEN);
	information.setBorder(Settings.SMALL_DOUBLE_BORDER);
	usernameInput.setBorder(Settings.THIN_GREEN_BORDER);
	firstnameInput.setBorder(Settings.THIN_GREEN_BORDER);
	surnameInput.setBorder(Settings.THIN_GREEN_BORDER);
	emailInput.setBorder(Settings.THIN_GREEN_BORDER);
	passwordInput.setBorder(Settings.THIN_GREEN_BORDER);
	confirmInput.setBorder(Settings.THIN_GREEN_BORDER);
	
	gbc.insets = new Insets(10, 0, 0, 0);
	gbc.gridwidth = 4;
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.PAGE_START;
	add(title, gbc);
	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
	add(back, gbc);
	
	i++;
	
	gbc.insets = new Insets(10, 10, 0, 0);
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(information, gbc);
	
	i++;
	
	gbc.gridwidth = 1;
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_END;
	add(username, gbc);
	
	gbc.gridx = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(usernameInput, gbc);
	
	gbc.gridwidth = 2;
	gbc.insets = new Insets(10, 0, 0, 105);
	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.gridx = 1;
	add(cross1, gbc);
	add(tick1, gbc);
	gbc.insets = new Insets(10, 10, 0, 0);
	gbc.gridwidth = 1;
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_END;
	add(firstname, gbc);
	
	gbc.gridx = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(firstnameInput, gbc);
	
	gbc.gridwidth = 2;
	gbc.insets = new Insets(10, 0, 0, 105);
	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.gridx = 1;
	add(cross2, gbc);
	add(tick2, gbc);
	gbc.insets = new Insets(10, 10, 0, 0);
	gbc.gridwidth = 1;
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_END;
	add(surname, gbc);
	
	gbc.gridx = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(surnameInput, gbc);
	
	gbc.gridwidth = 2;
	gbc.insets = new Insets(10, 0, 0, 105);
	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.gridx = 1;
	add(cross3, gbc);
	add(tick3, gbc);
	gbc.insets = new Insets(10, 10, 0, 0);
	gbc.gridwidth = 1;
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_END;
	add(email, gbc);
	
	gbc.gridx = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(emailInput, gbc);
	
	gbc.gridwidth = 2;
	gbc.insets = new Insets(10, 0, 0, 105);
	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.gridx = 1;
	add(cross4, gbc);
	add(tick4, gbc);
	gbc.insets = new Insets(10, 10, 0, 0);
	gbc.gridwidth = 1;
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_END;
	add(password, gbc);
	
	gbc.gridx = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(passwordInput, gbc);
	
	gbc.gridwidth = 2;
	gbc.insets = new Insets(10, 0, 0, 105);
	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.gridx = 1;
	add(cross5, gbc);
	add(tick5, gbc);
	gbc.insets = new Insets(10, 10, 0, 0);
	gbc.gridwidth = 1;
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_END;
	add(confirm, gbc);
	
	gbc.gridx = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(confirmInput, gbc);
	
	gbc.gridwidth = 2;
	gbc.insets = new Insets(10, 0, 0, 105);
	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.gridx = 1;
	add(cross6, gbc);
	add(tick6, gbc);
	gbc.insets = new Insets(10, 10, 0, 0);
	gbc.gridwidth = 1;
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_END;
	add(dob, gbc);
	
	gbc.gridx = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(dob_day, gbc);
	gbc.insets = new Insets(10, 110, 0, 0);
	add(dob_month, gbc);
	gbc.insets = new Insets(10, 230, 0, 0);
	add(dob_year, gbc);
	
	i++;
	
	gbc.gridwidth = 2;
	gbc.anchor = GridBagConstraints.CENTER;
	gbc.gridx = 0;
	gbc.gridy = i;
	add(submit, gbc);
	
	
	
	i++;
	gbc.gridwidth = 4;
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.fill = GridBagConstraints.BOTH;
	gbc.weightx = 10;
	gbc.weighty = 10;
	add(filler, gbc);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == back)
	{
	    Home.HideAllButOne(Home.login);
	}
	else if (e.getSource() == submit)
	{
	    String passText = new String(passwordInput.getPassword());
	    String confText = new String(confirmInput.getPassword());

	    if (RegisterController.CheckPasswordLength(passText))
	    {
		if (RegisterController.CheckConfPassword(confText, passText))
		{
		    if (RegisterController.CheckTxtCharactersNumbersOnly(usernameInput.getText()) && RegisterController.CheckTxtCharactersNumbersOnly(firstnameInput.getText()) && RegisterController.CheckTxtCharactersNumbersOnly(surnameInput.getText()) && emailInput.getText().contains("@"))
		    {
			if (!dob_day.getSelectedItem().equals("- Day -") && !dob_month.getSelectedItem().equals("- Month -") && !dob_year.getSelectedItem().equals("- Year -"))
			{
			    int dob_month_num = 0;
			    if (dob_month.getSelectedItem().equals("January")) {
				dob_month_num = 1;
			    } else if (dob_month.getSelectedItem().equals("February")) {
				dob_month_num = 2;
			    } else if (dob_month.getSelectedItem().equals("March")) {
				dob_month_num = 3;
			    } else if (dob_month.getSelectedItem().equals("April")) {
				dob_month_num = 4;
			    } else if (dob_month.getSelectedItem().equals("May")) {
				dob_month_num = 5;
			    } else if (dob_month.getSelectedItem().equals("June")) {
				dob_month_num = 6;
			    } else if (dob_month.getSelectedItem().equals("July")) {
				dob_month_num = 7;
			    } else if (dob_month.getSelectedItem().equals("August")) {
				dob_month_num = 8;
			    } else if (dob_month.getSelectedItem().equals("September")) {
				dob_month_num = 9;
			    } else if (dob_month.getSelectedItem().equals("October")) {
				dob_month_num = 10;
			    } else if (dob_month.getSelectedItem().equals("November")) {
				dob_month_num = 11;
			    } else if (dob_month.getSelectedItem().equals("December")) {
				dob_month_num = 12;
			    }
			    String full_dob = dob_year.getSelectedItem() + "-" + dob_month_num + "-" + dob_day.getSelectedItem();
			    RegisterController.SubmitUser(usernameInput.getText(), firstnameInput.getText(), surnameInput.getText(), emailInput.getText(), passText, full_dob);
			}
		    }
		}
	    }
	}
    }

    @Override
    public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void keyPressed(KeyEvent e) {
	
    }

    @Override
    public void keyReleased(KeyEvent e) {
	if (e.getSource() == usernameInput)
	{
	    if (RegisterController.CheckTxtCharactersNumbersOnly(usernameInput.getText()))
	    {
		tick1.setVisible(true);
		cross1.setVisible(false);
	    }
	    else 
	    {
		tick1.setVisible(false);
		cross1.setVisible(true);
	    }
	}
	else if (e.getSource() == firstnameInput)
	{
	    if (RegisterController.CheckTxtCharactersNumbersOnly(firstnameInput.getText()))
	    {
		tick2.setVisible(true);
		cross2.setVisible(false);
	    }
	    else 
	    {
		tick2.setVisible(false);
		cross2.setVisible(true);
	    }
	}
	else if (e.getSource() == surnameInput)
	{
	    if (RegisterController.CheckTxtCharactersNumbersOnly(surnameInput.getText()))
	    {
		tick3.setVisible(true);
		cross3.setVisible(false);
	    }
	    else 
	    {
		tick3.setVisible(false);
		cross3.setVisible(true);
	    }
	}
	else if (e.getSource() == emailInput)
	{
	    if (RegisterController.CheckEmailContainsAT(emailInput.getText()))
	    {
		tick4.setVisible(true);
		cross4.setVisible(false);
	    }
	    else 
	    {
		tick4.setVisible(false);
		cross4.setVisible(true);
	    }
	}
	else if (e.getSource() == passwordInput)
	{
	    String passText = new String(passwordInput.getPassword());
	    if (RegisterController.CheckPasswordLength(passText))
	    {
		tick5.setVisible(true);
		cross5.setVisible(false);
	    }
	    else 
	    {
		tick5.setVisible(false);
		cross5.setVisible(true);
	    }
	}
	else if (e.getSource() == confirmInput)
	{
	    String confText = new String(confirmInput.getPassword());
	    String passText = new String(passwordInput.getPassword());
	    
	    if (RegisterController.CheckConfPassword(confText, passText))
	    {
		tick6.setVisible(true);
		cross6.setVisible(false);
	    }
	    else 
	    {
		tick6.setVisible(false);
		cross6.setVisible(true);
	    }
	}
    }

}
