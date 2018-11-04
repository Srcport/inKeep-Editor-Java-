
package startup;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import javax.swing.SwingUtilities;

import com.apple.eawt.Application;

import config.Settings;

public class Start
{
	public static Home home;
	static java.net.URL imgURL; // java.net.URL imgURL
	
	public static void main(String[] args) throws InterruptedException, InvocationTargetException, MalformedURLException, IOException
	{	
	    new Settings();
	    
	    if (SetApplicationIcons()) {
		
	    }
	   
	    if (StartGUI()) {
		
	    }
	}
	
	private static boolean SetApplicationIcons()
	{
	    System.setProperty("apple.awt.brushMetalLook", "true"); // Sets the apple background property
	    Application application = Application.getApplication();
	    imgURL = Home.getWindows().getClass().getResource("/resources/dock2.png");
	    Image image = Toolkit.getDefaultToolkit().getImage(imgURL); // Sets apple icon image
	    application.setDockIconImage(image);
	    
	    return true;
	}
	
	private static boolean StartGUI()
	{
		try {
		SwingUtilities.invokeAndWait(new Runnable() {
		    public void run() {
			home = new Home();
		    }
		});
		return true;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

}
