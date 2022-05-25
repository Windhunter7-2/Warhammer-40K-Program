package External;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI_Helpers {

	/**
	 * This is the size to make all the common text.
	 */
	private static final int mainGUI_textSize = 16;
	
	/**
	 * The styling to have a VBox or HBox be bordered by a circularlike bolded border.
	 */
	public String style_borderedPanel = "-fx-border-color: black; -fx-border-radius: 20 20 20 20;" + 
			" -fx-padding: 30; -fx-border-width: 2px, 0px;";
	
	/**
	 * Note: The Elements to Add to the Panel Go in *Vertical* Order, As This Uses a VBox
	 * Creates a GUI Panel. Note that childrenToAdd Are the VBoxes and HBoxes to add to the panel!
	 * @param x_width How wide to make the panel
	 * @param y_height How high to make the panel
	 * @param s The stage (That gets passed throughout); CAN be null! (Which means it creates it)
	 * @param spacingAmt How much to separate the sections by
	 * @param childrenToAdd All the VBoxes/HBoxes to add to the panel; the most important parameter!
	 * @return The stage that just got modified
	 */
	public Stage createGUIPanel(String panelTitle, int x_width, int y_height, Stage s, int spacingAmt,
			boolean hasScrollbar, Node...childrenToAdd)
	{
		if (s == null)
		{
			s = new Stage();
			s.show();
		}
		s.setTitle(panelTitle);
		VBox overallPane = new VBox();	//The Overall Pane
		VBox mainPanel = new VBox();	//A Nested VBox to Support Scrolling Well
		Scene scene = new Scene(overallPane, x_width, y_height);
		s.setScene(scene);	//The Scene Itself
		ScrollPane mainScroller = new ScrollPane();	//Scrollbar
		if (hasScrollbar)
			mainScroller.setContent(mainPanel);
		mainPanel.setSpacing(spacingAmt);	//To Separate the Sections Clearly
		s.setMaximized(true);
		s.centerOnScreen();
		s.resizableProperty().setValue(Boolean.FALSE);	//Force Maximized (To Prevent Weird Skewing)
		double maxCoords = s.getWidth();
		mainPanel.setTranslateX(maxCoords / 5.0);	//Math to Put in Center of Screen
		for (int i = 0; i < childrenToAdd.length; i++)
			mainPanel.getChildren().add(childrenToAdd[i]);	//The Additions Themselves
		overallPane.getChildren().add(mainPanel);
		if (hasScrollbar)
		{
			overallPane.getChildren().add(mainScroller);
			mainScroller.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			mainScroller.setVisible(true);
		}
		return s;
	}

	/**
	 * Creates a Text object, with the given text. It's default font size is 16, but the optional textSizeOverride
	 * parameter can override that size.
	 * @param text The text to create
	 * @param isBold Whether it's bold
	 * @param isUnderlined Whether it's underlined
	 * @param isItalic Whether it's italic
	 * @param textSizeOverride Can be non-existing *or* a single integer, which would be the font size
	 * @return The Text object
	 */
	public Text createText(String text, boolean isBold, boolean isUnderlined, boolean isItalic, int...textSizeOverride)
	{
		//Set Text Size
		int textSize = mainGUI_textSize;
		if (textSizeOverride.length > 0)
			textSize = textSizeOverride[0];
		
		//The Fonts
		final Font mainGUI_unbolded = Font.font(Font.getDefault().getName(), FontWeight.NORMAL,
				FontPosture.REGULAR, textSize);
		final Font mainGUI_italic = Font.font(Font.getDefault().getName(), FontWeight.NORMAL,
				FontPosture.ITALIC, textSize);
		final Font mainGUI_bold = Font.font(Font.getDefault().getName(), FontWeight.BOLD,
				FontPosture.REGULAR, textSize);
		final Font mainGUI_boldItalic = Font.font(Font.getDefault().getName(), FontWeight.BOLD,
				FontPosture.ITALIC, textSize);
		
		//Set Bold / Bold Italic
		Text newText = new Text(text);
		if (isBold)
		{
			if (isItalic)
				newText.setFont(mainGUI_boldItalic);
			else
				newText.setFont(mainGUI_bold);
		}
		
		//Set Italic / Normal
		else if (isItalic)
			newText.setFont(mainGUI_italic);
		else
			newText.setFont(mainGUI_unbolded);
		
		//Set Underlined
		if (isUnderlined)
			newText.setUnderline(true);	//Underline
		return newText;
	}
	
	/**
	 * Helper method for GUIs.
	 * Creates a generic TextField for use in the GUI, where the initial text is set to initialText, and
	 * it sets the format of the TextField to the given regex only, and anything else will not work in the textbox.
	 * Note that if regexToAllow is an empty String, that any text can be entered.
	 * @param initialText The initial text to put in the TextField (e.g. "98%")
	 * @param regexToAllow Which regex to allow (e.g. "[0-9]*%"); if blank, no regex requirements
	 * @param widthSize How wide to make the textbox, in terms of number of characters *expected* (e.g. 3 for 3 chars wide)
	 * @return The created TextField
	 */
	public TextField mainGUI_genericField(String initialText, String regexToAllow, int widthSize)
	{
		//Create TextField, with Given Sizes
		TextField textField = new TextField(initialText);
		Font basicFont_defaultSize = Font.font(Font.getDefault().getName(), FontWeight.NORMAL,
				FontPosture.REGULAR, mainGUI_textSize);
		textField.setFont(basicFont_defaultSize);
		textField.setMinWidth(widthSize * mainGUI_textSize);
		textField.setMaxWidth(widthSize * mainGUI_textSize);	//Set Width to (Width of Single Character * widthSize)
		
		//Have TextField Follow the Regex (If Has a Regex)
		textField.textProperty().addListener(new ChangeListener<String>()
		{
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
		    {
		    	if (!regexToAllow.isBlank())
		    	{
			        if (!newValue.matches(regexToAllow)) {
			        	System.out.println("Regex Not Allowed! Replace!!! => oldValue = " + oldValue + ", newValue = " + newValue);
			            textField.setText(oldValue);
			        }
			        else
			        	textField.setText(newValue);
		    	}
		    }
		});
		return textField;
	}

}
