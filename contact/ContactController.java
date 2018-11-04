package contact;

import javax.swing.JOptionPane;

import startup.Home;

public class ContactController
{

	public static void MessageRecieved()
	{
		ContactView.messageInput.setText("");
		ContactView.subjectInput.setText("");
		JOptionPane.showMessageDialog(Home.toolbar, "Thank you, your message has been successfully recieved, we will try address your issues and get back to you soon.", "Contact Us", JOptionPane.INFORMATION_MESSAGE);
	}

}
