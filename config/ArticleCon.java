package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ArticleCon {

    public static Connection con;

    public ArticleCon() {
	String url = "jdbc:mysql://192.254.236.173/";
	String dbName = "crizly_articles";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "crizly_articlerw";
	String password = "ya*PR~%}t&z-qCv,0q";

	try {
	    Class.forName(driver).newInstance();
	    
	    con = DriverManager.getConnection(url + dbName, userName, password); // Starts
										 // the
										 // connections
	    
	    
	} catch (SQLException e) {
	    System.out.println("[-] Could not connect to article select database...");
	    General.ThrowInternetConnectionErrMsg(true);
	    e.printStackTrace();
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	System.out.println("[+] Made article select database conneciton...");
    }

}
