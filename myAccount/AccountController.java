package myAccount;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import login.LoginConnect;
import startup.Home;
import config.BetterSecurity;
import config.Settings;

public class AccountController
{
	protected static Hashtable<Integer, String> query = new Hashtable<Integer, String>();
	
	public static void SetUserDetails()
	{
		AccountView.usernameDisplay.setText(Settings.username);
		AccountView.emailDisplay.setText(Settings.email);
		AccountView.firstnameDisplay.setText(Settings.firstName);
		AccountView.surnameDisplay.setText(Settings.surname);	
	}
	
	protected static void UpdateDetails()
	{
		new Thread()
		{
		    public void run() {
		String username = AccountView.usernameInput.getText();
		char[] passInput = AccountView.passwordInput.getPassword();
		String pass = new String(passInput);
		char[] confInput = AccountView.confirmInput.getPassword();
		String conf = new String(confInput);
		String email = AccountView.emailInput.getText();
		String fname = AccountView.firstnameInput.getText();
		String sname = AccountView.surnameInput.getText();
		int i = 0;
		
		String response = JOptionPane.showInputDialog(Home.toolbar, "Please confirm your password to make changes", "Confirm Password", JOptionPane.QUESTION_MESSAGE);
		
		if (response == null) // cancel = null
		{
			System.out.println("cancel pressed");
		}
		else {
			if (LoginConnect.VerifyUserWithoutGet(Settings.email, response))
			{
				System.out.println("Correct Credentials");
				
				if (!username.equals(""))
				{
				    if (AccountConnect.CheckUsernameExists(username))
				    {
					AccountConnect.UpdateUsers("username", username);
					i++;
				    }
				    else {
					JOptionPane.showMessageDialog(Home.toolbar, "That username is already taken, username not changed.", "Username", JOptionPane.ERROR_MESSAGE);
				    }
				}
				if (!pass.equals("") && !conf.equals(""))
				{
					if (pass.equals(conf))
					{
					    try {
						AccountConnect.UpdateUsers("password", BetterSecurity.createHash(pass));						
					    } catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					    } catch (InvalidKeySpecException e) {
						e.printStackTrace();
					    }
						i++;
					}
					else {
						JOptionPane.showMessageDialog(Home.toolbar, "Your new passwords don't match", "Change Password", JOptionPane.ERROR_MESSAGE);
					}
				} 
				if (!email.equals(""))
				{
					AccountConnect.UpdateUsers("email", email);
					i++;
				}
				if (!fname.equals(""))
				{
					AccountConnect.UpdateUsers("fname", fname);
					i++;
				}
				if (!sname.equals(""))
				{
					AccountConnect.UpdateUsers("sname", sname);
					i++;
				}
				
				if (i > 0)
				{	
					JOptionPane.showMessageDialog(Home.toolbar, "Account Details Successfully Updated - Application Closing, Please Reopen And Log In.", "Application Restart Required", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				AccountView.submit.setEnabled(true);
				AccountView.loading.setVisible(false);
			}
			else 
			{
				 JOptionPane.showMessageDialog(Home.toolbar, "Wrong Password", "Verify Account", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		    }
		}.start();
	}
}
