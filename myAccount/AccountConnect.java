package myAccount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Settings;
import config.UserCon;

public class AccountConnect
{
	
	public static boolean CheckUsernameExists(String username)
	{
	    PreparedStatement pstmt;
	    
	    try {
		pstmt = UserCon.con.prepareStatement("SELECT COUNT(*) FROM `users` WHERE `username`= ?");
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
	    	  
		while (rs.next())
		{
			int number = rs.getInt(1);
			if (number > 0)
			{
			    return false;
			}
			else {
			    return true;
			}
		}
		return false;
      } catch (SQLException e) {
    	  e.printStackTrace();
    	  return false;
	  }
	}

	// Called multiple times to update
	public static void UpdateUsers(String item, String value)
	{
		PreparedStatement pstmt;
		String query = "";
		if (item.equals("username")) {
			query = "UPDATE `users` SET `username`=? WHERE `id`=?";
			System.out.println("Updating username");
		} else if (item.equals("password")) {
			query = "UPDATE `users` SET `password`=? WHERE `id`=?";
			System.out.println("Updating password");
		} else if (item.equals("email")) {
			query = "UPDATE `users` SET `email`=? WHERE `id`=?";
			System.out.println("Updating email");
		} else if (item.equals("fname")) {
			query = "UPDATE `users` SET `first_name`=? WHERE `id`=?";
			System.out.println("Updating first name");
		} else if (item.equals("sname")) {
			query = "UPDATE `users` SET `surname`=? WHERE `id`=?";
			System.out.println("Updating surname");
		}

		try {
			pstmt = UserCon.con.prepareStatement(query);
			pstmt.setString(1, value);
			pstmt.setInt(2, Settings.id);
			pstmt.executeUpdate();
		    	  
	      } catch (SQLException e) {
	    	  e.printStackTrace();
		  }
	}
}
