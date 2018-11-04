/* This is the toolbar that appears at the top of the screen. */
package startup;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import config.Settings;

public class Toolbar extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private static JButton dashboard = new JButton("Dashboard");
	private static JButton myAccount = new JButton("My Account");
	private static JButton createArticle = new JButton("Create Article");
	private static JButton selectAd = new JButton("Select Adverts");
	private static JButton registerAd = new JButton("Register Ad");
	private static JButton contact = new JButton("Contact Us");

	public Toolbar()
	{
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(Settings.WIDTH, 50));
		setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Settings.SITE_ORANGE));
		SetButtonState(false);
		
		// Action Listeners
		dashboard.addActionListener(this);
		myAccount.addActionListener(this);
		createArticle.addActionListener(this);
		selectAd.addActionListener(this);
		registerAd.addActionListener(this);
		contact.addActionListener(this);		
		
		// Add the buttons to the panel
		add(dashboard);
		add(myAccount);
		add(createArticle);
		add(selectAd);
		add(registerAd);
		add(contact);
		
	}
	
	public static void SetButtonState(boolean state)
	{
		dashboard.setEnabled(state);
		myAccount.setEnabled(state);
		createArticle.setEnabled(state);
		selectAd.setEnabled(state);
		registerAd.setEnabled(state);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == dashboard)
		{
			Home.HideAllButOne(Home.dashboard);
		}
		else if (e.getSource() == myAccount)
		{
			Home.HideAllButOne(Home.account);
		}
		else if (e.getSource() == createArticle)
		{
			Home.HideAllButTwo(Home.createArticleScroll, Home.toolbarArticle);
		}
		else if (e.getSource() == selectAd)
		{
			Home.HideAllButOne(Home.selectAd);
		}
		else if (e.getSource() == registerAd)
		{
			Home.HideAllButOne(Home.registerAdScroll);
		}
		else if (e.getSource() == contact)
		{
			Home.HideAllButOne(Home.contact);
		}
	}
}
