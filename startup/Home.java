/* This is the main JFrame where everything takes place. The JPanels *
 * are called here and can be accessed statically through  the  Home *
 * class. */
package startup;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import login.ActivateView;
import login.LoginView;
import login.RegisterView;
import myAccount.AccountView;
import registerAd.RegisterAdView;
import selectAdvert.SelectAdvertView;
import config.Settings;
import contact.ContactView;
import createArticle.CreateArticleStatus;
import createArticle.CreateArticleToolbar;
import createArticle.CreateArticleView;
import dashboard.Dashboard;

public class Home extends JFrame
{

	private static final long serialVersionUID = 1L;
	public static LoginView login = new LoginView();
	public static Toolbar toolbar = new Toolbar();
	public static Dashboard dashboard = new Dashboard();
	public static AccountView account = new AccountView();
	public static CreateArticleView createArticle = new CreateArticleView();
	public static JScrollPane createArticleScroll = new JScrollPane(createArticle, 
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	public static CreateArticleToolbar toolbarArticle = new CreateArticleToolbar();
	public static CreateArticleStatus createArticleStatus = new CreateArticleStatus();
	public static ContactView contact = new ContactView();
	public static RegisterAdView registerAd = new RegisterAdView();
	public static JScrollPane registerAdScroll = new JScrollPane(registerAd, 
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	public static SelectAdvertView selectAd = new SelectAdvertView();
	public static RegisterView acctRegister = new RegisterView();
	public static ActivateView acctActivate = new ActivateView();
	
	public Home()
	{	
		setSize(Settings.WIDTH, Settings.HEIGHT);
		setTitle(Settings.TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// Style
		createArticleScroll.setBorder(null);
		registerAdScroll.setBorder(null);
		
		// Sizes
		createArticleScroll.setPreferredSize(new Dimension(800, 488));
		registerAdScroll.setPreferredSize(Settings.PANEL_AREA);
		
		// Border
		
		// Visibility
		dashboard.setVisible(false);
		account.setVisible(false);
		createArticleScroll.setVisible(false);
		toolbarArticle.setVisible(false);
		createArticleStatus.setVisible(false);
		contact.setVisible(false);
		registerAdScroll.setVisible(false);
		selectAd.setVisible(false);
		acctRegister.setVisible(false);
		acctActivate.setVisible(false);
				
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		int i = 0;
		
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weighty = 1.0;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(toolbar, gbc);
		i++;
		
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(dashboard, gbc);
		add(account, gbc);
		add(createArticleScroll, gbc);
		add(createArticleStatus, gbc);
		add(contact, gbc);
		add(registerAdScroll, gbc);
		add(selectAd, gbc);
		add(acctRegister, gbc);
		add(acctActivate, gbc);
		
		gbc.fill = GridBagConstraints.NONE;
		add(login, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(toolbarArticle, gbc);
		
		//Toolbar.SetButtonState(true); // TODO: REMOVE ME TO MAKE LOGIN FUNCTION WORK
		//Home.login.setVisible(false); // TODO: REMOVE ME TO MAKE LOGIN FUNCTION WORK
		setVisible(true);
	}
	
	/* Hides all but one JPanel */
	public static void HideAllButOne(JComponent showMe)
	{
		dashboard.setVisible(false);
		account.setVisible(false);
		toolbarArticle.setVisible(false);
		createArticleScroll.setVisible(false);	
		createArticleStatus.setVisible(false);
		contact.setVisible(false);
		login.setVisible(false);
		registerAdScroll.setVisible(false);
		selectAd.setVisible(false);
		acctRegister.setVisible(false);
		acctActivate.setVisible(false);
		
		showMe.setVisible(true);
	}
	
	/* Hides all but two JPanels */
	public static void HideAllButTwo(JScrollPane showMe1, JPanel showMe2)
	{
		dashboard.setVisible(false);
		account.setVisible(false);
		toolbarArticle.setVisible(false);
		createArticleScroll.setVisible(false);
		createArticleStatus.setVisible(false);
		contact.setVisible(false);
		login.setVisible(false);
		registerAdScroll.setVisible(false);
		selectAd.setVisible(false);
		acctRegister.setVisible(false);
		acctActivate.setVisible(false);
		
		showMe1.setVisible(true);
		showMe2.setVisible(true);		
	}
}
