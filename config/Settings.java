package config;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import startup.CheckNotifications;

import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;

import createArticle.ArticleSelectConnect;
import createArticle.CreateArticleView;

public class Settings {

    /* Standard colours */
    public static final Color SITE_GREEN = new Color(0, 165, 77);
    public static final Color SITE_TRANSPARENT_GREEN = new Color(0, 165, 77,
	    150);
    public static final Color SITE_ORANGE = new Color(253, 139, 37);
    public static final Color SITE_GRAY = new Color(238, 238, 238);
    public static final Color SITE_TRANSPARENT = new Color(255, 255, 255, 0);

    /* Application Data */
    public static final double VERSION = 2.4;
    public static final String TITLE = "inKeep editor Beta v" + VERSION + " \u00a9 2014";
    public static final String LOGIN_TITLE = "Logic to inKeep Website Handler";

    /* Sizes */
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Dimension PANEL_AREA = new Dimension(800, 528); // Default
									// area
									// for
									// JPanels
    public static final Dimension INPUT_AREA = new Dimension(200, 25); // default
								       // input
								       // area
								       // for
								       // JTextFields

    /* Fonts */
    public static final Font TITLEFONT = new Font("sansserif", Font.BOLD, 22);
    public static final Font SUBTITLEFONT = new Font("sansserif", Font.BOLD, 18);

    /* Borders */
    private static Border Outer = BorderFactory.createMatteBorder(1, 5, 1, 1, SITE_ORANGE);
    private static Border Big_Inner = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private static Border Small_Inner = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    public static Border BIG_DOUBLE_BORDER = BorderFactory.createCompoundBorder(Outer, Big_Inner);
    public static Border SMALL_DOUBLE_BORDER = BorderFactory.createCompoundBorder(Outer, Small_Inner);

    public static Border THIN_ORANGE_BORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, SITE_ORANGE);
    public static Border THIN_GREEN_BORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, SITE_GREEN);
    public static Border BIG_EMPTY_INNER_BORDER = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    public static Border SMALL_EMPTY_INNER_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);

    /* User details - gets set by AccountController.SetUserDetails(); */
    public static int id = 0;
    public static String email = "guest_email";
    public static String username = "guest_username";
    public static String firstName = "guest_firstName";
    public static String surname = "guest_surname";
    public static String dob = "guest_dob";
    public static String dateJoined = "guest_dateJoined";

    public Settings() {
	// Load the account details (starts at app startup)
	new Thread() {
	    public void run() {
		System.out.println("Starting User Connection ...");
		new UserCon();
	    }
	}.start();

	// Load the article details (starts at app startup)
	new Thread() {
	    public void run() {
		System.out.println("Starting Article Connection ...");
		new ArticleCon();
		ArticleSelectConnect.GetArticleTypes();
		CreateArticleView.typeInput.revalidate();
		CreateArticleView.typeInput.repaint();
		for (String s : CreateArticleView.types) {
		    CreateArticleView.typeInput.addItem(s);
		}
	    }
	}.start();

	// Load the notifications (starts at app startup)
	new Thread() {
	    public void run() {
		System.out.println("Starting Notifications Connection ...");
		new NotifCon();
		CheckNotifications.GetLatestNotifications();
		CheckNotifications.SetLatestNotifications();
		CheckNotifications.CheckUpdateServer();
	    }
	}.start();

	SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
	SpellChecker.registerDictionaries(
		this.getClass().getResource("/dictionary"), "en");
	// TODO: Figure out a way to get rid of the other languages.
    }
}