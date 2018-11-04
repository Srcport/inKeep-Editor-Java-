/* This class controls the toolbar at the bottom of the CREATE-NEW-ARTICLE JPanel *
 * it's  used  to  control  the adding and removal of TEXT-AREAS and IMAGE-AREAS, *
 * and to submit the article once written 										  */

package createArticle;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import startup.Home;
import config.Settings;

public class CreateArticleToolbar extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	
	/* Toolbar buttons */
	protected static JButton newText = new JButton("New Text Area");
	protected static JButton newImage = new JButton("New Image Area");
	protected static JButton removeLast = new JButton("Remove Last Area");
	
	/* ArrayList keeps track of the items on screen in the form of *
	 * "text" or "image". */
	protected static ArrayList<String> lastAdded = new ArrayList<String>();
	
	protected static JButton submit = new JButton("Submit Article");
	
	// Maximum 17
	public static int textAreaCounter = 0;
	public static int imageAreaCounter = 0; 
	
	private static int maxElements = 10; // Change to 17 when supported

	/* Text in toolbar informing user how many elements they have on screen. */
	protected static JLabel total = new JLabel("Total Areas: " + (textAreaCounter + imageAreaCounter) + "/" + maxElements);
	
	public CreateArticleToolbar()
	{
		setPreferredSize(new Dimension(800, 40));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Settings.SITE_GREEN));
		
		// Action Listener
		newText.addActionListener(this);
		newImage.addActionListener(this);
		removeLast.addActionListener(this);
		submit.addActionListener(this);
		
		// Enabled
		removeLast.setEnabled(false);
		
		// Layout
		add(newText);
		add(newImage);
		add(removeLast);
		add(submit);
		add(Box.createHorizontalGlue());
		add(total);
		add(Box.createHorizontalStrut(20));
	}

	/* Runs when the ADD-TEXT-BUTTON, ADD-IMAGE-BUTTON or REMOVE-LAST-ADDED button is pressed *
	 * if the number of components is less than 17, a new component can be added. If not, the *
	 * button  is  grayed  out.  If the REMOVE-LAST-ADDED button is pressed, the component is *
	 * removed from the JPanel and if the button grayed out if necessary. 					  */
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == newText) // If newText button is pressed...
		{
			if ((textAreaCounter + imageAreaCounter) < maxElements) // and there are less than 17 items on screen...
			{
				lastAdded.add("text"); // Add the word "text" to the ArrayList keeping track...
				textAreaCounter++;     // Iterate the textAreaCounter...
				CreateArticleView.GenerateTextArea(textAreaCounter); // Generate a new text area on screen.
				MoveToBottom(); // Move the view of the JPanel to the bottom where the new text area is. 
				
				if ((textAreaCounter + imageAreaCounter) == maxElements) // If the total number of elements is 17, dissable buttons
				{
					newImage.setEnabled(false);
					newText.setEnabled(false);
				}
			}
			else 
			{
				newText.setEnabled(false);
				newImage.setEnabled(false);
			}
			KeepTally();
		}
		else if (e.getSource() == newImage)
		{
			if ((textAreaCounter + imageAreaCounter) < maxElements)
			{
				lastAdded.add("image");
				imageAreaCounter++;
				CreateArticleView.GenerateImageArea(imageAreaCounter);
				MoveToBottom();
				
				if ((textAreaCounter + imageAreaCounter) == maxElements)
				{
					newImage.setEnabled(false);
					newText.setEnabled(false);
				}
			}
			else 
			{
				newImage.setEnabled(false);
				newText.setEnabled(false);
			}
			KeepTally();
		}
		else if (e.getSource() == removeLast)
		{
			if (lastAdded.size() > 0)
			{
				if (lastAdded.get(lastAdded.size() - 1).equals("text"))
				{
					if (CreateArticleController.ConfTextDelete(textAreaCounter))
					{
						CreateArticleView.textContents.get(textAreaCounter).setText("");
						Home.createArticle.remove(CreateArticleView.textAreas.get(textAreaCounter));
						Home.createArticle.remove(CreateArticleView.textLabels.get(textAreaCounter));
						CreateArticleView.areaHeight -= 210;
						Home.createArticle.setPreferredSize(new Dimension(800, CreateArticleView.areaHeight));
						
						newText.setEnabled(true);
						newImage.setEnabled(true);
						textAreaCounter--;	
						lastAdded.remove(lastAdded.size() - 1);
					}
				}
				else if (lastAdded.get(lastAdded.size() - 1).equals("image"))
				{
					CreateArticleController.RemoveLastImage(imageAreaCounter);
					Home.createArticle.remove(CreateArticleView.imageAreas.get(imageAreaCounter));
					Home.createArticle.remove(CreateArticleView.imageButtons.get(imageAreaCounter));
					CreateArticleView.areaHeight -= 190;
					Home.createArticle.setPreferredSize(new Dimension(800, CreateArticleView.areaHeight));
					
					newImage.setEnabled(true);
					newText.setEnabled(true);
					imageAreaCounter--;
					lastAdded.remove(lastAdded.size() - 1);
				}
			}
			KeepTally();
		}
		else if (e.getSource() == submit) // Submits the article in its own thread
		{
			submit.setEnabled(false);
			Home.HideAllButOne(Home.createArticleStatus);
			Home.toolbar.setVisible(false);
			new Thread()
			{
				public void run() {
			    	CreateArticleController.SubmitArticle();
			    	Home.toolbar.setVisible(true);
			    	submit.setEnabled(true);
			    }
			}.start();
		}
	}
	
	/* Prints out a running tally of the numer of elements on page and checks the *
	 * total amount elements to enable and dissable the remove button.  */
	protected static void KeepTally()
	{
		// Keep tally on the number of areas
		System.out.println("Image Area Counter: " + imageAreaCounter);
		System.out.println("Text Area Counter: " + textAreaCounter);
		System.out.println("ArrayList: " + lastAdded + "\n");
		total.setText("Total Areas: " + (textAreaCounter + imageAreaCounter) + "/" + maxElements);
		
		// Enable and dissable the remove last item button
		if ((imageAreaCounter + textAreaCounter) == 0)
		{
			removeLast.setEnabled(false);
		}
		else if ((imageAreaCounter + textAreaCounter) > 0)
		{
			removeLast.setEnabled(true);
		}
	}
	
	/* Moves the focus of the JPanel to the bottom of the screen. */
	protected static void MoveToBottom()
	{
		JScrollBar vertical = Home.createArticleScroll.getVerticalScrollBar();
		Home.createArticleScroll.validate();
		vertical.setValue(vertical.getMaximum() + 210);
	}

}
