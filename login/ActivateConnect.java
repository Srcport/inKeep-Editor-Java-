package login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.UserCon;

public class ActivateConnect {

    public static boolean VerifyCode(String number, int uid) {
	PreparedStatement pstmt;
	ResultSet rs = null;

	try {
	    pstmt = UserCon.con.prepareStatement("SELECT COUNT(`id`) FROM `ike_access` WHERE `verified`= 1 AND `number` = ? AND `id` = ?");
	    pstmt.setString(1, number);
	    pstmt.setInt(2, uid);
	    rs = pstmt.executeQuery();
	    while (rs.next()) 
	    {
		int res = rs.getInt(1);
		if (res == 1) {
		    return true;
		} else {
		    return false;
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return false;
    }
    
    public static int FindUserId(String username, String email) {
	PreparedStatement pstmt;
	ResultSet rs = null;

	try {
	    pstmt = UserCon.con.prepareStatement("SELECT `id` FROM `users` WHERE BINARY `username` = ? AND BINARY `email` = ?");
	    pstmt.setString(1, username);
	    pstmt.setString(2, email);
	    rs = pstmt.executeQuery();
	    while (rs.next()) 
	    {
		return rs.getInt("id");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return 0;
    }
    
    public static int SetAppPass(int id, String hash) {
	PreparedStatement pstmt;

	try {
	    pstmt = UserCon.con.prepareStatement("UPDATE `users` SET `password` = ? WHERE `id` = ?");
	    pstmt.setString(1, hash);
	    pstmt.setInt(2, id);
	    pstmt.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return 0;
    }
    
    public static int FinishVerification(int uid) {
	PreparedStatement pstmt;

	try {
	    pstmt = UserCon.con.prepareStatement("UPDATE `ike_access` SET `verified` = 2 WHERE `user_id` = ?");
	    pstmt.setInt(1, uid);
	    pstmt.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return 0;
    }

}
