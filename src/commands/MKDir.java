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

import java.util.ArrayList;
import java.util.List;
import classes.Command;
import classes.ErrorChecker;
import classes.File;
import classes.FileSystem;
import classes.Folder;
import classes.Navigate;
import classes.PathChecker;

/**
 * The MKDir class is responsible for executing the mkdir command, which
 * creates new directories.
 */
public class MKDir extends Command {

  /**
   * Create a new folder at every specified location.
   *
   * @param fs Our main directory, which contains a pointer to the root and
   * cwd
   * @param pathList A list of paths representing folders to be created
   * @return The output displayed on terminal
   */
  @Override
  public String runCommand(FileSystem fs, List<String> pathList) {
    if (pathList.size() == 0) {
      System.out.println("Name of directory cannot be empty");
      return "";
    }

    // For every path given we need to perform actions
    for (String currPath : pathList) {
      if (!ErrorChecker.checkPathValidity(currPath)) {
        System.out.println("Invalid characters in: " + currPath);
        continue;
      }
      String folderPath;
      String folderName;
      File dFile;

      // This splits the path into the name of new folder + path to the folder
      int splitMarker = currPath.lastIndexOf("/");
      // this means we're making a folder in our current working directory
      if (splitMarker == -1) {
        folderPath = "";
        folderName = currPath;
      } else if (splitMarker == 0) {
        folderPath = "/";
        folderName = currPath.substring(1, currPath.length());
        if (folderName.length() == 0) {
          System.out.println("Name of directory cannot be empty");
          continue;
        }
      } else {
        folderPath = currPath.substring(0, splitMarker);
        folderName = currPath.substring(splitMarker + 1, currPath.length());
      }

      if (!PathChecker.checkPath(fs, folderPath)) {
        System.out.println("Invalid path given: " + currPath);
        continue;
      } else {
        dFile = Navigate.navigateTo(fs, folderPath);
        if (dFile.getType().equals("TEXTFILE")) {
          System.out.println("Invalid path given.");
          continue;
        }

        Folder dFolder = (Folder) dFile;
        if (dFolder.hasFile(folderName)) {
          System.out.println(("Folder already contains: " + folderName));
          continue;
        }
        Folder newFolder = new Folder(folderName);
        dFolder.addFile(newFolder);
        newFolder.setParent(dFolder);
      }
    }
    return "";
  }

  public static void main(String[] args) {
    FileSystem d = FileSystem.getFS();
    Folder root = d.getRoot();
    Folder d1 = new Folder("d1");
    Folder d2 = new Folder("d2");
    Folder d3 = new Folder("d3");

    root.addFile(d1);
    d1.addFile(d2);
    d2.addFile(d3);

    d1.setParent(root);
    d2.setParent(d1);
    d3.setParent(d2);

    // our current working directory is d2
    d.setCWD(d2);

    List<String> pathList = new ArrayList<String>();
    pathList.add("/music");
    pathList.add("/d1/d2/docs");
    pathList.add("math");
    pathList.add("../lamp");
    pathList.add("d3/calc");
    pathList.add("/d1/@"); // this should be an error

    System.out.println("Root CONTENTS:");
    root.printContents();
    System.out.println("\nd1 CONTENTS:");
    d1.printContents();
    System.out.println("\nd2 CONTENTS:");
    d2.printContents();
    System.out.println("\nd3 CONTENTS:");
    d3.printContents();
  }
}
