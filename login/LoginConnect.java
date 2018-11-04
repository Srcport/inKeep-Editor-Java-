package login;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import myAccount.AccountController;
import startup.Home;
import config.BetterSecurity;
import config.Settings;
import config.UserCon;

public class LoginConnect {
    /* Checks the login credenetials against the database of users */
    public static boolean VerifyUser(String email, String pass) {
	PreparedStatement pstmt;
	ResultSet rs = null;

	try {
	    pstmt = UserCon.con.prepareStatement("SELECT `password`, `id` FROM `users` WHERE BINARY `email`= ?");
	    pstmt.setString(1, email);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		String hash = rs.getString("password");
		
		if (hash.length() < 1) {
		    return false;
		}
		
		int id = rs.getInt("id");

		if (BetterSecurity.validatePassword(pass, hash)) {
		    hash = "askfdjsadhfsdhf908asdha0dsfhashfdasfah89a-fdshf89dsaf8h9sda8hs9d8h9safds8hd9faa8h9fdafh8d9sfd8ah9sad8fh9s8h9dfh8ah8fdafh89fdah89adhf8sfda8";
		    GetUserDetails(email, id); // If login credentials are true,
					       // the rest of the users details
					       // are fetched
		    return true;
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (InvalidKeySpecException e) {
	    e.printStackTrace();
	}
	return false;
    }

    public static boolean VerifyUserWithoutGet(String email, String pass) {
	PreparedStatement pstmt;
	ResultSet rs = null;

	try {
	    pstmt = UserCon.con
		    .prepareStatement("SELECT `password` FROM `users` WHERE BINARY `email`= ? AND `id`=?");
	    pstmt.setString(1, email);
	    pstmt.setInt(2, Settings.id);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		String hash = rs.getString("password");

		if (BetterSecurity.validatePassword(pass, hash)) {
		    hash = "askfdjsadhfsdhf908asdha0dsfhashfdasfah89a-fdshf89dsaf8h9sda8hs9d8h9safds8hd9faa8h9fdafh8d9sfd8ah9sad8fh9s8h9dfh8ah8fdafh89fdah89adhf8sfda8";
		    return true;
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (InvalidKeySpecException e) {
	    e.printStackTrace();
	}
	return false;
    }

    /* All user details are fetched when successfully verfied */
    public static void GetUserDetails(String email, int id) {
	PreparedStatement pstmt;
	ResultSet rs = null;
	try {
	    pstmt = UserCon.con
		    .prepareStatement("SELECT `username`, `first_name`, `surname`, `status` FROM `users` WHERE BINARY `email`= ? AND `id`=?");
	    pstmt.setString(1, email);
	    pstmt.setInt(2, id);
	    rs = pstmt.executeQuery();
	    while (rs.next()) {
		Settings.id = id;
		Settings.email = email;
		Settings.username = rs.getString("username");
		Settings.firstName = rs.getString("first_name");
		Settings.surname = rs.getString("surname");
		int status = rs.getInt("status");

		if (status != 0) // 0 = OK, 1 = BANNED, 2 = CONTACT US (status
				 // column user database)
		{
		    if (status == 1) {
			JOptionPane
				.showMessageDialog(Home.toolbar,
					"Your account has been disabled, please contact us at tech_support@inkeep.org.");
			System.exit(0);
		    } else if (status == 2) {
			JOptionPane
				.showMessageDialog(
					Home.toolbar,
					"Your account has been put on hold, please contact us at tech_support@inkeep.org.");
			System.exit(0);
		    }
		}
	    }
	    System.out.println("User Details loaded");
	    AccountController.SetUserDetails();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
