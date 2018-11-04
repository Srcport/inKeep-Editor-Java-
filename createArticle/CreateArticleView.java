/* This is the main viewing panel for CREATING-NEW-ARTICLES, the user controls this *
 * by adding content into provided boxes and using the CREATE-ARTICLE-TOOLBAR class *
 * to add and remove new areas. 												    */

package createArticle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import startup.Home;

import com.inet.jortho.SpellChecker;

import config.Settings;

public class CreateArticleView extends JPanel implements ActionListener, KeyListener, MouseListener
{

	private static final long serialVersionUID = 1L;
	// The height of the JPanel which dynamicall changes as the user adds / removes elements
	public static int areaHeight = 1000; 
	// JPanel title
	protected static JLabel title = new JLabel("Create New Article");
	// Counter for gbc.gridy = i;
	protected static int i = 0;
	protected static GridBagConstraints gbc = new GridBagConstraints();
	
	// Array that holds the characters for the WFURL based off the title
	protected static ArrayList<Character> wfurlArray = new ArrayList<Character>();
	
	// First section, contains the users name choice for the article
	protected static JLabel instructions1 = new JLabel("[1]: Do you want to publish the article under your username or full name?");
	protected static JLabel usernameSelect = new JLabel("Use Username:");
	protected static JRadioButton usernameTick = new JRadioButton();
	protected static JLabel fullnameSelect = new JLabel("Use Full Name:");
	protected static JRadioButton fullnameTick = new JRadioButton();
	protected static JLabel firstNameSelect = new JLabel("Use Just First Name:");
	protected static JRadioButton firstNameTick = new JRadioButton();
	protected static ButtonGroup buttonGroup = new ButtonGroup();
	protected static JLabel help1 = new JLabel();

	// Second section contains the title and wfurl areas
	protected static JLabel instructions2 = new JLabel("[2]: Choose a title and edit the web friendly URL if necessary");
	protected static JLabel titleLabel = new JLabel("Article Title:");
	protected static JTextField titleInput = new JTextField();
	protected static JLabel wfurlLabel = new JLabel("Web-Friendly-URL:");
	protected static JTextField wfurlInput = new JTextField();
	protected static JLabel help2 = new JLabel();
	
	// Third section contains the article types 
	protected static JLabel instructions3 = new JLabel("<html><body>[3]: Choose the category that best fits your article <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(if one doesn't fit send us a message through the contact section)</body></html>");
	protected static JLabel typeLabel = new JLabel("Select an article type:");
	public static ArrayList<String> types = new ArrayList<String>(); // This array is populated via a database in Settings
	public static JComboBox<String> typeInput = new JComboBox<String>();
	protected static JLabel help3 = new JLabel();
	
	// Fourth section contains a space for the user to type an introduction and upload intro-image icon
	protected static JLabel instructions4 = new JLabel("[4]: Write a short introduction & select a 200-by-200 pixel image icon.");
	protected static JLabel introLabel = new JLabel("Introduction:");
	protected static JTextArea introInput = new JTextArea();
	protected static JScrollPane introScroll = new JScrollPane(introInput,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JLabel help4 = new JLabel();
	protected static JButton loadIntroImg = new JButton("Load Image");
	protected static JLabel introImgArea = new JLabel();
	
	// Fifth section is for the main image to be uploaded
	protected static JLabel instructions5 = new JLabel("[5]: Select the main image for your article, it should be 720 * 400 pixels.");
	protected static JButton loadMainImg = new JButton("Load Image");
	protected static JLabel loadMainArea = new JLabel();
	protected static JLabel help5 = new JLabel();
	
	// Sixth section is instructions for allowing the user to add elements
	protected static JLabel instructions6 = new JLabel("[6]: Use the toolbar at the bottom to add text and image areas.");
	protected static JLabel help6 = new JLabel();
	
	// Hashtable of JScrollPanes (17 in total)
	public static Hashtable<Integer, JScrollPane> textAreas = new Hashtable<Integer, JScrollPane>();
	// Hashtable of JTextAreas (17 in total) (they are used by the JScrollPanes)
	public static Hashtable<Integer, JTextArea> textContents = new Hashtable<Integer, JTextArea>();
	// Hashtable of JLabels for the JScrollpanes (17 in total)
	public static Hashtable<Integer, JLabel> textLabels = new Hashtable<Integer, JLabel>();
	// Hashtable of JButtons (17 in total) for the image areas
	public static Hashtable<Integer, JButton> imageButtons = new Hashtable<Integer, JButton>();
	// Hashtable of JLabels for the image displays (17 in total)
	public static Hashtable<Integer, JLabel> imageAreas = new Hashtable<Integer, JLabel>();
	
	/* These are the text labels that appear to the left of the text areas */
	protected static JLabel textInput1 = new JLabel("Text Area 1:"); protected static JLabel textInput10 = new JLabel("Text Area 10:");
	protected static JLabel textInput2 = new JLabel("Text Area 2:"); protected static JLabel textInput11 = new JLabel("Text Area 11:");
	protected static JLabel textInput3 = new JLabel("Text Area 3:"); protected static JLabel textInput12 = new JLabel("Text Area 12:");
	protected static JLabel textInput4 = new JLabel("Text Area 4:"); protected static JLabel textInput13 = new JLabel("Text Area 13:");
	protected static JLabel textInput5 = new JLabel("Text Area 5:"); protected static JLabel textInput14 = new JLabel("Text Area 14:");
	protected static JLabel textInput6 = new JLabel("Text Area 6:"); protected static JLabel textInput15 = new JLabel("Text Area 15:");
	protected static JLabel textInput7 = new JLabel("Text Area 7:"); protected static JLabel textInput16 = new JLabel("Text Area 16:");
	protected static JLabel textInput8 = new JLabel("Text Area 8:"); protected static JLabel textInput17 = new JLabel("Text Area 17:");
	protected static JLabel textInput9 = new JLabel("Text Area 9:");
	
	/* These are the JScrollPanes that the user types their article into */
	protected static JTextArea jta1 = new JTextArea();
	protected static JScrollPane jts1 = new JScrollPane(jta1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta2 = new JTextArea();
	protected static JScrollPane jts2 = new JScrollPane(jta2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta3 = new JTextArea();
	protected static JScrollPane jts3 = new JScrollPane(jta3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta4 = new JTextArea();
	protected static JScrollPane jts4 = new JScrollPane(jta4, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta5 = new JTextArea();
	protected static JScrollPane jts5 = new JScrollPane(jta5, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta6 = new JTextArea();
	protected static JScrollPane jts6 = new JScrollPane(jta6, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta7 = new JTextArea();
	protected static JScrollPane jts7 = new JScrollPane(jta7, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta8 = new JTextArea();
	protected static JScrollPane jts8 = new JScrollPane(jta8, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta9 = new JTextArea();
	protected static JScrollPane jts9 = new JScrollPane(jta9, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta10 = new JTextArea();
	protected static JScrollPane jts10 = new JScrollPane(jta10, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta11 = new JTextArea();
	protected static JScrollPane jts11 = new JScrollPane(jta11, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta12 = new JTextArea();
	protected static JScrollPane jts12 = new JScrollPane(jta12, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta13 = new JTextArea();
	protected static JScrollPane jts13 = new JScrollPane(jta13, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta14 = new JTextArea();
	protected static JScrollPane jts14 = new JScrollPane(jta14, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta15 = new JTextArea();
	protected static JScrollPane jts15 = new JScrollPane(jta15, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta16 = new JTextArea();
	protected static JScrollPane jts16 = new JScrollPane(jta16, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	protected static JTextArea jta17 = new JTextArea();
	protected static JScrollPane jts17 = new JScrollPane(jta17, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	/* These are the image buttons the user clicks to launch the file selector */
	protected static JButton loadImg1 = new JButton("Load Image 1"); protected static JButton loadImg10 = new JButton("Load Image 10");
	protected static JButton loadImg2 = new JButton("Load Image 2"); protected static JButton loadImg11 = new JButton("Load Image 11");
	protected static JButton loadImg3 = new JButton("Load Image 3"); protected static JButton loadImg12 = new JButton("Load Image 12");
	protected static JButton loadImg4 = new JButton("Load Image 4"); protected static JButton loadImg13 = new JButton("Load Image 13");
	protected static JButton loadImg5 = new JButton("Load Image 5"); protected static JButton loadImg14 = new JButton("Load Image 14");
	protected static JButton loadImg6 = new JButton("Load Image 6"); protected static JButton loadImg15 = new JButton("Load Image 15");
	protected static JButton loadImg7 = new JButton("Load Image 7"); protected static JButton loadImg16 = new JButton("Load Image 16");
	protected static JButton loadImg8 = new JButton("Load Image 8"); protected static JButton loadImg17 = new JButton("Load Image 17");
	protected static JButton loadImg9 = new JButton("Load Image 9");
	
	/* These are the JLabels used to display the iamges after the user selects an image using the file selector */
	protected static JLabel imgArea1 = new JLabel(); protected static JLabel imgArea10 = new JLabel();
	protected static JLabel imgArea2 = new JLabel(); protected static JLabel imgArea11 = new JLabel();
	protected static JLabel imgArea3 = new JLabel(); protected static JLabel imgArea12 = new JLabel();
	protected static JLabel imgArea4 = new JLabel(); protected static JLabel imgArea13 = new JLabel();
	protected static JLabel imgArea5 = new JLabel(); protected static JLabel imgArea14 = new JLabel();
	protected static JLabel imgArea6 = new JLabel(); protected static JLabel imgArea15 = new JLabel();
	protected static JLabel imgArea7 = new JLabel(); protected static JLabel imgArea16 = new JLabel();
	protected static JLabel imgArea8 = new JLabel(); protected static JLabel imgArea17 = new JLabel();
	protected static JLabel imgArea9 = new JLabel();
			
	public CreateArticleView()
	{
		setPreferredSize(new Dimension(800, areaHeight)); // areaHight dynamically changes depending on num of elements added
		setLayout(new GridBagLayout());
		
		SpellChecker.register(titleInput);
		SpellChecker.register(introInput);
		
		/* Add the text labels (JLabels) to the hashtable */
		textLabels.put(1, textInput1); textLabels.put(7, textInput7); textLabels.put(13, textInput13);
		textLabels.put(2, textInput2); textLabels.put(8, textInput8); textLabels.put(14, textInput14);
		textLabels.put(3, textInput3); textLabels.put(9, textInput9); textLabels.put(15, textInput15);
		textLabels.put(4, textInput4); textLabels.put(10, textInput10); textLabels.put(16, textInput16);
		textLabels.put(5, textInput5); textLabels.put(11, textInput11); textLabels.put(17, textInput17);
		textLabels.put(6, textInput6); textLabels.put(12, textInput12);
		
		/* Adds the JScrollPane to the equivelant hashtable */
		textAreas.put(1, jts1); textAreas.put(7, jts7); textAreas.put(13, jts13);
		textAreas.put(2, jts2); textAreas.put(8, jts8); textAreas.put(14, jts14);
		textAreas.put(3, jts3); textAreas.put(9, jts9); textAreas.put(15, jts15);
		textAreas.put(4, jts4); textAreas.put(10, jts10); textAreas.put(16, jts16);
		textAreas.put(5, jts5); textAreas.put(11, jts11); textAreas.put(17, jts17);
		textAreas.put(6, jts6); textAreas.put(12, jts12);
		
		/* Adds the JTextAreas to the equivelant hashtable */
		textContents.put(1, jta1); textContents.put(7, jta7); textContents.put(13, jta13);
		textContents.put(2, jta2); textContents.put(8, jta8); textContents.put(14, jta14);
		textContents.put(3, jta3); textContents.put(9, jta9); textContents.put(15, jta15);
		textContents.put(4, jta4); textContents.put(10, jta10); textContents.put(16, jta16);
		textContents.put(5, jta5); textContents.put(11, jta11); textContents.put(17, jta17);
		textContents.put(6, jta6); textContents.put(12, jta12);
		
		/* Adds the loads image buttons to the hashtable */
		imageButtons.put(1, loadImg1); imageButtons.put(7, loadImg7); imageButtons.put(13, loadImg13);
		imageButtons.put(2, loadImg2); imageButtons.put(8, loadImg8); imageButtons.put(14, loadImg14);
		imageButtons.put(3, loadImg3); imageButtons.put(9, loadImg9); imageButtons.put(15, loadImg15);
		imageButtons.put(4, loadImg4); imageButtons.put(10, loadImg10); imageButtons.put(16, loadImg16);
		imageButtons.put(5, loadImg5); imageButtons.put(11, loadImg11); imageButtons.put(17, loadImg17);
		imageButtons.put(6, loadImg6); imageButtons.put(12, loadImg12);
		
		/* Adds the JLabels for the image areas to the hastable */
		imageAreas.put(1, imgArea1); imageAreas.put(7, imgArea7); imageAreas.put(13, imgArea13);
		imageAreas.put(2, imgArea2); imageAreas.put(8, imgArea8); imageAreas.put(14, imgArea14);
		imageAreas.put(3, imgArea3); imageAreas.put(9, imgArea9); imageAreas.put(15, imgArea15);
		imageAreas.put(4, imgArea4); imageAreas.put(10, imgArea10); imageAreas.put(16, imgArea16);
		imageAreas.put(5, imgArea5); imageAreas.put(11, imgArea11); imageAreas.put(17, imgArea17);
		imageAreas.put(6, imgArea6); imageAreas.put(12, imgArea12); 
			
		/* Iterates the JScrollPanes to style them */
		for (JScrollPane item : textAreas.values()) {
			item.setPreferredSize(new Dimension(540, 200));
			((JTextArea) item.getViewport().getView()).setLineWrap(true);
			((JTextArea) item.getViewport().getView()).setMargin(new Insets(5, 5, 5, 5));
			item.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Settings.SITE_ORANGE));
		}
		
		for (JTextArea item : textContents.values()) {
		    SpellChecker.register(item);
		}
		
		/* Iterates the JLabel image areas to style them */
		for (JLabel item : imageAreas.values()) {
			item.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Settings.SITE_ORANGE));
			item.setPreferredSize(new Dimension(360, 175));
			java.net.URL imgURL = getClass().getResource("/resources/imagefillercross.png");
			ImageIcon icon = new ImageIcon(imgURL);
			Image image = icon.getImage();
			Image imageIcon = image.getScaledInstance(360, 175,  java.awt.Image.SCALE_SMOOTH);
			item.setIcon(new ImageIcon(imageIcon));
		}
		
		/* Iterates the image loading buttons to add actionListener */
		for (JButton item : imageButtons.values()) {
			item.addActionListener(this);
		}
		
		/* Loads the (?) help symbol and applys them to the help JLabels */
		java.net.URL imgURL = getClass().getResource("/resources/info.png");
		ImageIcon icon = new ImageIcon(imgURL);
		Image image = icon.getImage();
		Image helpIcon = image.getScaledInstance(16, 16,  java.awt.Image.SCALE_SMOOTH);
		help1.setIcon(new ImageIcon(helpIcon));
		help2.setIcon(new ImageIcon(helpIcon));
		help3.setIcon(new ImageIcon(helpIcon));
		help4.setIcon(new ImageIcon(helpIcon));
		help5.setIcon(new ImageIcon(helpIcon));
		help6.setIcon(new ImageIcon(helpIcon));
		
		/* Adds the author name checkboxes to a group and selects the default tick */
		usernameTick.setSelected(true);
		buttonGroup.add(usernameTick);
		buttonGroup.add(fullnameTick);
		buttonGroup.add(firstNameTick);
		
		// Action & Key Listeners
		titleInput.addKeyListener(this);
		loadIntroImg.addActionListener(this);
		loadMainImg.addActionListener(this);
		help1.addMouseListener(this);
		help2.addMouseListener(this);
		help3.addMouseListener(this);
		help4.addMouseListener(this);
		help5.addMouseListener(this);
		help6.addMouseListener(this);
		
		// Size
		titleInput.setPreferredSize(new Dimension(400, 25));
		wfurlInput.setPreferredSize(new Dimension(400, 25));
		introScroll.setPreferredSize(new Dimension(550, 150));
		introImgArea.setPreferredSize(new Dimension(200, 200));
		loadMainArea.setPreferredSize(new Dimension(360, 200));
		
		// Line wrap
		introInput.setLineWrap(true);
		introInput.setWrapStyleWord(false);
		
		// Style
		title.setForeground(Settings.SITE_GREEN);
		title.setFont(Settings.TITLEFONT);
		titleInput.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Settings.SITE_ORANGE));
		wfurlInput.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Settings.SITE_ORANGE));
		instructions1.setForeground(Settings.SITE_GREEN);
		instructions1.setFont(Settings.SUBTITLEFONT);
		instructions2.setForeground(Settings.SITE_GREEN);
		instructions2.setFont(Settings.SUBTITLEFONT);
		instructions3.setForeground(Settings.SITE_GREEN);
		instructions3.setFont(Settings.SUBTITLEFONT);
		instructions4.setForeground(Settings.SITE_GREEN);
		instructions4.setFont(Settings.SUBTITLEFONT);
		instructions5.setForeground(Settings.SITE_GREEN);
		instructions5.setFont(Settings.SUBTITLEFONT);
		instructions6.setForeground(Settings.SITE_GREEN);
		instructions6.setFont(Settings.SUBTITLEFONT);
		introScroll.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Settings.SITE_ORANGE));
		introImgArea.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Settings.SITE_ORANGE));
		loadMainArea.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Settings.SITE_ORANGE));
		
		/* Adds the top areas (title, name, title, type, intro & main image) to the JPanel */
		gbc.weighty = 0.1;
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.gridwidth = 4;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(title, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(instructions1, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(usernameSelect, gbc);
		
		gbc.insets = new Insets(10, 0, 0, 15);
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		add(usernameTick, gbc);
		
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(fullnameSelect, gbc);
		
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 2;
		add(fullnameTick, gbc);
		
		gbc.insets = new Insets(10, 30, 0, 0);
		gbc.gridx = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(firstNameSelect, gbc);
		
		gbc.insets = new Insets(10, 155, 0, 0);
		gbc.gridx = 3;
		add(firstNameTick, gbc);
		
		
		gbc.insets = new Insets(10, 185, 0, 0);
		add(help1, gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		
		i++;
		
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(instructions2, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(titleLabel, gbc);
		
		gbc.insets = new Insets(10, 5, 0, 0);
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 1;
		add(titleInput, gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(wfurlLabel, gbc);
		
		gbc.insets = new Insets(10, 5, 0, 0);
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 1;
		add(wfurlInput, gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 3;
		gbc.insets = new Insets(10, 0, 0, 130);
		add(help2, gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(instructions3, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(typeLabel, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 1;
		add(typeInput, gbc);
		
		gbc.gridx = 2;
		gbc.insets = new Insets(10, 5, 0, 0);
		add(help3, gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(instructions4, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, 25, 0, 0);
		add(help4, gbc);
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(10, 0, 0, 5);
		add(introLabel, gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 1;
		add(introScroll, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(loadIntroImg, gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		add(introImgArea, gbc);
		
		i++;
		
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(instructions5, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(loadMainImg, gbc);
		
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		add(loadMainArea, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridwidth = 1;
		gbc.gridx = 3;
		gbc.insets = new Insets(10, 0, 0, 170);
		add(help5, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(20, 0, 10, 0);
		add(instructions6, gbc);	
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(20, 0, 10, 80);
		add(help6, gbc);
	}
	
	/* When called (supplied with hashtable ID) this method generates a *
	 * new TextArea and label for the user to write in.  This method is *
	 * called by the  CreateArticleToolbar  class  at the bottom of the 
	 * page. The JPanel size is extended when called. */
	
	public static void GenerateTextArea(int id)
	{
		areaHeight += 210;
		Home.createArticle.setPreferredSize(new Dimension(800, areaHeight));
		i++; 
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(10, 0, 0, 5);
		gbc.anchor = GridBagConstraints.LINE_END;
		Home.createArticle.add(textLabels.get(id), gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weighty = 10;
		Home.createArticle.add(textAreas.get(id), gbc);
	}
	
	/* When called (supplied with hashtable ID) this method generates a *
	 * new image area and button for the user to select an image,  like *
	 * the method above this is called by the toolbar  at  the  bottom. *
	 * The JPanel size is extended when called. */
	
	public static void GenerateImageArea(int id)
	{
		areaHeight += 190;
		Home.createArticle.setPreferredSize(new Dimension(800, areaHeight));
		
		i++;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, 0, 0, 0);
		Home.createArticle.add(imageButtons.get(id), gbc);
		
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		gbc.weighty = 10;
		Home.createArticle.add(imageAreas.get(id), gbc);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (imageButtons.contains(e.getSource())) // Finds the key for the imageButton pressed
		{
			for (int i = 0; i < imageButtons.size(); i++)
			{
				if (imageButtons.get(i) == e.getSource())
				{
					System.out.println(i);
					CreateArticleController.LoadImage(i); // Loads image to necessary image JLabel
				}
			}
		}
		else if (e.getSource() == loadIntroImg) // Loads intro image to intro JLabel
		{
			CreateArticleController.LoadIntroImage();
		}
		else if (e.getSource() == loadMainImg) // Loads main image to main JLable
		{
			CreateArticleController.LoadMainImage();
		}
	}
	
	/* The wfurl is an array of characters, every time user types a character gets added *
	 * onto the array and the wfurl input box gets rebuilt  with  the  new  array.  This *
	 * method is called by the keyPressed listener. */
	
	public static void SetWfurl(char c)
	{
		wfurlArray.add(c);
		wfurlInput.setText("");
		for (Character item : wfurlArray) { 
		    wfurlInput.setText(wfurlInput.getText() + item.toString());
		}
	}
	
	/* This method works similar to the SetWfurl but doesn't take in a character, it *
	 * simply rebuilds the wfurl in the box, the method that calls this method first *
	 * removes a character (for when backspace is pressed)  then  calls  the  update *
	 * method to save changes. */
	
	public static void UpdateWfurl()
	{
		wfurlInput.setText("");
		for (Character item : wfurlArray) { 
		    wfurlInput.setText(wfurlInput.getText() + item.toString());
		}
	}

	@Override
	public void keyTyped(KeyEvent ke)
	{
	}

	/* Runs whenever a key is pressed in the title input box, builds the *
	 * wfurl for the user removing any unwanted characters. */
	@Override
	public void keyPressed(KeyEvent kp)
	{
		if (kp.getSource() == titleInput)
		{
			Character pressed = kp.getKeyChar();
			String temp = "";
			temp += pressed;
			if (temp.matches("^[-a-zA-Z0-9]+")) // Only allow A-Za-z0-9- symbols in wfurl
			{
				char c = Character.toLowerCase(pressed); // Make all chars lower case
				SetWfurl(c);	
			}
			else if (temp.matches("^[ ]+")) // Replace any spaces with dashes (-)
			{
				SetWfurl('-');
			}
		}
		if(kp.getKeyCode() == KeyEvent.VK_BACK_SPACE) // Handle backspaces by removing last char from wfurlArray
		{
			if (wfurlArray.size() > 0)
			{
				wfurlArray.remove(wfurlArray.size() - 1);
				UpdateWfurl();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}

	/* When the (?) help icons are clicked, the corresponding advice is shown in the form *
	 * of a popup window. */
	@Override
	public void mouseClicked(MouseEvent me)
	{
		if (me.getSource() == help1)
		{
			JOptionPane.showMessageDialog(Home.toolbar, "<html><body>Select whether you want to publish your article <br>under your username, first name or your full<br> name (first and surname).</body></html>");
		}
		else if (me.getSource() == help2)
		{
			JOptionPane.showMessageDialog(Home.toolbar, "<html><body>Article titles should be short and snappy, you may use symbols <br>but use them sparingly.<br><br>A web-friendly-url is what's displayed in the URL box at<br>the top of the users web browser.<br>It should contain NO symbols and NO spaces, only lower case letters,<br>numbers, and dashes. Only edit it if absolutely necessary.</body></html>");
		}
		else if (me.getSource() == help3)
		{
			JOptionPane.showMessageDialog(Home.toolbar, "<html><body>Select the category that best fits your article, if you<br>find that the categories don't quite fit your article, send us a<br>message in the contact section and we'll set you up a new category.</body></html>");
		}
		else if (me.getSource() == help4)
		{
			JOptionPane.showMessageDialog(Home.toolbar, "<html><body>Each article requires a short introduction and 200x200 pixel icon image<br>for the homepage. <br><br>NOTE: Ensure that the image you're using is not copyright.</body></html>");
		}
		else if (me.getSource() == help5)
		{
			JOptionPane.showMessageDialog(Home.toolbar, "<html><body>Each article requires a main image to be displayed at the<br>top of the article page.<br><br>NOTE: Ensure that the image you're using is not copyright.</body></html>");
		}
		else if (me.getSource() == help6)
		{
			JOptionPane.showMessageDialog(Home.toolbar, "<html><body>" +
					"An article is made up of multiple text and image areas. You can use the buttons<br>" +
					"at the bottom of the page in the toolbar to add new areas. It's advised that you<br>" +
					"start with a text area to explain what your article is all about.<br><br>" +
					"You can have up to 17 areas in total.<br><br>" +
					"Images are displayed int he center of the page and should be no larger than<br>" +
					"720 * 350 pixels. The box the image is displayed in is to aspect ratio so<br>" +
					"make sure your image looks good in it, although the actual image will be<br>" +
					"twice as big.<br><br>" +
					"Text areas should be no more than 20,000 letters, although if you're<br>" +
					"planning on writing that much, you should probably split it up over<br>" +
					"multiple text areas and use images to make it more appealing.</body></html>");
		}
	}

	@Override
	public void mousePressed(MouseEvent me)
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent me)
	{
		
	}

	/* When the mouse enters one of the (?) help icons, the corresponding *
	 * elements light up orange on mouse over.  */
	@Override
	public void mouseEntered(MouseEvent me) 
	{
		if (me.getSource() == help1)
		{
			usernameSelect.setForeground(Settings.SITE_ORANGE);
			fullnameSelect.setForeground(Settings.SITE_ORANGE);
			firstNameSelect.setForeground(Settings.SITE_ORANGE);
		}
		else if (me.getSource() == help2)
		{
			titleInput.setBackground(Settings.SITE_ORANGE);
			wfurlInput.setBackground(Settings.SITE_ORANGE);
		}
		else if (me.getSource() == help3)
		{
			typeLabel.setForeground(Settings.SITE_ORANGE);
		}
		else if (me.getSource() == help4)
		{
			introLabel.setForeground(Settings.SITE_ORANGE);
			loadIntroImg.setForeground(Settings.SITE_ORANGE);
		}
		else if (me.getSource() == help5)
		{
			loadMainImg.setForeground(Settings.SITE_ORANGE);
		}
		else if (me.getSource() == help6)
		{
			instructions6.setForeground(Settings.SITE_ORANGE);
		}
	}

	/* When the user moves his mouse out of the path of the (?) *
	 * help  icon  the  item  goes  back  to being its original *
	 * colour. */
	@Override
	public void mouseExited(MouseEvent me)
	{
		if (me.getSource() == help1)
		{
			usernameSelect.setForeground(Color.BLACK);
			fullnameSelect.setForeground(Color.BLACK);
			firstNameSelect.setForeground(Color.BLACK);
		}
		else if (me.getSource() == help2)
		{
			titleInput.setBackground(Color.WHITE);
			wfurlInput.setBackground(Color.WHITE);
		}
		else if (me.getSource() == help3)
		{
			typeLabel.setForeground(Color.BLACK);
		}
		else if (me.getSource() == help4)
		{
			introLabel.setForeground(Color.BLACK);
			loadIntroImg.setForeground(Color.BLACK);
		}
		else if (me.getSource() == help5)
		{
			loadMainImg.setForeground(Color.BLACK);
		}
		else if (me.getSource() == help6)
		{
			instructions6.setForeground(Settings.SITE_GREEN);
		}
	}
}
