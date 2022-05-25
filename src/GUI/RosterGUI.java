package GUI;

import External.GUI_Helpers;
import External.ImageEditing;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainFunctionality.FileIO;

public class RosterGUI {
	
	/**
	 * The primary unit types.
	 */
	private String [] primaryUnitTypes = {"HQ", "Troops", "Transports"};

	/**
	 * The secondary unit types.
	 */
	private String [] secondaryUnitTypes = {"Elites", "Fast Attack", "Heavy Support"};
	
	/**
	 * The tertiary unit types.
	 */
	private String [] tertiaryUnitTypes = {"Flyers", "Lords of War"};
	
	/**
	 * Spacing to give each section in the GUI.
	 */
	private final int spacing = 10;
	
	/**
	 * The root factions folder, i.e. "Warhammer-40K-Program/Factions/FactionName/".
	 */
	private String factionDirectory;
	
	/**
	 * The official title of the faction. (For clarity to the user which faction they are editing)
	 */
	private String factionName;
	
	/**
	 * Methods to help make GUI creation a lot more efficient.
	 */
	private GUI_Helpers gui = new GUI_Helpers();
	
	/**
	 * Some additional GUI utilities, primarily for node resizing.
	 */
	private ImageEditing resizer = new ImageEditing();
	
	/**
	 * This is the main directory of the program.
	 */
	private FileIO io = new FileIO(factionDirectory);
	
	/**
	 * Constructor.
	 * @param curFactionDirectory The current faction folder, i.e. "Warhammer-40K-Program/Factions/FactionName/"
	 * @param factionNameTrimmed The faction name itself
	 */
	public RosterGUI(String curFactionDirectory, String factionNameTrimmed)
	{
		this.factionDirectory = curFactionDirectory;
		this.factionName = factionNameTrimmed;
		
	}
	
	//TODO -> EXAMPLE USAGE OF createGUIPanel():
	Stage s = gui.createGUIPanel("Example Title", 500, 500, null, 5, true, new VBox(), new VBox(), new HBox(), new VBox());
	
	//TODO -> Don't forget to use "spacing" on all these methods (Do math on the spacing, rather than hardcoded numbers, if needing
	//			separate spacing amounts), to allow for scalability and maintainability!
	//TODO -> Also be sure to use factionName in things such as titles to be more clarified to the user
	
	public void editArmyRoster()
	{
		//TODO -> Call getUnitTypes(), then create a stage that shows this VBox; also put a "Return" button at the bottom, as well,
		//			which will exit only the current stage (The one you just created!)
		//TODO Hint: Use gui for the stage creation, and resizer to resize the GUI afterwards; you can see FactionGUI.java for an example
	}
	
	private VBox getUnitTypes()
	{
		//TODO -> Use primaryUnitTypes, secondaryUnitTypes, and tertiaryUnitTypes
		//TODO -> In the orders they are, put buttons for each type in an HBox (e.g. HBox 1 will be ["HQ", "Troops", "Transports"])
		//TODO -> For each button, call getUnits(<NAME_OF_BUTTON>) (e.g. getUnits(primaryUnitTypes[0]); for the button that says "HQ")
		return null;
	}
	
	private void getUnits(String unitType)
	{
		//TODO -> Get list of all folders in (factionDirectory + unitType + "/"); if no folders, put a Text along the lines of
		//			"No units currently for this unit type"; otherwise, do:
		//					-> Create a button for each unit, with the text of the button being the same as the name
		//					-> Upon clicking the button, do getUnit(<Button Text / Unit Name>)
		//TODO -> Display the list of buttons on a new stage; also put a "Return" button at the bottom, as well,
		//			which will exit only the current stage (The one you just created!)
		//TODO -> Also on this same stage, put also the following button: "New Unit", which, if clicked, will call
		//			createUnit(unitType, <Button Text / Unit Name>), followed by
		//			updateUnit(unitType, <Button Text / Unit Name>)
		//TODO Hint: Use gui for the stage creation, and resizer to resize the GUI afterwards; you can see FactionGUI.java for an example
	}
	
	private void getUnit(String unitType, String unitName)
	{
		//TODO -> Get list of all files in (factionDirectory + unitType + "/" + unitName + "/"); use io.getText() on all these files,
		//			and then convert these inputs into getUnitGUI(), as variadic arguments for "inputsForFields" variable
		//TODO Note: If you work on this one, you will need to know the file structure from Evan
	}
	
	private void createUnit(String unitType, String unitName)
	{
		//TODO -> Create a folder for the unit, and then call io.setText() for every file for the unit (Put just blank text in each file)
		//TODO Note: If you work on this one, you will need to know the file structure from Evan
	}
	
	private void updateUnit(String unitType, String unitName, String...inputsForFields)
	{
		//TODO -> Create a GUI that maps to what the text files look like, and if and only if inputsForFields is not blank,
		//			copy the fields from those variadic arguments into the actual fields themselves
		//TODO -> Also put two buttons, one being "Cancel", which just returns, and the other being "Save", which calls io.setText(),
		//			converting the fields back to text form to be inserted into the files
		//TODO Note: If you work on this one, you will need to know the file structure from Evan
		//TODO Hint: Use gui for the stage creation, and resizer to resize the GUI afterwards; you can see FactionGUI.java for an example
	}
	
}
