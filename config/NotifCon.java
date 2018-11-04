package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NotifCon {

    public static Connection con;

    public NotifCon() {

	String url = "jdbc:mysql://192.254.236.173/";
	String dbName = "crizly_app-data";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "crizly_appdataro";
	String password = "4g&%z_=sZ%E#0{X[8f";

	try {
	    Class.forName(driver).newInstance();
	    con = DriverManager.getConnection(url + dbName, userName, password); // Starts
										 // the
										 // connection.
	} catch (Exception e) {
	    System.out.println("[-] Could not connect to notifications database...");
	    General.ThrowInternetConnectionErrMsg(true);
	    e.printStackTrace();
	}
	System.out.println("[+] Made notifications database conneciton...");
	
	new Thread() {
	    public void run() {
		try {
		    while (true)
		    {
			if (NotifCon.con.isClosed())
			{
			    System.out.println("Notif Con closed.");
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
