package dashboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import config.Settings;

public class Dashboard extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private static JLabel filler = new JLabel();
	
	private static JLabel appIcon = new JLabel();
	
	private static JTextArea accountArea = new JTextArea();
	
	private static JTextArea createArticleArea = new JTextArea();
	
	private static JTextArea manageArticleArea = new JTextArea();
	
	private static JTextArea registerBookArea = new JTextArea();
	
	private static JTextArea lessonsArea = new JTextArea();
	private static JButton lessonsBtn = new JButton("Software Help");
	private static JButton compressBtn = new JButton("Image Compression");
	
	public static JPanel notificationsArea = new JPanel();
	public static JScrollPane notificationsScroll = new JScrollPane(notificationsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private static JLabel notificationsTitle = new JLabel("Notifications");
	
	private static JTextArea contactArea = new JTextArea();

	public Dashboard()
	{	
		setPreferredSize(Settings.PANEL_AREA);
		setBackground(Settings.SITE_GRAY);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		int i = 0;
		
		// Set layout for notifications JPanel
		notificationsArea.setLayout(new BoxLayout(notificationsArea, BoxLayout.Y_AXIS));
		notificationsArea.setBorder(Settings.SMALL_EMPTY_INNER_BORDER);
		
		// action Listener 
		lessonsBtn.addActionListener(this);
		compressBtn.addActionListener(this);
		
		// Filler
		filler.setBackground(Color.red);
		
		// Style
		
		notificationsTitle.setFont(Settings.SUBTITLEFONT);
		notificationsTitle.setForeground(Settings.SITE_GREEN);
		
		// Size
		accountArea.setPreferredSize(new Dimension(460,45));
		createArticleArea.setPreferredSize(new Dimension(460,45));
		manageArticleArea.setPreferredSize(new Dimension(460,45));
		registerBookArea.setPreferredSize(new Dimension(460,45));
		lessonsArea.setPreferredSize(new Dimension(460,60));
		contactArea.setPreferredSize(new Dimension(460,45));
		notificationsScroll.setPreferredSize(new Dimension(300, 320));
		
		// Label Message
		accountArea.setText("You can manage you account and personal details in the 'My Account' area.");
		createArticleArea.setText("You can create new articles in the 'Create Article' area to be submitted for review.");
		manageArticleArea.setText("You can manage your existing articles that have already been submitted in the 'Manage Articles' area.");
		registerBookArea.setText("You can regsiter your own books for use in promotions in the 'Register My Book' area.");
		contactArea.setText("You can contact us for any questions or assistance in the 'contact' area.");
		lessonsArea.setText("You can learn how to use this software more by viewing some of the lessons. The lessons provide easy to follow instructions with images showing you how to perform any task.");
		
		// Wrap Text
		accountArea.setLineWrap(true);
		accountArea.setWrapStyleWord(true);
		createArticleArea.setLineWrap(true);
		createArticleArea.setWrapStyleWord(true);
		manageArticleArea.setLineWrap(true);
		manageArticleArea.setWrapStyleWord(true);
		registerBookArea.setLineWrap(true);
		registerBookArea.setWrapStyleWord(true);
		lessonsArea.setLineWrap(true);
		lessonsArea.setWrapStyleWord(true);
		contactArea.setLineWrap(true);
		contactArea.setWrapStyleWord(true);
		
		// Editable 
		accountArea.setEditable(false);
		createArticleArea.setEditable(false);
		manageArticleArea.setEditable(false);
		registerBookArea.setEditable(false);
		contactArea.setEditable(false);
		lessonsArea.setEditable(false);
		
		// Border
		accountArea.setBorder(Settings.BIG_DOUBLE_BORDER);
		createArticleArea.setBorder(Settings.BIG_DOUBLE_BORDER);
		manageArticleArea.setBorder(Settings.BIG_DOUBLE_BORDER);
		registerBookArea.setBorder(Settings.BIG_DOUBLE_BORDER);
		contactArea.setBorder(Settings.BIG_DOUBLE_BORDER);
		lessonsArea.setBorder(Settings.BIG_DOUBLE_BORDER);
		notificationsScroll.setBorder(Settings.THIN_GREEN_BORDER);
		
		// App Image
		java.net.URL imgURL = getClass().getResource("/resources/longicon.png");
		ImageIcon icon = new ImageIcon(imgURL);
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(462, 85,  java.awt.Image.SCALE_SMOOTH);
		appIcon.setIcon(new ImageIcon(newimg));
		
		gbc.weighty = 0.1;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridx = i;
		add(appIcon, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(accountArea, gbc);
		
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		add(notificationsTitle, gbc);
		gbc.insets = new Insets(33, 0, 0, 0);
		gbc.gridheight = 6;
		add(notificationsScroll, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(createArticleArea, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(manageArticleArea, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(registerBookArea, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(contactArea, gbc);
		
		i++;
		
		gbc.weighty = 20;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(lessonsArea, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 1;
		add(lessonsBtn, gbc);
				
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 2;
		add(compressBtn, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
	    if (e.getSource() == lessonsBtn) 
	    {
		OpenWebPage("http://www.inkeep.org/info/software-lessons/");
	    }
	    else if (e.getSource() == compressBtn) 
	    {
		OpenWebPage("http://www.inkeep.org/info/image-compression/");
	    }
	}
	
	public void OpenWebPage(String url){
	    try {         
	      java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
	    }
	    catch (java.io.IOException e) {
	        System.out.println(e.getMessage());
	    }
	 }
}
