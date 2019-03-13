package commands;

import java.util.List;

import classes.CWDString;
import classes.Command;
import classes.ErrorChecker;
import classes.File;
import classes.FileSystem;
import classes.Folder;
import classes.Navigate;
import classes.PathChecker;
import classes.TextFile;

/**
 * The CP class is responsible for executing the copy command, which copies
 * a file to a new location without removing the old one.
 */
public class CP extends Command {
 /**
  * Helper for runCommand.
  * @param path Given path in string form.
  * @param fs The file system we are working with
  * @return get(0) is path get(1) is filename
  */
 public String[] findFolder(String path, FileSystem fs) {
  String textFileName;
  String folderPath;
  // Create variable keeping track of the last slash to know if
  // working with current directory or not
  int splitDir = path.lastIndexOf("/");
  // Root directory
  if (splitDir == 0) {
   textFileName = path.substring(1);
   folderPath = "/";
  } else if (splitDir == -1) {
   textFileName = path;
   folderPath = CWDString.getString(fs);
  } else {
   textFileName = path.substring(splitDir + 1, path.length());
   folderPath = path.substring(0, splitDir + 1);
  }

  String[] stringArr = {
   folderPath,
   textFileName
  };
  return stringArr;
 }

 /**
  * Helper for runCommand.
  * @param path Given path in string form.
  * @param fs The file system we are working with
  * @return True or False if path is valid
  */
 public boolean checkPath(String path, FileSystem fs) {
  if ((!ErrorChecker.checkPathValidity(path) ||
    !PathChecker.checkPath(fs, path))) {
   System.out.println(path + " is an invalid path.");
   return false;
  } else {
   return true;
  }
 }
 /**
  * Helper for runCommand.
  * @param recurseFolder the folder that is to be copied
  * @param fs The file system we are working with
  * @param copyFolder the folder that is a copy of original
  * @return 
  */
 public void recursiveCPHelper(
  File recurseFolder, FileSystem fs, File copyFolder) {
  if (recurseFolder.getType().equals("FOLDER")) {
   if (((Folder) recurseFolder).isEmpty()) {
    Folder copy = new Folder(recurseFolder.getName());
    ((Folder) copyFolder).addFile(copy);
    copy.setParent((Folder) copyFolder);
    recursiveCPHelper(
     recurseFolder.getNextFile(), fs, copyFolder.getNextFile());
   } else {
    Folder copy = new Folder(recurseFolder.getName());
    ((Folder) copyFolder).addFile(copy);
    copy.setParent((Folder) copyFolder);
    // if the next file belongs to the directly to this folder
    if (!((Folder) recurseFolder).getContents().contains(
      recurseFolder.getNextFile())) {
     recursiveCPHelper(recurseFolder.getNextFile(),
      fs, copyFolder.getNextFile());
    } // if the next file is in a deeper folder
    else {
     recursiveCPHelper(
      recurseFolder.getNextFile(), fs, copyFolder);
    }
   }
  }
  // If the next file is a textfile 
  else {
   TextFile copy = new TextFile(recurseFolder.getName());
   copy.overwrite(((TextFile) recurseFolder).getText());
   ((Folder) copyFolder).addFile(copy);
   if (recurseFolder.getParent().getContents().contains(
     recurseFolder.getNextFile()))
    recursiveCPHelper(recurseFolder.getNextFile(), fs, copyFolder);
  }

 }

 /**
  * Runs the copy command.
  * 
  * @param fs The file system we are working with.
  * @param argList The list of params all command classes use.
  * @return The string output on the terminal.
  */
 public String runCommand(FileSystem fs, List < String > argList) {
  // If there isn't solely a oldpath/newpath given.
  if (argList.size() != 2) {
   System.out.println(
    "Invalid input; must provide an oldpath and newpath.");
   return "";
  }

  // Separated the paths from names of files
  String[] oldPath = findFolder(argList.get(0), fs);
  String[] newPath = findFolder(argList.get(1), fs);
  Folder oldPathFolder = (Folder) Navigate.navigateTo(fs, oldPath[0]);
  Folder newPathFolder = (Folder) Navigate.navigateTo(fs, newPath[0]);

  // Check if the given paths are valid
  if (!(checkPath(oldPath[0], fs) && checkPath(newPath[0], fs))) {
   return "";
  }
  String oldPathType = oldPathFolder.getFile(oldPath[1]).getType();
  String newPathType = newPathFolder.getFile(newPath[1]).getType();
  // If copying a textfile to directory
  if (oldPathType.equals("TEXTFILE") &&
   newPathType.equals("FOLDER")) {
   Folder newLocation =
    (Folder) newPathFolder.getFile(newPath[1]);
   /*
    * If the new folder doesn't already have a file 
    * with the same name
    */
   if (newLocation.hasFile(oldPath[1])) {
    File fileToRemove = newLocation.getFile(oldPath[1]);
    newLocation.removeFile(fileToRemove);
   }
   if (!newPathFolder.hasFile(oldPath[1]) ||
    oldPath[0].equals(newPath[0])) {
    // get the oldpaths textfile
    TextFile oldTextFile =
     (TextFile)(oldPathFolder.getFile(oldPath[1]));
    // create a new textfile in the newpath
    TextFile newTextFile =
     Echo.createFile(
      fs, newPath[0] + newPath[1], oldPath[1]);
    // put the contents of the oldtextfile in the new one
    newTextFile.overwrite(oldTextFile.getText());
   }
  }
  // If copying directory to directory (Recursive content copy)
  else if ((oldPathType.equals("FOLDER")) &&
   newPathType.equals("FOLDER")) {
   Folder recurseFolder =
    (Folder) oldPathFolder.getFile(oldPath[1]);
   Folder newLocation =
    (Folder) newPathFolder.getFile(newPath[1]);

   Folder copyFolder = new Folder(recurseFolder.getName());
   for (File file: recurseFolder.getContents()) {
    try {
     recursiveCPHelper(file, fs, copyFolder);
    } catch (java.lang.ArrayIndexOutOfBoundsException ex) {}
    catch (java.lang.NullPointerException ex) {}
   }
   if (newLocation.hasFile(copyFolder.getName())) {
    File fileToRemove = newLocation.getFile(copyFolder.getName());
    newLocation.removeFile(fileToRemove);
   }
   newLocation.addFile(copyFolder);
   copyFolder.setParent(newLocation);
  }
  // final case of copying textfile textfile (overwrite)
  else if ((oldPathType.equals("TEXTFILE")) &&
   newPathType.equals("TEXTFILE")) {
   TextFile oldText = (
    (TextFile) oldPathFolder.getFile(oldPath[1]));
   TextFile newText = (
    (TextFile) newPathFolder.getFile(newPath[1]));
   newText.overwrite(oldText.getText());
  }
  // and if a directory is being copied to a textfile (error)
  else {
   System.out.println("Cannot copy a directory into a textfile.");
  }
  return "";
 }
}
