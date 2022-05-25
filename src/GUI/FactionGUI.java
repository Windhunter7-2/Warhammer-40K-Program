package GUI;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import External.GUI_Helpers;
import External.ImageEditing;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import mainFunctionality.FileIO;

public class FactionGUI {
	
	/**
	 * Spacing to give each section in the GUI.
	 */
	private final int spacing = 10;
	
	/**
	 * The root factions folder, i.e. "Warhammer-40K-Program/Factions/".
	 */
	private String factionsDirectory;
	
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
	private FileIO io = new FileIO(factionsDirectory);
	
	/**
	 * Constructor.
	 * @param factionsDirectory The root factions folder, i.e. "Warhammer-40K-Program/Factions/"
	 */
	public FactionGUI(String factionsDirectory)
	{
		this.factionsDirectory = factionsDirectory;
	}
	
	/**
	 * Note: Only will look for folders with name having "faction" in it!
	 * Get (Or update) the list of factions in the main GUI.
	 * @param curFactionsList The *global* factions list to be updated/fetched
	 * @return The updated factions list
	 */
	public VBox factionsList(VBox curFactionsList)
	{
		//VBox Setup
		VBox factions = new VBox();
		factions.setSpacing(spacing * 3);
		factions.getChildren().add(new VBox(new Text("\n")));
		
		//Get List of Factions Folders
		File folder = new File(factionsDirectory);
		File [] factionsList = folder.listFiles();
		int longestNumChars = 0;
		ArrayList<String> factionNames = new ArrayList<String>();
		for (int i = 0; i < factionsList.length; i++)
		{
			//Skip a File/Folder That Is NOT a Faction
			String curFactionName = factionsList[i].getName();
			if ( (!curFactionName.toLowerCase().contains("faction")) || (!factionsList[i].isDirectory()) )
				continue;
			
			//Add Faction to List, and Check for Largest Length Name
			factionNames.add(curFactionName);
			if (curFactionName.length() > longestNumChars)
				longestNumChars = curFactionName.length();
		}
		
		//Get GUI List of Factions Folders
		for (int i = 0; i < factionNames.size(); i++)
		{
			//Create a Button Section for the Current Faction
			String curFactionName = factionNames.get(i);
			String curFactionNameTrimmed = trimFactionName(curFactionName);
			HBox curFaction = factionOptions(curFactionName, curFactionNameTrimmed, longestNumChars);
			factions.getChildren().add(curFaction);
		}
		
		//Resize VBox to Be a Bit Larger
		resizer.resize(factions, 1.5, 1.5);
		return factions;
	}
	
	/**
	 * These are the options for each faction. Namely, a Text for the faction itself, and then 3 buttons: One for editing that
	 * faction, one for creating an army list for that faction, and one for removing the faction (Which prompts twice).
	 * @param factionName The faction to associate the buttons with
	 * @return The HBox containing these various faction options
	 */
	private HBox factionOptions(String factionName, String factionNameTrimmed, int longestNumChars)
	{
		//HBox Setup
		HBox factionOptions = new HBox();
		factionOptions.setSpacing(spacing);
		
		//The Faction Name Text
		VBox factionNameBox = new VBox();
		Text factionNameTxt = gui.createText(factionNameTrimmed, true, false, false);
		factionNameBox.getChildren().add(factionNameTxt);
		
		//Make the Columns Evenly Wide (To Look a Lot Nicer!)
		int offsetWidth = (longestNumChars * 8);
		factionNameBox.setPrefWidth(offsetWidth);
		
		//The Edit Faction Button
		HBox buttonsBox = new HBox();
		buttonsBox.setSpacing(spacing);
		Button editFaction = new Button("Edit Faction Units");
		editFaction.setOnAction(e -> {
			RosterGUI roster = new RosterGUI(factionsDirectory + factionName + "/", factionNameTrimmed);
			roster.editArmyRoster();
		});
		
		//The Create Army Button
		Button createArmy = new Button("Create Army List");
		createArmy.setOnAction(e -> {
			ArmyListGUI armyList = new ArmyListGUI(factionsDirectory + factionName + "/", factionNameTrimmed);
			armyList.createArmyList();
		});
		
		//The Remove Faction Button
		Button removeFaction = new Button("Remove Faction");
		removeFaction.setOnAction(e -> removeFaction(factionName, factionNameTrimmed) );
		
		//Combine Everything
		buttonsBox.getChildren().addAll(createArmy, editFaction, removeFaction);
		factionOptions.getChildren().addAll(factionNameBox, buttonsBox);
		return factionOptions;
	}
	
	/**
	 * Trims a given faction name to remove the word "faction" (Not case-sensitive at all) and any surrounding _ or - symbols
	 * it might have.
	 * @param factionName The faction name to trim
	 * @return The trimmed faction name
	 */
	private String trimFactionName(String factionName)
	{
		String trimmedFactionName = factionName.replaceAll("([_-]*)(f|F)(a|A)(c|C)(t|T)(i|I)(o|O)(n|N)([_-]*)", "");
		return trimmedFactionName;
	}
	
	/**
	 * If and only if the user says yes to two prompts confirming deletion, will remove the entire faction that was chosen.
	 * @param factionName Which faction to request deletion (Including the "faction" in the String
	 * @param factionNameTrimmed Which faction to request deletion (The name only)
	 */
	private void removeFaction(String factionName, String factionNameTrimmed)
	{
		//Double Prompt Removal; if Either Is a "No", Return!
		int prompt1 = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the faction \"" + factionNameTrimmed
				+ "\"? (This action cannot be undone)", "40K Program - Are You Sure You Want to Delete the Current Faction?",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (prompt1 == 0)
		{
			int prompt2 = JOptionPane.showConfirmDialog(null, "Are you REALLY sure you want to delete the faction \"" + factionName
					+ "\"? (This action cannot be undone)", "40K Program - Are You Sure You Want to Delete the Current Faction?",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (prompt2 == 0)
				io.deleteFileOrFolder(factionName);
		}
	}

}
