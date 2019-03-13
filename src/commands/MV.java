package commands;

import classes.Command;
import classes.ErrorChecker;
import classes.File;
import classes.FileSystem;
import classes.Folder;
import classes.Navigate;
import classes.PathChecker;
import classes.PathParser;
import classes.Printer;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

/**
 * The MV class is responsible for executing the MV command, which
 * moves a file to a specified location.
 */
public class MV extends Command {

  public MV() { }

  /**
   * Executes the MV command.
   * 
   * @param fs The file system we are working with
   * @param args The list of params all commands use
   * @return The output displayed on console.
   */
  @Override
  public String runCommand(FileSystem fs, List<String> args) {
    // The paths passed by the user
    if (args.size() != 2) {
      Printer.writeError("MV: Invalid arguments");
      return "";
    }
    // Get arguments
    String fromPath = args.get(0);
    String toPath = args.get(1);
    // Separate FROM path
    List<String> fromPathExploded = PathParser.separateFileFromPath(fromPath);
    String fromPathDir = fromPathExploded.get(0);
    String fromPathFileName = fromPathExploded.get(1);
    // Separate TO path
    List<String> toPathExploded = PathParser.separateFileFromPath(toPath);
    String toPathDir = toPathExploded.get(0);
    String toPathFileName = toPathExploded.get(1);
    // Check if the paths are valid
    if (!checkMultiplePaths(fromPathDir, toPathDir)) {
      Printer.writeError("MV: Path is invalid.");
      return "";
    }
    // Check if the destination name is valid
    if (!ErrorChecker.checkNameValidity(toPathFileName)){
      Printer.writeError("MV: Target name is invalid");
      return "";
    }
    // Obtain the TO folder and FROM file
    Folder to = (Folder)this.getPathContents(fs, toPathDir);
    File from = this.getPathContents(fs, fromPath);
    if (from == null) {
      Printer.writeError("MV: " + fromPathFileName + " not found");
      return "";
    }
    // If the "to" folder contains toPathFileName as a Folder, then we preserve
    // the file name of "from"
    // If "to" folder does not contain toPathFileName, toPathFileName will be
    // the renamed path.
    if (to.hasFile(toPathFileName)) {
      if (to.getFile(toPathFileName).getType().equals("TEXTFILE")) {
        // "to" path is actually an existing textfile
        Printer.writeError("MV: Cannot move into a text file.");
        return "";
      }
      // Navigate into the folder
      to = (Folder)to.getFile(toPathFileName);
    } else if (toPathFileName.equals("..") || toPathFileName.equals(".") ||
        toPathFileName.equals("")) {
      // Ignore ".." and "." and "" renames
    } else {
      // Rename the "from" file
      from.rename(toPathFileName);
    }
    // Move the File "from" to the folder "to"
    moveFile(from, to);
    return "";
  }

  /**
   * Returns true if both paths are valid
   * @param strOne First path
   * @param strTwo Second path
   * @return True if both paths are valid
   */
  private boolean checkMultiplePaths(String strOne, String strTwo){
    return PathChecker.checkPath(FileSystem.getFS(), strOne) &&
        PathChecker.checkPath(FileSystem.getFS(), strTwo);
  }


  /**
   * Moves a file from one folder to another
   * @param i Initial file
   * @param d Target destination
   */
  private void moveFile(File i, Folder d) {
    // Unlink parent from child
    Folder parent = i.getParent();
    parent.removeFile(i);
    // Add the folder to the new target folder
    d.addFile(i);
    i.setParent(d);
  }


  /**
   * Gets the File of the directory
   * @param dir A string directory
   * @return The file given
   */
  private File getPathContents(FileSystem fs, String dir) {
    // Separate the path into the path and file name
    List<String> paths = PathParser.separateFileFromPath(dir);
    // Navigate to the path we just checked
    Folder parentDir = (Folder) Navigate.navigateTo(fs, paths.get(0));
    if (paths.get(1).equals("")) {
      // Return the parent directory
      return parentDir;
    } else {
      // Get the file from this folder
      return parentDir.getFile(paths.get(1));
    }
  }

}
