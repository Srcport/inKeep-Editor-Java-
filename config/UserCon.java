package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserCon {

    public static Connection con;

    public UserCon() {

	String url = "jdbc:mysql://192.254.236.173/";
	String dbName = "crizly_users";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "crizly_usersrw";
	String password = "msAPLpOOb]yD+Mn5+#";

	try {
	    Class.forName(driver).newInstance();
	    con = DriverManager.getConnection(url + dbName, userName, password);
	} catch (Exception e) {
	    System.out.println("[-] Could not connect to account database...");
	    General.ThrowInternetConnectionErrMsg(true);
	    e.printStackTrace();
	}
	System.out.println("[+] Made user account database conneciton...");
	
	new Thread() {
	    public void run() {
		try {
		    while (true)
		    {
			if (UserCon.con.isClosed())
			{
			    System.out.println("User Con closed.");
			}
		    }
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}.start();
    }

}
