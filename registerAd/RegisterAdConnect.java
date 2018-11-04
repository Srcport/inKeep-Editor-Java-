package registerAd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import config.ArticleCon;
import config.Settings;

public class RegisterAdConnect {

    public static void SubmitAdvert(String title, String url, String image,
	    String description, int type) {
	PreparedStatement pstmt = null;

	try {
	    if (type == 1) {
		pstmt = ArticleCon.con.prepareStatement("INSERT INTO `adverts` (`author_id`, `title`, `url`, `description`, `type`) VALUES (?, ?, ?, ?, ?)");
	    } else {
		pstmt = ArticleCon.con.prepareStatement("INSERT INTO `adverts` (`author_id`, `title`, `url`, `description`, `type`, `image`) VALUES (?, ?, ?, ?, ?, ?)");
	    }
	    pstmt.setInt(1, Settings.id);
	    pstmt.setString(2, title);
	    pstmt.setString(3, url);
	    pstmt.setString(4, description);
	    pstmt.setInt(5, type);

	    if (type != 1) {

		if (!image.equals("")) {
		    FileInputStream inputStream = null;
		    File img = new File(image);
		    try {
			inputStream = new FileInputStream(img);
		    } catch (FileNotFoundException e) {
			e.printStackTrace();
		    }
		    pstmt.setBinaryStream(6, (InputStream) inputStream,
			    (int) (img.length()));
		} else {
		    pstmt.setBinaryStream(6, null);
		}
	    }
	    pstmt.execute();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
