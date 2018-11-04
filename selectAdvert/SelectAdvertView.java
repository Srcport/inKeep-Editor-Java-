package selectAdvert;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import startup.Home;
import config.Settings;

public class SelectAdvertView extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	private static JLabel filler = new JLabel();
	private static JLabel title = new JLabel("Select an Advert");
	
	protected static JTextArea instructions = new JTextArea("Here you have the opportunity to set adverts for your articles, you need to load your articles then load just your ads or everyones ads. You can upload adverts in the Register Ad section.");
	
	private static JLabel articleLabel = new JLabel("Click To Load Articles:");
	private static JButton articleBtn = new JButton("Load Now");
	
	private static String[] columnNames = {"ID", "Title", "Type", "Used Advert ID", "Date Published"};
	protected static DefaultTableModel dtm = new DefaultTableModel(null, columnNames);
	protected static JTable table = new JTable(dtm);
	protected static JScrollPane tableScroll = new JScrollPane(table);
	
	private static JLabel articleIdLabel = new JLabel("Select the ID of the article:");
	protected static JComboBox<Integer> articleIds = new JComboBox<Integer>();
	private static JButton articleLoad = new JButton("Select this Article");
	protected static int selectedArticle = 0;
	
	private static JLabel loadAdsLabel = new JLabel("Do you want to load all ads or just the ones you submitted?");
	private static JButton loadAllAds = new JButton("Load All Ads");
	private static JButton loadUserAds = new JButton("Load Just My Ads");
	
	private static String[] columnNames2 = {"ID", "Title", "URL", "Date Uploaded"};
	protected static DefaultTableModel dtm2 = new DefaultTableModel(null, columnNames2);
	protected static JTable table2 = new JTable(dtm2);
	protected static JScrollPane tableScroll2 = new JScrollPane(table2);
	
	private static JLabel advertIdLabel = new JLabel("Select the ID of the advert:");
	protected static JComboBox<Integer> advertIds = new JComboBox<Integer>();
	private static JButton advertLoad = new JButton("Select this Advert");
	protected static int selectedAdvert = 0;

	public SelectAdvertView()
	{
		setPreferredSize(Settings.PANEL_AREA);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		int i = 0;
		
		TableColumnModel colMd1 = table.getColumnModel();
		colMd1.getColumn(0).setPreferredWidth(20);
		colMd1.getColumn(1).setPreferredWidth(200);
		colMd1.getColumn(3).setPreferredWidth(20);
		colMd1.getColumn(4).setPreferredWidth(90);
		
		TableColumnModel colMd2 = table2.getColumnModel();
		colMd2.getColumn(0).setPreferredWidth(10);
		colMd2.getColumn(1).setPreferredWidth(200);
		colMd2.getColumn(2).setPreferredWidth(200);
		colMd2.getColumn(3).setPreferredWidth(70);
		
		
		// Action Listener
		articleBtn.addActionListener(this);
		articleLoad.addActionListener(this);
		loadAllAds.addActionListener(this);
		loadUserAds.addActionListener(this);
		advertLoad.addActionListener(this);
		
		// Visibility
		articleIdLabel.setVisible(false);
		articleIds.setVisible(false);
		articleLoad.setVisible(false);
		loadAllAds.setVisible(false);
		loadUserAds.setVisible(false);
		loadAdsLabel.setVisible(false);
		tableScroll2.setVisible(false);
		advertLoad.setVisible(false);
		advertIdLabel.setVisible(false);
		advertIds.setVisible(false);
		
		// Style
		title.setForeground(Settings.SITE_GREEN);
		title.setFont(Settings.TITLEFONT);
		instructions.setBorder(Settings.SMALL_DOUBLE_BORDER);
		instructions.setEditable(false);
		instructions.setOpaque(false);
		tableScroll.setBorder(Settings.THIN_ORANGE_BORDER);
		tableScroll2.setBorder(Settings.THIN_ORANGE_BORDER);
		table.setEnabled(false);
		tableScroll2.setEnabled(false);
		
		// Size
		instructions.setPreferredSize(new Dimension(770, 40));
		tableScroll.setPreferredSize(new Dimension(770, 100));
		tableScroll2.setPreferredSize(new Dimension(770, 100));
		
		// Wrap
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(10, 0, 0, 0);
		add(title, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, 0, 0, 0);
		add(instructions, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(articleLabel, gbc);
		
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(articleBtn, gbc);
		
		i++;
		
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(tableScroll, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(articleIdLabel, gbc);
		
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(articleIds, gbc);
		
		gbc.insets = new Insets(10, 70, 0, 0);
		gbc.gridx = 1;
		add(articleLoad, gbc);
		
		i++;
		
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 2;
		add(loadAdsLabel, gbc);
		
		i++;
		
		gbc.gridx = 0;
		gbc.gridy = i;
		add(loadAllAds, gbc);
		
		gbc.gridx = 1;
		add(loadUserAds, gbc);
		
		i++;
		
		gbc.gridwidth = 4;
		gbc.gridx = 0;
		gbc.gridy = i;
		add(tableScroll2, gbc);
		
		i++;
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.anchor = GridBagConstraints.LINE_END;
		add(advertIdLabel, gbc);
		
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		add(advertIds, gbc);
		
		gbc.insets = new Insets(10, 70, 0, 0);
		gbc.gridx = 1;
		add(advertLoad, gbc);
		
		i++;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridx = 0;
		gbc.gridy = i;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.weighty = 10;
		add(filler, gbc);
				
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == articleBtn)
		{
			articleIds.removeAllItems();
			SelectAdvertConnect.GetUsersArticles();
			ShowArticleIdSelectArea();
		}
		else if (e.getSource() == articleLoad)
		{
			selectedArticle = SelectAdvertConnect.articleNums.get((Integer) articleIds.getSelectedItem());
			loadAdsLabel.setVisible(true);
			loadAllAds.setVisible(true);
			loadUserAds.setVisible(true);
		}
		else if (e.getSource() == loadAllAds)
		{
			advertIds.removeAllItems();
			SelectAdvertConnect.GetAllAdverts();
			tableScroll2.setVisible(true);
			advertIdLabel.setVisible(true);
			advertIds.setVisible(true);
			advertLoad.setVisible(true);
			this.revalidate();
		}
		else if (e.getSource() == loadUserAds)
		{
			advertIds.removeAllItems();
			SelectAdvertConnect.GetUsersAdverts();
			tableScroll2.setVisible(true);
			advertIdLabel.setVisible(true);
			advertIds.setVisible(true);
			advertLoad.setVisible(true);
			this.revalidate();
		}
		else if (e.getSource() == advertLoad)
		{
			selectedAdvert = (Integer) advertIds.getSelectedItem();
			SelectAdvertConnect.SetAdvert(selectedAdvert, selectedArticle);
			JOptionPane.showMessageDialog(Home.toolbar, "Your have successfully set an advert for your article.");
		}
	}
	
	private static void ShowArticleIdSelectArea()
	{
		for (int i : SelectAdvertConnect.articleNums.keySet())
		{
			articleIds.addItem(i);
		}
		articleIds.revalidate();
		articleIdLabel.setVisible(true);
		articleIds.setVisible(true);
		articleLoad.setVisible(true);
	}

}
