package contact;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import startup.Home;

import com.inet.jortho.SpellChecker;

import config.Settings;

public class ContactView extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	private static JLabel title = new JLabel("Contact Us");
	
	private static JButton back = new JButton("Back");
	
	private static JTextArea instructions = new JTextArea();

	private static JLabel subject = new JLabel("Subject");
	protected static JTextArea subjectInput = new JTextArea();
	protected static JScrollPane subjectScroll = new JScrollPane(subjectInput,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private static JLabel message = new JLabel("Message");
	protected static JTextArea messageInput = new JTextArea();
	protected static JScrollPane messageScroll = new JScrollPane(messageInput,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private static JButton submit = new JButton("Submit");
	
	private JLabel loading = new JLabel();
	
	public ContactView()
	{
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(Settings.PANEL_AREA));
		GridBagConstraints gbc = new GridBagConstraints();
		int i = 0;
		
		SpellChecker.register(subjectInput);
		SpellChecker.register(messageInput);
		
		// Loading bar
		ImageIcon icon = new ImageIcon("src/resources/loadbar.gif");
		Image loadingIcon = icon.getImage();
		loading.setIcon(new ImageIcon(loadingIcon));
		loading.setVisible(false);
		
		// Text
		instructions.setText("You can send us a message through the text boxes below. If you need any assistance we will be happy to help or if you want to suggest a feature or anything else, send us a message. If you're not already logged in you'll be prompted for an email address, it's strongly recommended that you enter one if you want a reply.");
		// Size
		instructions.setPreferredSize(new Dimension(780,65));
		subjectScroll.setPreferredSize(new Dimension(780, 45));
		messageScroll.setPreferredSize(new Dimension(780, 230));
		
		// Line wrap
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		subjectInput.setLineWrap(true);
		subjectInput.setWrapStyleWord(true);
		messageInput.setLineWrap(true);
		messageInput.setWrapStyleWord(true);
		
		// Style
		title.setFont(Settings.TITLEFONT);
		title.setForeground(Settings.SITE_GREEN);
		instructions.setOpaque(false);
		subject.setFont(Settings.SUBTITLEFONT);
		subject.setForeground(Settings.SITE_ORANGE);
		message.setFont(Settings.SUBTITLEFONT);
		message.setForeground(Settings.SITE_ORANGE);
		
		// Margin
		instructions.setMargin(new Insets(5, 5, 5, 5));
		subjectInput.setMargin(new Insets(5, 5, 5, 5));
		messageInput.setMargin(new Insets(5, 5, 5, 5));
		
		// Border
		instructions.setBorder(Settings.BIG_DOUBLE_BORDER);
		subjectScroll.setBorder(Settings.THIN_ORANGE_BORDER);
		messageScroll.setBorder(Settings.THIN_ORANGE_BORDER);

		
		// Edit
		instructions.setEditable(false);
		
		// ActionListeners
		back.addActionListener(this);
		submit.addActionListener(this);
		
		gbc.weighty = 0.1;
		gbc.weightx = 1.0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(10, 10, 0, 0);
		add(title, gbc);
				
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(back, gbc);
		
		i++;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(instructions, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(subject, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(subjectScroll, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(message, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(messageScroll, gbc);
		
		i++;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(submit, gbc);
		add(loading, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == back)
		{
			Home.HideAllButOne(Home.login);
		}
		else if (e.getSource() == submit)
		{
			submit.setVisible(false);
			loading.setVisible(true);
			new Thread()
			{
			    public void run() {
					new ContactConnect();
					if (Settings.id == 0)
					{
						String response = JOptionPane.showInputDialog(Home.toolbar, "Please enter your email address.", "Contact Us", JOptionPane.QUESTION_MESSAGE);
						if (response == null)
						{
							response = "not provided";
						}
						ContactConnect.InsertMessage(subjectInput.getText(), messageInput.getText(), response);
					}
					else 
					{
						ContactConnect.InsertMessage(subjectInput.getText(), messageInput.getText(), Settings.email);
					}
					submit.setVisible(true);
					loading.setVisible(false);
					ContactController.MessageRecieved();
			    }
			}.start();
		}
	}

}
