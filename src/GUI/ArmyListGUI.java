package GUI;

import External.GUI_Helpers;
import External.ImageEditing;
import mainFunctionality.FileIO;

public class ArmyListGUI {
	
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
	public ArmyListGUI(String curFactionDirectory, String factionNameTrimmed)
	{
		this.factionDirectory = curFactionDirectory;
		this.factionName = factionNameTrimmed;
	}
	
	//TODO -> Don't forget to use "spacing" on all these methods (Do math on the spacing, rather than hardcoded numbers, if needing
	//			separate spacing amounts), to allow for scalability and maintainability!
	
	public void createArmyList()
	{
		//TODO -> Create plan for this!
	}

}
