package selectAdvert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import config.ArticleCon;
import config.Settings;

public class SelectAdvertConnect
{	
	protected static Hashtable<Integer, Integer> articleNums = new Hashtable<Integer, Integer>();
	
	protected static void SetAdvert(int advertId, int articleId)
	{
		PreparedStatement pstmt;
		
		try {
			pstmt = ArticleCon.con.prepareStatement("UPDATE `articles` SET `advert_id` = ? WHERE `id` = ?");
			pstmt.setInt(1, advertId);
			pstmt.setInt(2, articleId);
			pstmt.executeUpdate();
		    	  
	      } catch (SQLException e) {
	    	  e.printStackTrace();
		  }
	}
	
	protected static void GetUsersArticles() // TODO: Change database when done
	{
		SelectAdvertView.dtm.setRowCount(0);
		PreparedStatement pstmt;
		ResultSet rs = null;
		
		try {
			pstmt = ArticleCon.con.prepareStatement("SELECT `id`, `title`, `type`, `advert_id`, `date` FROM `articles` WHERE `author_id` = ?");
			pstmt.setInt(1, Settings.id);
			rs = pstmt.executeQuery();
			int count = 1;
		      while (rs.next()) {
		    	  articleNums.put(count, rs.getInt("id"));
		    	  String b = rs.getString("title");
		    	  String c = rs.getString("type");
		    	  String d = rs.getString("advert_id");
		    	  String e = rs.getString("date");
		    	  
		    	  SelectAdvertView.dtm.addRow(new Object[]{count, b, c, d, e});
		    	  count++;
		      }
		     
		      		      
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void GetUsersAdverts() // TODO: Change database when done
	{
		SelectAdvertView.dtm2.setRowCount(0);
		PreparedStatement pstmt;
		ResultSet rs = null;
		
		try {
			pstmt = ArticleCon.con.prepareStatement("SELECT `id`, `date`, `title`, `url` FROM adverts WHERE `author_id` = ?");
			pstmt.setInt(1, Settings.id);
			rs = pstmt.executeQuery();
		      while (rs.next()) {
		    	  int a = rs.getInt("id");
		    	  SelectAdvertView.advertIds.addItem(a);
		    	  SelectAdvertView.advertIds.revalidate();
		    	  String b = rs.getString("date");
		    	  String c = rs.getString("title");
		    	  String d = rs.getString("url");
		    	  
		    	  SelectAdvertView.dtm2.addRow(new Object[]{a, c, d, b});
		      }	      
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected static void GetAllAdverts() // TODO: Change database when done
	{
		SelectAdvertView.dtm2.setRowCount(0);
		PreparedStatement pstmt;
		ResultSet rs = null;
		
		try {
			pstmt = ArticleCon.con.prepareStatement("SELECT `id`, `date`, `title`, `url` FROM adverts");
			rs = pstmt.executeQuery();
		      while (rs.next()) {
		    	  int a = rs.getInt("id");
		    	  SelectAdvertView.advertIds.addItem(a);
		    	  SelectAdvertView.advertIds.revalidate();
		    	  String b = rs.getString("date");
		    	  String c = rs.getString("title");
		    	  String d = rs.getString("url");
		    	  
		    	  SelectAdvertView.dtm2.addRow(new Object[]{a, c, d, b});
		      }	      
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
