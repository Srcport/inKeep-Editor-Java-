package contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.Settings;

public class ContactConnect
{

	public static Connection contactCon = null;

	public ContactConnect()
	{
		String url = "jdbc:mysql://192.254.236.173:3306/";
        String dbName = "crizly_users";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "crizly_usersrw";
        String password = "msAPLpOOb]yD+Mn5+#";
        
        try {
        Class.forName(driver).newInstance();
        contactCon = DriverManager.getConnection(url+dbName,userName,password);
        } catch (Exception e) {
        	System.out.println("[-] Could not connect to message update database...");
        	e.printStackTrace();
        }
        
        System.out.println("[+] Made account message database conneciton...");
	}
	
	protected static void InsertMessage(String subject, String message, String email)
	{
		PreparedStatement pstmt;
		try {
			pstmt = contactCon.prepareStatement("INSERT INTO `ike_contact` (`user_id`, `email`, `subject`, `message`) VALUES (?, ?, ?, ?)");
			pstmt.setInt(1, Settings.id);
			pstmt.setString(2, email);
			pstmt.setString(3, subject);
			pstmt.setString(4, message);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
