package registerAd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.inet.jortho.SpellChecker;

import config.Settings;

public class RegisterAdView extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	private static JLabel title = new JLabel("Register an Ad");
	private static JLabel filler = new JLabel();
	private static JLabel step1  = new JLabel("[1]: Choose what type of product you are registering.");
	private static JButton bookBtn = new JButton("Book / eBook");
	private static JButton youtubeBtn = new JButton("YouTube Video");
	private static JButton productBtn = new JButton("Other Product");
	
	protected static JLabel step2 = new JLabel("[2a]: Pick a URL, Image and Description for the book.");
		
	protected static JTextArea instructions = new JTextArea("You can currently submit a book, product or YouTube video as an advert at the bottom of your article. To do this you must first regsiter the product here, you and others will then be able to link to the product when creating articles, when you do, a small area will appear at the bottom of your article advertising the product.\n\nIf you're an author of an eBook or the creator of the product, this service will help drive traffic to your product page and hopefulyl result in a few extra sales.\n\nPlease note that you are solely responsible for the product images you upload and link to, it's always better if you own the rights to the product.");

	private static JLabel titleLabel = new JLabel("Title:");
	protected static JTextField titleInput = new JTextField();
	
	private static JLabel urlLabel = new JLabel("URL:");
	protected static JTextField urlInput = new JTextField();
	
	protected static JLabel imageDisplay = new JLabel();
	private static JButton loadImage = new JButton("Load Image:");
	
	protected static JLabel descLabel = new JLabel("Description:");
	protected static JTextArea descInput = new JTextArea();
	protected static JScrollPane descScroll = new JScrollPane(descInput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	protected static int areaHeight = 940;
	
	protected static JButton submit = new JButton("Submit");
	protected static int type = 0;
	
	public RegisterAdView()
	{
		setPreferredSize(new Dimension(800, areaHeight));
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		int i = 0;
		
		SpellChecker.register(titleInput);
		SpellChecker.register(descInput);
		
		// Size
		instructions.setPreferredSize(new Dimension(770, 153));
		urlInput.setPreferredSize(new Dimension(600, 25));
		titleInput.setPreferredSize(new Dimension(600, 25));
		imageDisplay.setPreferredSize(new Dimension(188, 300)); // 750 1200
		descScroll.setPreferredSize(new Dimension(600, 200));
		
		// Wrap
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		descInput.setLineWrap(true);
		descInput.setWrapStyleWord(true);

		// Action Listeners
		bookBtn.addActionListener(this);
		youtubeBtn.addActionListener(this);
		productBtn.addActionListener(this);
		loadImage.addActionListener(this);
		submit.addActionListener(this);
		
		// Style
		title.setFont(Settings.TITLEFONT);
		title.setForeground(Settings.SITE_GREEN);
		instructions.setMargin(new Insets(5, 5, 5, 5));
		instructions.setBorder(Settings.SMALL_DOUBLE_BORDER);
		instructions.setEditable(false);
		instructions.setOpaque(false);
		step1.setFont(Settings.SUBTITLEFONT);
		step1.setForeground(Settings.SITE_GREEN);
		step2.setFont(Settings.SUBTITLEFONT);
		step2.setForeground(Settings.SITE_GREEN);
		bookBtn.setEnabled(false);
		imageDisplay.setBorder(Settings.THIN_ORANGE_BORDER);
		descScroll.setBorder(Settings.THIN_ORANGE_BORDER);

		filler.setOpaque(true);
		filler.setBackground(Color.RED);
		
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(10, 10, 0, 0);
		add(title, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, 10, 0, 0);
		add(instructions, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(step1, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(bookBtn, gbc);
		
		gbc.gridx = 1;
		add(youtubeBtn, gbc);
		
		gbc.gridx = 2;
		add(productBtn, gbc);
		
		i++;
		
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(step2, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(titleLabel, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		add(titleInput, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(urlLabel, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		add(urlInput, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(loadImage, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 3;
		gbc.gridx = 1;
		add(imageDisplay, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 1;
		add(descLabel, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 1;
		gbc.gridwidth = 3;
		add(descScroll, gbc);

		i++;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(submit, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bookBtn)
		{
			ShowAllButOneButtons(bookBtn);
			step2.setText("[2a]: Select a URL, Image and Description for the book.");
			urlLabel.setText("URL:");
			imageDisplay.setPreferredSize(new Dimension(188, 300));
			imageDisplay.setVisible(true);
			loadImage.setVisible(true);
			imageDisplay.setIcon(null);
			RegisterAdController.imageLoc = "";
			if (areaHeight == 640)
			{
				areaHeight += 300;
				this.setPreferredSize(new Dimension(800, areaHeight));
			}
			type = 0;
		}
		else if (e.getSource() == youtubeBtn)
		{
			ShowAllButOneButtons(youtubeBtn);
			step2.setText("[2b]: Select the YouTube Embed URL and description for the video.");
			urlLabel.setText("Embed URL:");
			imageDisplay.setVisible(false);
			loadImage.setVisible(false);
			areaHeight -= 300;
			this.setPreferredSize(new Dimension(800, areaHeight));
			type = 1;
			imageDisplay.setIcon(null);
			RegisterAdController.imageLoc = "";
		}
		else if (e.getSource() == productBtn)
		{
			ShowAllButOneButtons(productBtn);
			step2.setText("[2c]: Select a URL, Image and Description for the product.");
			urlLabel.setText("URL:");
			imageDisplay.setPreferredSize(new Dimension(500, 300));
			imageDisplay.setVisible(true);
			loadImage.setVisible(true);
			if (areaHeight == 640)
			{
				areaHeight += 300;
				this.setPreferredSize(new Dimension(800, areaHeight));
			}
			type = 2;
			imageDisplay.setIcon(null);
			RegisterAdController.imageLoc = "";
		}
		else if (e.getSource() == submit)
		{
			RegisterAdController.SubmitAdvert();
			urlInput.setText("");
			titleInput.setText("");
			descInput.setText("");
			imageDisplay.setIcon(null);
		}
		else if (e.getSource() == loadImage)
		{
			if (type == 0)
			{
				RegisterAdController.GetAdImage(188, 300);
			}
			else if (type == 2)
			{
				RegisterAdController.GetAdImage(500, 300);
			}
		}
	}
	
	public static void ShowAllButOneButtons(JButton showMe) // Hides one JButton, shows the rest
	{
		bookBtn.setEnabled(true);
		youtubeBtn.setEnabled(true);
		productBtn.setEnabled(true);
		
		showMe.setEnabled(false);
	}
}
