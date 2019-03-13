// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: zhang568
// UT Student #: 1000125170
// Author: Jesse Zhang
//
// Student2:
// UTORID user_name: chengmog
// UT Student #: 1004415769
// Author: Mogen Cheng
//
// Student3:
// UTORID user_name: debalke2
// UT Student #: 1004390081
// Author: Saron Debalkew
//
// Student4:
// UTORID user_name: luhaora1
// UT Student #: 1004043091
// Author: Haoran Lu
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package commands;

import java.util.List;
import classes.CWDString;
import classes.Command;
import classes.FileSystem;
import classes.PathChecker;
import classes.Folder;
import classes.Navigate;
import classes.TextFile;

/**
 * The Echo class is responsible for executing the echo command, which either
 * prints out a string or appends/overwrites a text file.
 */
public class Echo extends Command {

 /**
  * If only "STRING" parameter is given then print contents of "STRING" to
  * shell. If given "STRING" and "OUTFILE" parameters creates "OUTFILE" with
  * "STRING" inside.
  *
  * @param fs The file system we are working with.
  * @param pathList List of parsed string parameters
  */
 // Gets the string from the args list
 public String getString(String rawInput) {
  int startIndex = rawInput.indexOf("\"");
  int stopIndex = rawInput.lastIndexOf("\"");
  String actualString = rawInput.substring(startIndex + 1, stopIndex);
  return actualString;
 }
 // To be able to create textfiles 
 public static TextFile createFile(
     FileSystem fs, String path, String fileName) {
	 Folder folder = (Folder)Navigate.navigateTo(fs, path);
	 TextFile newText = new TextFile(fileName);
	 newText.setParent(folder);
	 folder.addFile(newText);
	 return newText;
	 } 
 public String runCommand(
     FileSystem fs, List < String > pathList, String rawInput) {
  /**
   * textFileName Name of text file.
   * folderPath Path to the folder.
   * actualString We start with empty string and build on it.
   * counter Counter to be used to facilitate our method.
   */
  String textFileName;
  String folderPath;
  int counter = 0;
  Folder testFolder;
  if (pathList.get(0).equals(">") || 
	   pathList.get(0).equals(">>")){
	  return "";
  }
  if (pathList.size() < 1 || pathList.size() > 3) {
	  System.out.println("Please input valid parameters.");
	  return "";
  }
  
  if (!((pathList.get(0).startsWith("\"")) && 
      (pathList.get(0).endsWith("\"")))) {
	  System.out.println("Please enter an appropriate string.");
	  return "";
  }

  //Find where the string ends in the pathlist
  for (int x = 0; x < pathList.size(); x++) {
   if (((String) pathList.get(x)).equals(">") ||
    ((String) pathList.get(x)).equals(">>")) {
    break;
   } else {
    counter++;
   }
  }
  String actualString = getString(rawInput);
  // Create new pathList without the string passed in
  List < String > newPList = pathList.subList(counter, pathList.size());
  // Trim the string so there are no more quotes
  // If the only parameter given is a String
  if (newPList.size() == 0) {
   // Print the string to the shell
   System.out.println(actualString);
   return "";
  }

  // If the [> OUTFILE] parameter or [>> OUTFILE] parameter passed in
  else if (newPList.size() == 2) {

   // Create a string representing the actual path
   String actualPath = ((String) newPList.get(1));

   /*
    * Create variable keeping track of the last slash to know if
    * working with current directory or not
    */
   int splitDir = actualPath.lastIndexOf("/");
   // Root directory
   if (splitDir == 0) {
		textFileName = actualPath.substring(1);
		folderPath = "/";
	}
	else if (splitDir == -1) {
		textFileName = actualPath;
		folderPath = CWDString.getString(fs);
	}
	else {
		textFileName = actualPath.substring(splitDir + 1, actualPath.length());
		folderPath = actualPath.substring(0, splitDir + 1);
	}

   //If the path given is a valid one
   if (PathChecker.checkPath(fs, folderPath)) {
    // If they want to overwrite/create a textfile
    if (((String) newPList.get(0)).equals(">")) {
     // **CANT BE A TEXT FILE (for later error handling)
     testFolder = (Folder) Navigate.navigateTo(fs, folderPath);
     // If the file already exists
     if (testFolder.hasFile(textFileName)) {
      // If the file is actually a textfile
      if (testFolder.getFile(textFileName).getType()
       .equals("TEXTFILE")) {
       TextFile newText = (TextFile)(testFolder.getFile(textFileName));
       newText.overwrite(actualString);
      }
      // If the file isn't a textfile
      else {
       System.out.println("Invalid file type.");
       return "";
      }
     }
     // If it doesn't exist yet
     else {
      TextFile newText = new TextFile(textFileName);
      newText.overwrite(actualString);
      newText.setParent(testFolder);
      testFolder.addFile(newText);
     }

    }

    // If they want to append to an existing textfile
    else if (((String) newPList.get(0)).equals(">>")) {
     testFolder = (Folder) Navigate.navigateTo(fs, folderPath);
     // If the file already exists
     if (testFolder.hasFile(textFileName)) {
      // If the file is a textfile
      if (testFolder.getFile(textFileName).getType()
       .equals("TEXTFILE")) {
       TextFile newText = (TextFile)(testFolder.getFile(textFileName));
       newText.appendLine(actualString);
      }
      // If the file isn't a textfile
      else {
       System.out.println("Invalid file type.");
      }
     }
     // If it doesn't exist
     else {
      TextFile newText = new TextFile(textFileName);
      newText.appendLine(actualString);
      newText.setParent(testFolder);
      testFolder.addFile(newText);
     }
    }
   }
   // If the path isnt valid
   else {
    System.out.println("Invalid Path.");
    return "";
   }
  }
  return "";
 }
}

