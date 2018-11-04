package registerAd;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import startup.Home;

public class RegisterAdController
{
	
	protected static String imageLoc = "";
	
	protected static void SubmitAdvert()
	{
		String title = RegisterAdView.titleInput.getText();
		String url = RegisterAdView.urlInput.getText();
		String desc = RegisterAdView.descInput.getText();
		int type = 999;
		
		if (RegisterAdView.step2.getText().equals("[2a]: Pick a URL, Image and Description for the book."))
		{
		    type = 0;
		}
		else if (RegisterAdView.step2.getText().equals("[2b]: Select the YouTube Embed URL and description for the video."))
		{
		    type = 1;
		}
		else if (RegisterAdView.step2.getText().equals("[2c]: Select a URL, Image and Description for the product."))
		{
		    type = 2;
		}
		
		if (CheckAdvertData(title, url, desc))
		{
			RegisterAdConnect.SubmitAdvert(title, url, imageLoc, desc, type);
			JOptionPane.showMessageDialog(Home.toolbar, "Your advert has been successfully submitted.");
		}
	}
	
	protected static boolean CheckAdvertData(String title, String url, String desc)
	{
		if (title.length() < 1000)
		{
			if (url.length() < 2000)
			{
				if (desc.length() < 20000)
				{
					File f = new File(imageLoc);
					if (f.length() < 1048576) 
					{
						if (!title.equals("") || !desc.equals(""))
						{
							return true;
						} else {
							JOptionPane.showMessageDialog(Home.toolbar, "The title and description boxes cannot be empty.");
						}
					} else {
						JOptionPane.showMessageDialog(Home.toolbar, "Your image file is too big, it must be less than 1MB.");
					}
				} else {
					JOptionPane.showMessageDialog(Home.toolbar, "Your description is too long, it must be less than 20,000 characters.");
				}
			} else {
				JOptionPane.showMessageDialog(Home.toolbar, "Your URL is too long, it must be less than 2000 characters.");
			}
		} else {
			JOptionPane.showMessageDialog(Home.toolbar, "Your title is too long, it must be less than 1000 characters.");
		}
		
		return false;
	}
	
	protected static void GetAdImage(int width, int height)
	{
		JFileChooser fc = new JFileChooser();
		int value = fc.showOpenDialog(Home.toolbar);
		if (value == JFileChooser.APPROVE_OPTION) 
		{
			imageLoc = fc.getSelectedFile().toString();
			
			if (imageLoc.endsWith(".jpg") || imageLoc.endsWith(".png") || imageLoc.endsWith(".jpeg"))
			{
				ImageIcon icon = new ImageIcon(imageLoc);
				Image image = icon.getImage();
				Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
				RegisterAdView.imageDisplay.setIcon(new ImageIcon(newimg));
			}
			else {
				JOptionPane.showMessageDialog(Home.toolbar, "You can only upload .png, .jpg or .jpeg files.");
			}
		}
	}
}
