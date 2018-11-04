package login;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import startup.Home;

import config.BetterSecurity;
import config.Settings;

public class ActivateView extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static JLabel title = new JLabel("Activate Account");
    private static JButton back = new JButton("Back");
    private static JTextArea information = new JTextArea("If you're a new author, you'll need to activate your account - to do this following the instructions at inkeep.org/info/account-upgrade/ and then enter the code provided below.");
    
    private static JLabel filler = new JLabel();
    
    private static JLabel codeLabel = new JLabel("Enter Code:");
    protected static JTextField codeInput = new JTextField();
    
    private static JLabel usernameLabel = new JLabel("Enter Username:");
    protected static JTextField usernameInput = new JTextField();
    
    private static JLabel emailLabel = new JLabel("Enter Email:");
    protected static JTextField emailInput = new JTextField();
    
    private static JTextArea information2 = new JTextArea("You'll need to set an application password, this can be the same as your website login or different, just don't forget it!");
    
    private static JLabel passLabel = new JLabel("Enter Password:");
    protected static JPasswordField passInput = new JPasswordField();
    
    private static JLabel confLabel = new JLabel("Confirm Password:");
    protected static JPasswordField confInput = new JPasswordField();
    
    protected static JButton submit = new JButton("Submit");

    public ActivateView() {
	setLayout(new GridBagLayout());
	setPreferredSize(Settings.PANEL_AREA);
	GridBagConstraints gbc = new GridBagConstraints();
	int i = 0;
	
	back.addActionListener(this);
	submit.addActionListener(this);
	
	information.setPreferredSize(new Dimension(780, 45));
	information.setEditable(false);
	information.setLineWrap(true);
	information.setWrapStyleWord(true);
	information.setBorder(Settings.SMALL_DOUBLE_BORDER);
	
	information2.setPreferredSize(new Dimension(780, 25));
	information2.setEditable(false);
	information2.setLineWrap(true);
	information2.setWrapStyleWord(true);
	information2.setBorder(Settings.SMALL_DOUBLE_BORDER);
	
	title.setFont(Settings.TITLEFONT);
	title.setForeground(Settings.SITE_GREEN);
	
	codeInput.setPreferredSize(Settings.INPUT_AREA);
	codeInput.setBorder(Settings.THIN_ORANGE_BORDER);
	
	emailInput.setPreferredSize(Settings.INPUT_AREA);
	emailInput.setBorder(Settings.THIN_ORANGE_BORDER);
	
	passInput.setPreferredSize(Settings.INPUT_AREA);
	passInput.setBorder(Settings.THIN_GREEN_BORDER);
	
	confInput.setPreferredSize(Settings.INPUT_AREA);
	confInput.setBorder(Settings.THIN_GREEN_BORDER);
	
	usernameInput.setPreferredSize(Settings.INPUT_AREA);
	usernameInput.setBorder(Settings.THIN_ORANGE_BORDER);
	
	gbc.insets = new Insets(10, 0, 0, 0);
	gbc.gridwidth = 4;
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.PAGE_START;
	add(title, gbc);
	gbc.insets = new Insets(10, 10, 0, 0);
	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
	add(back, gbc);
	
	i++;
	
	gbc.insets = new Insets(10, 10, 0, 0);
	gbc.gridwidth = 4;
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(information, gbc);
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(codeLabel, gbc);
	
	gbc.gridx = 1;
	add(codeInput, gbc);
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(usernameLabel, gbc);
	
	gbc.gridx = 1;
	add(usernameInput, gbc);
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(emailLabel, gbc);
	
	gbc.gridx = 1;
	add(emailInput, gbc);
	
	i++;
	
	gbc.gridwidth = 4;
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(information2, gbc);
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.gridwidth = 1;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(passLabel, gbc);
	
	gbc.gridx = 1;
	add(passInput, gbc);
	
	i++;
	
	gbc.gridx = 0;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_START;
	add(confLabel, gbc);
	
	gbc.gridx = 1;
	add(confInput, gbc);
	
	i++;
	
	gbc.gridx = 1;
	gbc.gridy = i;
	gbc.anchor = GridBagConstraints.LINE_START;
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
	    int uid = ActivateConnect.FindUserId(usernameInput.getText(), emailInput.getText());
	    boolean verified = ActivateConnect.VerifyCode(codeInput.getText(), uid);
	    
	    if (verified)
	    {
		String passText = new String(passInput.getPassword());
		String confText = new String(confInput.getPassword());
		
		if (passText.matches(confText))
		{
		    try {
			String hash = BetterSecurity.createHash(passText);
			
			ActivateConnect.SetAppPass(uid, hash);
			
			ActivateConnect.FinishVerification(uid);
			
		    } catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		    } catch (InvalidKeySpecException e1) {
			e1.printStackTrace();
		    }
		}
	    }
	    
	    System.out.println(verified);
	}
	
    }

}
