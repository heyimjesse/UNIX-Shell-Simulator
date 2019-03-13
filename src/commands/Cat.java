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
import classes.Folder;
import classes.Navigate;
import classes.PathChecker;
import classes.TextFile;

/**
 * The Cat class is responsible for performing the cat command, which displays
 * the contents of a text file.
 */
public class Cat extends Command {

  /**
   * Displays the contents of the specified file.
   *
   * @param fs The file system we are working with.
   * @param pathList The parsed list of input.
   */
  public String runCommand(FileSystem fs, List<String> pathList) {
    /**
     * concatString An empty string.
     * textFileName Name of the text file.
     * folderPath Path to folder.
     */
    String concatString = "";
    String textFileName;
    String folderPath;

    for (String path : pathList) {
      /*
       * Create instance variable keeping track of the last slash to know
       * whether to work with current directory or not.
       */
      int splitDir = path.lastIndexOf("/");
      // Root directory
      if (splitDir == 0) {
        textFileName = path.substring(1);
        folderPath = "/";
      } else {
        textFileName = path.substring(splitDir + 1, path.length());
        folderPath = path.substring(0, splitDir + 1);
      }
      // Check to see if working with cwd
      if (!(folderPath.equals(CWDString.getString(fs)))) {
        folderPath += CWDString.getString(fs);
      }
      // If path is valid
      if (PathChecker.checkPath(fs, folderPath)) {
        // Go to the given folder
        Folder actualFolder = (Folder) Navigate.navigateTo(fs, folderPath);
        // Check if the file exists in the folder
        if (actualFolder.hasFile(textFileName)) {
          // Check if it's a textfile
          if (!actualFolder.getFile(textFileName).getType().equals("FOLDER")) {
            TextFile actualTextFile = (TextFile) actualFolder
                .getFile(textFileName);
            concatString += actualTextFile.getText() + "\n------------\n";
          }
          // If it's not a textfile
          else {
            System.out.println("Can only display contents of text files.");
            return "";
          }
        }
      }
      // If path given isn't valid
      else {
        System.out.println("Invalid Path");
        return "";
      }

    }
    return concatString;
  }
}

