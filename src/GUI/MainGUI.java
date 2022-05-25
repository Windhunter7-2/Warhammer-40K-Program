package GUI;

import External.GUI_Helpers;
import External.MainDirectory;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainFunctionality.FileIO;

public class MainGUI extends Application {
	
	/**
	 * File I/O as needed for settings files.
	 */
	private FileIO io;
	
	/**
	 * Methods to help make GUI creation a lot more efficient.
	 */
	private GUI_Helpers gui = new GUI_Helpers();
	
	/**
	 * This is how far apart to have each box on the main GUI, to make it look significantly nicer.
	 */
	private final int mainGUI_spacing = 10;
	
	/**
	 * This is the main GUI.
	 * @param s The stage
	 */
	public void startGUI(Stage s)
	{
		//Get the Main Directory
		MainDirectory md = new MainDirectory();
		String mainDirectory = (md.getMainDirectory() + "Warhammer-40K-Program/");
		io = new FileIO(mainDirectory);
		
		//TESTING CURRENT OTHER CLASSES HERE!!!
		FactionGUI factionGUI_Tester = new FactionGUI("Z:/Blender Documents/");
		VBox factionGUI_vbox = new VBox();
		factionGUI_vbox = factionGUI_Tester.factionsList(factionGUI_vbox);
		
		//TODO -> !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//TODO -> !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//TODO -> !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//TODO -> !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//TODO -> !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//TEST CODE... REPLACE!!!
		VBox zzzA = new VBox();
		Text zzzB = gui.createText("Hello World", true, true, true, 30);
		zzzA.getChildren().add(zzzB);
		s = gui.createGUIPanel("Warhammer 40K Army List Builder", 700, 900, s, 50, false, factionGUI_vbox);
	}
	
	/**
	 * Initializes the program. 
	 * @param nullStage A dummy parameter
	 */
	public void start(Stage nullStage)
	{
		MainGUI start = new MainGUI();
		Stage s = new Stage();
		s.show();
		start.startGUI(s);
	}
	
	/**
	 * The main method. Runs the program.
	 * @param args Commandline arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		//BUTTON EXAMPLE ACTION SPEC:
//		//		buttonName.setOnAction( e -> { //actions here... } );
//
//	}

}
