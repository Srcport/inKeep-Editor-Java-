package config;

import javax.swing.JOptionPane;

import startup.Home;

public class General {
    // Takes in a string and removes everything after the last occurance of a
    // forward slash: /
    public static String CutStringAfterSlash(String str) {
	String newStr = "";
	if (null != str && str.length() > 0) {
	    int endIndex = str.lastIndexOf("/");
	    if (endIndex != -1) {
		newStr = str.substring(0, endIndex); // not forgot to put check
						     // if(endIndex != -1)
	    }
	}
	return newStr;
    }

    // Displays a user-friendly custom error messgae
    public static void ThrowCustArticleErrMsg(String errNo) {
	JOptionPane
		.showMessageDialog(
			Home.toolbar,
			"<html><body>A problem has occured while submitting your article, please<br>"
				+ "email the article  and  images  to  tech_support@inkeep.org<br>"
				+ "along with the error code: " + errNo
				+ "</body></html>");
    }
    
    public static void ThrowInternetConnectionErrMsg(boolean shouldQuit) {
	JOptionPane.showMessageDialog(null, "The inKeep Editor requires an internet connection to start.");
	
	if (shouldQuit)
	{
	    System.exit(0);
	}
    }

}
