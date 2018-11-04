package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import startup.Home;
import startup.Toolbar;
import config.Settings;

public class LoginView extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static JTextArea instructions = new JTextArea(
	    "Please log in with your email address and password");

    private static JLabel emailLabel = new JLabel("Email:");
    static JTextField emailInput = new JTextField();

    private static JLabel passLabel = new JLabel("Password:");
    static JPasswordField passInput = new JPasswordField();

    public static JButton submitBtn = new JButton("Login");

    protected static JLabel infoLabel = new JLabel();

    protected static JButton registerBtn = new JButton("Create an Account");
    protected static JButton activateBtn = new JButton("Activate Account (Step 4)");

    private static int counter = 0; // Counter for wrong credentials

    public LoginView() {
	setPreferredSize(new Dimension(430, 310));
	setBackground(Settings.SITE_GRAY);
	setLayout(new GridBagLayout());
	setBorder(Settings.THIN_GREEN_BORDER);
	GridBagConstraints gbc = new GridBagConstraints();
	int i = 0;
	
	registerBtn.setEnabled(false);

	// ActionListeners
	submitBtn.addActionListener(this);
	registerBtn.addActionListener(this);
	activateBtn.addActionListener(this);

	// Font
	instructions.setFont(Settings.SUBTITLEFONT);
	instructions.setForeground(Settings.SITE_ORANGE);

	// Line wrap
	instructions.setLineWrap(true);
	instructions.setWrapStyleWord(true);

	// Editable
	instructions.setEditable(false);
	// submitBtn.setEnabled(false);

	// Size
	instructions.setPreferredSize(new Dimension(380, 70));
	emailInput.setPreferredSize(new Dimension(250, 25));
	passInput.setPreferredSize(new Dimension(250, 25));

	// Style
	instructions.setOpaque(false);
	emailInput.setBorder(Settings.THIN_GREEN_BORDER);
	passInput.setBorder(Settings.THIN_GREEN_BORDER);

	gbc.weighty = 0.1;
	gbc.weightx = 1.0;
	gbc.gridwidth = 2;
	gbc.insets = new Insets(10, 10, 10, 10);
	gbc.anchor = GridBagConstraints.PAGE_START;
	gbc.gridx = 0;
	gbc.gridy = i;
	add(instructions, gbc);

	i++;

	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.gridwidth = 1;
	gbc.gridx = 0;
	gbc.gridy = i;
	add(emailLabel, gbc);

	gbc.anchor = GridBagConstraints.LINE_START;
	gbc.gridx = 1;
	add(emailInput, gbc);

	i++;

	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.gridx = 0;
	gbc.gridy = i;
	add(passLabel, gbc);

	gbc.anchor = GridBagConstraints.LINE_START;
	gbc.gridx = 1;
	add(passInput, gbc);

	i++;

	gbc.anchor = GridBagConstraints.CENTER;
	gbc.gridwidth = 2;
	gbc.gridx = 0;
	gbc.gridy = i;
	add(submitBtn, gbc);

	i++;

	gbc.weighty = 10;
	gbc.weightx = 10;
	gbc.fill = GridBagConstraints.BOTH;
	gbc.insets = new Insets(0, 10, -50, 0);
	gbc.gridwidth = 2;
	gbc.gridx = 0;
	gbc.gridy = i;
	add(infoLabel, gbc);
	
	gbc.insets = new Insets(10, 10, 10, 10);
	
	gbc.gridx = 1;
	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.fill = GridBagConstraints.NONE;
	add(registerBtn, gbc);
	
	gbc.gridx = 0;
	gbc.anchor = GridBagConstraints.LINE_END;
	gbc.insets = new Insets(0, 0, 0, 210);
	gbc.fill = GridBagConstraints.NONE;
	add(activateBtn, gbc);

    }

    /* Overwrite the users credentials */
    public static void ClearInputs() {
	emailInput
		.setText("af89dsy8fs9ah9sf9-asdf98sh98afy8a9sdyf90a8ysdf98ays9f8yas90yd8fa98ysdf89yasdf8ya0sf");
	passInput
		.setText("asf0dha-s9dyf-a98sdhfd9-8aydf98ayd-98fha98sfh98ahsdf89has8hfd89a-hsdf89-ahf89-ahdsfa-d");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	String passText = new String(passInput.getPassword());

	if (e.getSource() == submitBtn) {
	    if (passText.length() < 1) {
		infoLabel.setText("Password Empty");
	    }
	    else if (emailInput.getText().length() < 1) {
		infoLabel.setText("Email Empty");
	    } else {
		if (LoginConnect.VerifyUser(emailInput.getText().toString(), passText)) // Check login credentials
		    {
			Home.login.setVisible(false);
			Home.dashboard.setVisible(true);
			Toolbar.SetButtonState(true);
		    } else {
			infoLabel.setText("Wrong Credentials"); // If user attempts to
								// log in 3 times
								// wrongly program
								// exits.
			counter++;

			if (counter == 1) {
			    infoLabel.setForeground(Settings.SITE_GREEN);
			} else if (counter == 2) {
			    infoLabel.setForeground(Settings.SITE_ORANGE);
			} else if (counter == 3) {
			    infoLabel.setForeground(Color.RED);
			} else if (counter == 4) {
			    JOptionPane
				    .showMessageDialog(Home.toolbar,
					    "You've attempted to login with bad credentials too many times.");
			    System.exit(0);
			}

		    }
	    }
	    
	} else if (e.getSource() == registerBtn) {
	    Home.HideAllButOne(Home.acctRegister);
	} else if (e.getSource() == activateBtn) {
	    Home.HideAllButOne(Home.acctActivate);
	}
    }
}