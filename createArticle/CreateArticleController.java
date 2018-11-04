/* This method is the home of most of the methods that perform actions *
 * on the CreateArticleView class. The project has been split up in an *
 * MVC  architecture,  this  is the controller, the other class is the *
 * view. */
package createArticle;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import startup.Home;
import config.General;
import config.Settings;

public class CreateArticleController
{
    // List of image paths to be used, the keys are the image number (e.g. image_3 = key:3)
    public static Hashtable<Integer, String> imageUploads = new Hashtable<Integer, String>();
    // The final list of text contents + image paths
    protected static Hashtable<Integer, String> submitList = new Hashtable<Integer, String>();
    // List with 'text' or 'image' to preserve the order of data in SUBMITLIST above
    protected static Hashtable <Integer, String> submitListRef = new Hashtable<Integer, String>();
    // Populated list of banned words
    public static ArrayList<String> bannedWords = new ArrayList<String>();
    // The path of the intro image is set here
    public static String introImgUpload = "";
    // The path of the main image is set here
    public static String mainImgUpload = "";
    // A list of article details (name, url, intro, mainImg, introImg) is stored here, keys are words "name", "wfurl" etc. 
    public static Hashtable<String, String> articleDetails = new Hashtable<String, String>();
	
    /* Submit article is called when the user clicks the submit butotn (in its own thread) *
     * each of the called methods returns a TRUE or FALSE value, to submit the article all *
     * methods must return true.  							   */	
    @SuppressWarnings("unused")
    public static void SubmitArticle()
    {	
	if (BuildSubmitList()) // Build the submitList hashtable full of values
	{	
	    CreateArticleStatus.append("[+] All text areas extracted...\n");
			
	    if (CheckListSize()) // Check the size + input values of the submit list
	    {	
		CreateArticleStatus.append("[+] All areas correct size...\n");
				
		if (true) // Check the input against a database of banned words CheckBannedWords()
		{	
		    CreateArticleStatus.append("[+] No banned words detected...\n");
					
		    if (CheckImageFileSize()) // Check the image file sizes to make sure they are less than 1MB.
		    {  
			CreateArticleStatus.append("[+] All image sizes acceptable...\n");
							
			if (CheckArticleDetails()) // Check article details 
			{
			    CreateArticleStatus.append("\n[+] Article details (name, url, title etc) are fine...\n");
			    CreateArticleStatus.append("\n[>] Starting article upload process (be patient)...\n");
								
			    if (UploadArticle()) // Starts the uplaad process
			    {
				CreateArticleStatus.append("\n[<] Article successfully uploaded for review...\n");
			    } 
			    else 
			    {
				General.ThrowCustArticleErrMsg("E-CAC-00001");
			    }
			}
			else 
			{
			    General.ThrowCustArticleErrMsg("E-CAC-00002");
			}
		    } 
		    else 
		    {
			General.ThrowCustArticleErrMsg("E-CAC-00003");
		    }
		} 
		else
		{
		    General.ThrowCustArticleErrMsg("E-CAC-00004");
		}
	    } 
	    else 
	    {
		General.ThrowCustArticleErrMsg("E-CAC-00005");
	    }
	}
	submitList.clear();
	submitList.values().clear();
    }
	
    /* This method builds the prepared statement query dynamically based on how many contents *
     * and images the user has selected to fill in.					      */
    protected static String BuildSubmitQuery()
    {
	String query = "INSERT INTO `articles` (`title`, `author`, `author_id`, `type`, `wfurl`, `intro`, `intro_image`, `main_image`";
		
	int textCount = 1;
	int imgCount = 1;
	int overallCount = 0;
		
	for (int i = 1; i <= submitListRef.size(); i++)
	{
	    if (submitListRef.get(i).contains("text"))
	    {
		query += ", `content_" + textCount +"`";
		textCount++;
		overallCount++;
	    }
	    else if (submitListRef.get(i).contains("image"))
	    {
		query += ", `image_" + imgCount +"`";
		imgCount++;
		overallCount++;
	    }
	}
	
	query += ") VALUES (?, ?, ?, ?, ?, ?, ?, ?";
		
	for (int i = 0; i < overallCount; i++) // Appnds ", ?" onto the query depending on number of elements
	{
	    query += ", ?";
	}
		
	query += ")";
	return query;
    }
	
    /* This method is used to check the details of the article header, the *
     * name, wfurl and title etc. 					   */
    private static boolean CheckArticleDetails()
    {
	String title = CreateArticleView.titleInput.getText();
	String wfurl = CreateArticleView.wfurlInput.getText();
	String type = CreateArticleView.typeInput.getSelectedItem().toString();
	String intro = CreateArticleView.introInput.getText();
		
	try {	
			
	    // Set the name to be published under
	    if (CreateArticleView.usernameTick.isSelected())
	    {
	    articleDetails.put("name", Settings.username);
	    }
	    else if (CreateArticleView.fullnameTick.isSelected())
	    {
		articleDetails.put("name", Settings.firstName + " " + Settings.surname);
	    }
	    else if (CreateArticleView.firstNameTick.isSelected())
	    {
		articleDetails.put("name", Settings.firstName);
	    }
			
	    articleDetails.put("type", type);
			
	    if (!introImgUpload.equals("") && !mainImgUpload.equals(""))
	    {
		articleDetails.put("introImg", introImgUpload); // RenameImage(introImgUpload));
		articleDetails.put("mainImg", mainImgUpload); //RenameImage(mainImgUpload));
		
		File f1 = new File(articleDetails.get("introImg"));
		File f2 = new File(articleDetails.get("mainImg"));
				
		if (f1.length() > 524288 || f2.length() > 5242880)
		{
		    CreateArticleStatus.append("\n[-] The intro image max size is 2mb and the main image max size is 5mb\n");
		    return false;
		}
				
		if (!title.equals("") && title.length() <= 200)
		{
		    articleDetails.put("title", title);
					
		    if (!wfurl.equals("") && wfurl.length() <= 200 && wfurl.matches("^[-a-zA-Z0-9-]+"))
		    {
			articleDetails.put("wfurl", wfurl);
						
			if (!intro.equals("") && intro.length() <= 10000)
			{
			    articleDetails.put("intro", intro);
			    return true;
			}
		    }
		}
	    }
	    else 
	    {
		JOptionPane.showMessageDialog(Home.toolbar, "You need to upload an 200*200 icon and larger main image");
		return false;
	    }
			
	    return true;
	
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }
	
    /* Calls the upload method in the connect class. */	
    private static boolean UploadArticle()
    {
	boolean result = ArticleSelectConnect.UploadArticleProcess();
	    
	if (result) 
	    return true;
	else 
	    return false;
    }
	
    /* This method is a handler for multiple items to be renamed, it calls the *
     * rename function.							       */ 
    
    /* * * * * * * * * * * * * * * * * * * * * * * *
    private static boolean RenameImageHandler()
    {
	int i = 1;
	for (String s : submitListRef.values())
	{
	    if (s.contains("image"))
	    {
		submitList.put(i, RenameImage(submitList.get(i)));
	    }
	    
	    i++;
	}
	
	return true;
    }
    * * * * * * * * * * * * * * * * * * * * * * * */ 
	
    /* This method checks the image file sizes for the content images */
    private static boolean CheckImageFileSize()
    {
	int i = 1;
	for (String s : submitListRef.values())
	{
	    if (s.contains("image"))
	    {
		File f = new File(submitList.get(i));
		
		System.out.println("Length File: " + submitList.get(i));
		System.out.println("Length: " + f.length());
				
		if (f.length() > 4194304) // Check file is < 4mb
		{
		    CreateArticleStatus.append("[ERROR] Image " + submitList.get(i) + " is bigger than 4MB, reduce image size...\n");
		    return false;
		}
		
		i++;
	    }
	}
	
	return true;
    }
	
    /* This is the method that actually renames the files, you pass *
     * in a URL string and  the name is  extracted and changed to a * 
     * name based off a database naming system (the 		    *
     * GenerateImageNum method returns the number that it should be *
     * renamed to.)  A  check  is also performed to ensure that the *
     * image is infact an image of type (png, jpg, or jpeg).        */
	
    /* * * * * * * * * * * * * * * * * * * * * * * *
    public static String RenameImage(String fileUrl)
    {   
    	String newFileName = fileUrl;
    	String fileNameEnding = ".";
    	
	try {
	    	
	    if (fileUrl.endsWith(".png"))
	    {
	    	fileNameEnding = ".png";
	    }
	    else if (fileUrl.endsWith(".jpg"))
	    {
	    	fileNameEnding = ".jpg";
	    }
	    else if (fileUrl.endsWith(".jpeg"))
	    {
	    	fileNameEnding = ".jpeg";
	    }
	    	
	    String filePath = fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1); // Extracts the filePath with '/' on the end
	    File target = new File(fileUrl); 
	    String newName = null;
	    newName = filePath + "image_" + ArticleSelectConnect.GenerateImageNum() + fileNameEnding;
	    
	    if (target.renameTo(new File(newName)))
	    {
	    	newFileName = newName; 
	    }
	    else
	    {
	    	CreateArticleStatus.append("[ERROR] Image Rename Failed.\n");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	CreateArticleStatus.append("    [+] Image name successfully changed...\n");
		
    	return newFileName; 
    }
    * * * * * * * * * * * * * * * * * * * * * * * */ 
	
    /* This simply checks the contents against the list of banned words */
    /* * * * * * * * * * * * * * * * * * * * * * * *
    private static boolean CheckBannedWords()
    {
	for (String s : submitList.values())
	{
	    for (String b : bannedWords)
	    {
		if (s.matches(b) || s.matches(b.toLowerCase()) || s.matches(b.toUpperCase()))
		{
			JOptionPane.showMessageDialog(Home.toolbar, "<html><body>Your article contains one or more of the banned words: " + b + ".<br><br>" +
			return false;
		}
	    }
	}

	return true;
    }
    * * * * * * * * * * * * * * * * * * * * * * * */
	
    /* Used for checking the size of the input areas */
    private static boolean CheckListSize()
    {
	try {
			
	    for (String s : submitList.values())
	    {
		if (s.length() <= 20000)
		{
		    return true;
		} 
		else 
		{
		    JOptionPane.showMessageDialog(Home.toolbar, "One or more of your text areas are too big, make sure they are less than 20,000 words each.");
		    return false;
		}
	    }
	    
	    System.out.println(imageUploads.keySet());
			
	    return true;
	    
	} catch (Exception e) {
	    return false;
	}
    }
	
    /* Builds the list of values that should be submitted *
     * removes any empty boxes left on screen. 		  */
	
    private static boolean BuildSubmitList()
    {
	int textCount = 1;
	int imageCount = 1;
	
	try {
	    
	    System.out.println("Loop Size: " + CreateArticleToolbar.lastAdded.size());
	    
	    for (int i = 1; i <= CreateArticleToolbar.lastAdded.size(); i++)
	    {
		if (CreateArticleToolbar.lastAdded.get(i - 1).contains("text"))
		{
		    if (!CreateArticleView.textContents.get(textCount).getText().toString().equals(""))
		    {
			System.out.println("    Text count: " + textCount);
			
			submitList.put(i, CreateArticleView.textContents.get(textCount).getText().toString());
			submitListRef.put(i, "text");
		    }
		    else
		    {
			System.out.println("empty");
		    }
			
		    textCount++;
		}
		else if (CreateArticleToolbar.lastAdded.get(i - 1).contains("image"))
		{
		    if (imageUploads.get(imageCount) != null)
		    {
			if (imageUploads.get(imageCount).length() <= 1000)
			{
			    System.out.println("    Image count: " + imageCount);
			    submitList.put(i, imageUploads.get(imageCount));
			    submitListRef.put(i, "image");
			}
			else 
			{
			    return false;
			}
		    }
		
		    imageCount++;
		}
	    }
	
	    System.out.println("\nSubmit List: " + submitList.values());
	    System.out.println("Submit List Ref: " + submitListRef.values());
		
	    return true;
	
	} catch (Exception e) {
	    return false;
	}	
    }
	
    /* When the user selects an image from the button, this method *
     * puts a  copy  of  the image in the corresponding JLabel for *
     * viewing purposes 					   */
    public static void LoadImage(int key)
    {
	JFileChooser fc = new JFileChooser();
	int value = fc.showOpenDialog(Home.toolbar);
	if (value == JFileChooser.APPROVE_OPTION) 
	{
	    String imageLoc = fc.getSelectedFile().toString();
			
	    if (imageLoc.endsWith(".jpg") || imageLoc.endsWith(".png") || imageLoc.endsWith(".jpeg"))
	    {
		System.out.println("LoadImage key: " + key);
		
		imageUploads.put(key, imageLoc);
		ImageIcon icon = new ImageIcon(imageLoc);
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(360, 175,  java.awt.Image.SCALE_SMOOTH);
		
		CreateArticleView.imageAreas.get(key).setIcon(new ImageIcon(newimg));
	    }
	    else 
	    {
		JOptionPane.showMessageDialog(Home.toolbar, "You can only upload .png, .jpg or .jpeg files.");
	    }
	}
	
	System.out.println("Image Added: " + imageUploads.values());
    }

    // Removes the image string from the imageUpload array if it's been set. 
    public static void RemoveLastImage(int key)
    {
	if (imageUploads.size() == key)
	{
	    ImageIcon icon = new ImageIcon("src/resources/imagefillercross.png");
	    Image image = icon.getImage();
	    Image imageIcon = image.getScaledInstance(360, 175,  java.awt.Image.SCALE_SMOOTH);

	    CreateArticleView.imageAreas.get(key).setIcon(new ImageIcon(imageIcon));
			
	    imageUploads.remove(key - 1);
			
	    System.out.println("[+] Removed last image ran...");
	}
	else 
	{
	    System.out.println("[~] Removed last image did not run...");	
	}
    }
	
    /* When the text area contains text, if the user attempts to delete *
     * the box,  this method  will  show a popup box asking the user if *
     * they really want to delete it. 					*/
    public static boolean ConfTextDelete(int key)
    {
	if (!CreateArticleView.textContents.get(key).getText().equals(""))
	{
	    int reply = JOptionPane.showConfirmDialog(Home.createArticle, "Are you sure you want to remove that text box, the text will be permanently deleted.", "Remove Text Area",  JOptionPane.YES_NO_OPTION);
	
	    if (reply == JOptionPane.YES_OPTION)
	    {
		return true;
	    }
	    else if (reply == JOptionPane.NO_OPTION)
	    {
		return false;
	    }
	}
		
	return true;
    }
	
    /* This method is responsible for loading the INTRO image and checking its type */
	
    public static void LoadIntroImage()
    {
	JFileChooser fc = new JFileChooser();
	int value = fc.showOpenDialog(Home.toolbar);
	
	if (value == JFileChooser.APPROVE_OPTION) 
	{
	    String imageLoc = fc.getSelectedFile().toString();
			
	    if (imageLoc.endsWith(".jpg") || imageLoc.endsWith(".png") || imageLoc.endsWith(".jpeg"))
	    {
		introImgUpload = imageLoc;
		
		ImageIcon icon = new ImageIcon(imageLoc);
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
				
		CreateArticleView.introImgArea.setIcon(new ImageIcon(newimg));
	    }
	    else 
	    {
		JOptionPane.showMessageDialog(Home.toolbar, "You can only upload .png, .jpg or .jpeg files.");
	    }
	}
    }
	
    /* This method is responsible for loading the main image and checking its type */
	
    public static void LoadMainImage()
    {
	JFileChooser fc = new JFileChooser();
	
	int value = fc.showOpenDialog(Home.toolbar);
		
	if (value == JFileChooser.APPROVE_OPTION) 
	{	
	    String imageLoc = fc.getSelectedFile().toString();
			
	    if (imageLoc.endsWith(".jpg") || imageLoc.endsWith(".png") || imageLoc.endsWith(".jpeg"))
	    {
		mainImgUpload = imageLoc;
				
		ImageIcon icon = new ImageIcon(imageLoc);
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(360, 200,  java.awt.Image.SCALE_SMOOTH);
				
		CreateArticleView.loadMainArea.setIcon(new ImageIcon(newimg));		
	    }
	    else
	    {
		JOptionPane.showMessageDialog(Home.toolbar, "You can only upload .png, .jpg or .jpeg files.");
	    }
	}
    }
}
