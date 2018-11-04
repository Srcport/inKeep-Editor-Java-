/* This screen is displayed when the user clicks the submit button, it shows the submit process */

package createArticle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import startup.Home;

import config.Settings;

public class CreateArticleStatus extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	private static JTextArea textArea = new JTextArea();
	private static JScrollPane textScroll = new JScrollPane(textArea);
	private static JLabel title = new JLabel("Article Submission");
	private static JButton done = new JButton("Done");

	public CreateArticleStatus()
	{
		setLayout(new BorderLayout());
		setPreferredSize(Settings.PANEL_AREA);
		setBorder(Settings.BIG_EMPTY_INNER_BORDER);
		
		// Style
		title.setForeground(Settings.SITE_GREEN);
		title.setFont(Settings.TITLEFONT);
		textScroll.setBorder(Settings.THIN_ORANGE_BORDER);
		textArea.setMargin(new Insets(5, 5, 5, 5));
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		title.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		done.addActionListener(this);
		done.setSize(new Dimension(200, 50));
		
		add(title, BorderLayout.PAGE_START);
		add(textScroll, BorderLayout.CENTER);
		add(done, BorderLayout.PAGE_END);
	}
	
	public static void append(String text)
	{
		textArea.setText(textArea.getText() + text);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == done)
		{
			textArea.setText("");
			Home.HideAllButOne(Home.createArticleScroll);
		}
	}

}
