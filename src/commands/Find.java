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
import classes.PathParser;

/**
 * The find class is responsible for executing the find command, which
 * finds files fulfilling certain requirements.
 */
public class Find extends Command {

 /**
  * Helper for runCommand.
  * @param path Given path in string form.
  * @param fs The file system we are working with
  * @return True or False if path is valid
  */
 private boolean checkPath(String path, FileSystem fs) {
  if ((!ErrorChecker.checkPathValidity(path) ||
    !PathChecker.checkPath(fs, path))) {
   System.out.println(path + " is an invalid path.");
   return false;
  } else {
   return true;
  }
 }

 /**
  * Executes the find command, returning all files which match the
  * requirements.
  * 
  * @param fs The file system we are working with
  * @param argList The List of parameters all command classes use.
  * @return String representation of all found files.
  */
 public String runCommand(FileSystem fs, List < String > argList) {
  //First check that the name and type are specified
  if (!(argList.contains("-type") && argList.contains("-name"))) {
   System.out.println("The -type or -name parameter is missing");
   return "";
  }
  //Now we can assume that the type and name have been passed in
  //Grab the type and name
  String type = argList.get(argList.indexOf("-type") + 1);
  String name = argList.get(argList.indexOf("-name") + 1);
  name = name.replaceAll("\"", "");
  String result = "The following directories " +
   "satisfy the given name and type";


  //Check that the proper type has been provided
  if (!((type.equals("f")) || type.equals("d"))) {
   System.out.println("Please provide a proper type.");
   return "";
  }

  //Grab the index of the last path given
  int lastPathLocation = argList.indexOf("-type");

  //Create argList of solely paths
  List < String > pathList = argList.subList(0, lastPathLocation);

  //Go through every path passed in
  for (String path: pathList) {
   //Check every path
   if (!checkPath(path, fs)) {} else {
    String folderPath;
    String folderName;
    //separate file name from file path
    List < String > separatedPath = PathParser.separateFileFromPath(path);
    folderPath = separatedPath.get(0);
    folderName = separatedPath.get(1);
    File actualFile;
    File parentActualFile = Navigate.navigateTo(fs, folderPath);
    if (parentActualFile.hasParent()) {
    	actualFile =
    	((Folder) parentActualFile).getFile(folderName);
    }
    else {
    	actualFile = parentActualFile;
    }
    //if the path is a textfile, return error
    if (!actualFile.hasParent()) {
    	result += "\n/"+name;
    	return result;
    }
    if (actualFile.getType().equals("TEXTFILE")) {
     System.out.println(
      "Textfiles cannot contain any other files.");
     continue;
    }
    //otherwise get contents of the file
    else {
     List < File > folderContents =
      ((Folder) actualFile).getContents();
     // if its a directory, check if it contains 
     // a file with the same name as given
     if (type.equals("d")) {
      for (File file: folderContents) {
       if (file.getName().equals(name) &&
        file.getType().equals("FOLDER")) {
        result += "\n" + file.getParent().getName();
       }
      }
     }
     //same idea as above
     else {
      for (File file: folderContents) {
       if (file.getName().equals(name) &&
        file.getType().equals("TEXTFILE")) {
        result += "\n" + file.getParent().getName();
       }
      }
     }
    }
   }
  }
  return result;
 }
}
