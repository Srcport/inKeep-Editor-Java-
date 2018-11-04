package createArticle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.ArticleCon;
import config.ImageCompression;
import config.Settings;

public class ArticleSelectConnect
{	
	/* This is the prepared statement used to upload the contents and images to *
	 * the database.  The query is dynamically build using the BuildSubmitQuery *
	 * method.  The  standard  article  details are then put into the query and *
	 * a loop begins for the contents and images.  */
	
	protected static boolean UploadArticleProcess()
	{
		PreparedStatement pstmt;
		String query = CreateArticleController.BuildSubmitQuery();
		System.out.println("\"" + query + "\"");
		try {
		        ArticleCon.con.close();
		        new ArticleCon();
			pstmt = ArticleCon.con.prepareStatement(query);
			pstmt.setString(1, CreateArticleController.articleDetails.get("title"));
			pstmt.setString(2, CreateArticleController.articleDetails.get("name"));
			pstmt.setInt(3, Settings.id);
			pstmt.setString(4, CreateArticleController.articleDetails.get("type"));
			pstmt.setString(5, CreateArticleController.articleDetails.get("wfurl"));
			pstmt.setString(6, CreateArticleController.articleDetails.get("intro"));
			
			String image = ImageCompression.ImageCompressionStart(CreateArticleController.articleDetails.get("introImg"), "introImage", false);
			InputStream imageStream = ConvertImageToStream(image);
			
			pstmt.setBinaryStream(7, (InputStream) imageStream,(int) new File(image).length());
			
			image = ImageCompression.ImageCompressionStart(CreateArticleController.articleDetails.get("mainImg"), "mainImg", true);
			imageStream = ConvertImageToStream(image);
			
			pstmt.setBinaryStream(8, (InputStream) imageStream,(int) new File(image).length()); 
			int c = 9;
			for (int i = 1; i <= CreateArticleController.submitListRef.size(); i++)
			{
				if (CreateArticleController.submitListRef.get(i).contains("text"))
				{
				    pstmt.setString(c, CreateArticleController.submitList.get(i));
				}
				else if (CreateArticleController.submitListRef.get(i).contains("image"))
				{
				    image = ImageCompression.ImageCompressionStart(CreateArticleController.submitList.get(i), "image_" + i, false);
				    imageStream = ConvertImageToStream(image);
				    pstmt.setBinaryStream(c, (InputStream) imageStream,(int) new File(image).length());
				}
				c++;
			}
			pstmt.execute();
		} catch (SQLException e) {
		    e.printStackTrace();
		    return false;
		} catch (IOException e) {
		    e.printStackTrace();
		    return false;
		}
		return true;
	}
	
	private static FileInputStream ConvertImageToStream(String imgPath)
	{    
	    FileInputStream inputStream = null;
	    File image = new File(imgPath);
	    try {
		inputStream = new FileInputStream(image);
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	        }
	    
	    return inputStream;
	}
	
	/* Accesses the 'images' database table to reserve an image name *
	 * for the Rename process. */
	
		
	/* Simply retrieves a list of article types from the database. */
	
	public static void GetArticleTypes()
	{	    
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			pstmt = ArticleCon.con.prepareStatement("SELECT `type` FROM article_types");
			rs = pstmt.executeQuery();
		        while (rs.next()) {
		        CreateArticleView.types.add(rs.getString("type"));	
		        CreateArticleView.typeInput.revalidate();
		      }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Retrieves a list of banned words from the database 
	public static void GetBannedWords()
	{
		PreparedStatement pstmt;
		ResultSet rs = null;
		try {
			pstmt = new ArticleCon().con.prepareStatement("SELECT `word` FROM `bad_words`");
			rs = pstmt.executeQuery();
		      while (rs.next()) {
		        CreateArticleController.bannedWords.add(rs.getString("word"));
		      }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}
