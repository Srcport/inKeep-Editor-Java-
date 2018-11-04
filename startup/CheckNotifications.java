/* This is where any notifications and news are handled.
 * 
 *  Banning individual user logins is handled in the LoginConnect method
 *  and in the individual user database. 
 *  
 *  The application can be dissabled via the database using the versions
 *  table. */
package startup;

import java.awt.Insets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import config.NotifCon;
import config.Settings;
import dashboard.Dashboard;

public class CheckNotifications
{
	public static Hashtable<Integer, ArrayList<String>> notifications = new Hashtable<Integer, ArrayList<String>>();
	
	public static void CheckUpdateServer()
	{
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			pstmt = NotifCon.con.prepareStatement("SELECT `status`, `message`, `kill` FROM `ike_versions` WHERE `version`= ?");
			pstmt.setDouble(1, Settings.VERSION);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int stat = rs.getInt("status");
				String mess = rs.getString("message");
				int kill = rs.getInt("kill");
				
		    	  if (stat != 0)
		    	  {
		    		  JOptionPane.showMessageDialog(Home.toolbar, mess, "IMPORTANT NOTICE", JOptionPane.ERROR_MESSAGE);
		    		  
		    		  if (kill == 1)
		    		  {
		    			  System.exit(0);
		    		  }
		    	  }
		      }
	      } catch (SQLException e) {
	    	  e.printStackTrace();
		  }
	}
	
	/* Sets the 10 latest notifications */
	public static void SetLatestNotifications()
	{
		for (int i = notifications.size(); i >= 1; i--)
		{
			ArrayList<String> s = notifications.get(i);
			Dashboard.notificationsArea.add(new JScrollPane(new JTextArea((s.get(1).toString() + "\n\n" + s.get(0).toString()))));
			Dashboard.notificationsArea.add(Box.createVerticalStrut(5));
			s.clear();
		}
		
		Dashboard.notificationsArea.add(Box.createVerticalGlue());
		
		for (int ii = 0; ii < Dashboard.notificationsArea.getComponents().length; ii++)
		{
			if (Dashboard.notificationsArea.getComponent(ii) instanceof JScrollPane)
			{
				((JScrollPane)Dashboard.notificationsArea.getComponent(ii)).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Settings.SITE_ORANGE));
				((JTextArea) ((JScrollPane)Dashboard.notificationsArea.getComponent(ii)).getViewport().getView()).setMargin(new Insets (2, 2, 2, 2));
				((JTextArea) ((JScrollPane)Dashboard.notificationsArea.getComponent(ii)).getViewport().getView()).setLineWrap(true);
				((JTextArea) ((JScrollPane)Dashboard.notificationsArea.getComponent(ii)).getViewport().getView()).setEditable(false);
				((JTextArea) ((JScrollPane)Dashboard.notificationsArea.getComponent(ii)).getViewport().getView()).setWrapStyleWord(true);
			}
		}
		Dashboard.notificationsArea.revalidate();
	}
	
	/* Gets the 10 latest notifications */
	public static void GetLatestNotifications()
	{
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			pstmt = NotifCon.con.prepareStatement("SELECT `id`, `notification`, `date` FROM `ike_notifications` WHERE `status`= 0 ORDER BY `id` LIMIT 10");
			rs = pstmt.executeQuery();
			while (rs.next()) {
		    	  ArrayList<String> notifData = new ArrayList<String>();
		    	  notifData.add(rs.getString("notification"));
		    	  String subs = rs.getTimestamp("date").toString().substring(0, Math.min(rs.getTimestamp("date").toString().length(), 16));
		    	  notifData.add(subs);
		    	  notifications.put(rs.getInt("id"), notifData);
		      }
	      } catch (SQLException e) {
	    	  e.printStackTrace();
		  }
	}
}
