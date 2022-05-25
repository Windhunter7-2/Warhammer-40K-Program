package mainFunctionality;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {

	/**
	 * This is the main directory of the program (i.e. "getMainDirectory() + Warhammer-40K-Program/").
	 */
	private String mainDirectory;
	
	/**
	 * Constructor.
	 * @param mainDirectory This is the main directory of the program (i.e. "getMainDirectory() + Warhammer-40K-Program")
	 */
	public FileIO(String mainDirectory)
	{
		this.mainDirectory = mainDirectory;
	}
	
	/**
	 * This reads the given file, fileNameWithExt, and returns it as a String.
	 * @param fileNameWithExt The file to read from
	 * @return The text in the file
	 * @throws IOException
	 */
	public String getText(String fileNameWithExt) throws IOException
	{
		String retString = new String("");
		BufferedReader br = new BufferedReader(new FileReader(mainDirectory + fileNameWithExt));
		int next = br.read();
		while (next != -1) {
			retString = retString + (char)next;
			next = br.read();
		}
		br.close();
		return retString;
	}
	
	/**
	 * This writes to the file fileNameWithExt, with the contents being written as the given String, contents.
	 * @param fileNameWithExt The file being written to (Overwrites, rather than concatenates)
	 * @param contents The contents to put into the file
	 * @throws IOException
	 */
	public void setText(String fileNameWithExt, String contents) throws IOException
	{
		File f = new File(mainDirectory + fileNameWithExt);
		f.createNewFile(); //Creates a new file if necessary.
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));	
		bw.write(contents);
		bw.close();
	}
	
	/**
	 * Delete the given file (Or folder) that is the "fullPath" given.
	 * @param fullPath The full path to the file/folder that requests deletion (From the root of the main directory)
	 */
	public void deleteFileOrFolder(String fullPath)
	{
		//TODO -> Delete the Given File Or Folder (Located at mainDirectory+fullPath)
		String placeholder;	//Temporary Placeholder for a Reminder That This Needs Implementation!
		System.out.println("Deleting: " + mainDirectory + fullPath);
	}
	

}
