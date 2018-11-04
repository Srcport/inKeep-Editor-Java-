package login;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.BetterSecurity;
import config.UserCon;

public class RegisterController {

    public static boolean CheckTxtCharactersNumbersOnly(String txt)
    {
	if (txt.matches("^[-a-zA-Z0-9-]+"))
	{
	    return true;
	}
	else 
	{
	    return false;
	}
    }
    
    public static boolean CheckEmailContainsAT(String txt)
    {
	if (txt.contains("@"))
	{
	    return true;
	}
	else 
	{
	    return false;
	}
    }
    
    public static boolean CheckPasswordLength(String txt)
    {
	if (txt.length() >= 8)
	{
	    return true;
	}
	else 
	{
	    return false;
	}
    }
    
    public static boolean CheckConfPassword(String conf, String pass)
    {
	if (conf.equals(pass))
	{
	    return true;
	}
	else 
	{
	    return false;
	}
    }
    
    public static void SubmitUser(String username, String firstname, String surname, String email, String password, String dob)
    {
	PreparedStatement pstmt = null;
	try
	{
		pstmt = UserCon.con.prepareStatement("INSERT INTO `users` (`username`, `password`, `email`, `first_name`, `surname`, `dob`) VALUES (?, ?, ?, ?, ?, ?)");
		pstmt.setString(1, username);
		pstmt.setString(2, BetterSecurity.createHash(password));
		pstmt.setString(3, email);
		pstmt.setString(4, firstname);
		pstmt.setString(5, surname);
		pstmt.setString(6, dob);
		pstmt.execute();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	} catch (InvalidKeySpecException e) {
	    e.printStackTrace();
	}
    }

}
