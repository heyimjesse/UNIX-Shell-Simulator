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
import classes.File;
import classes.FileSystem;
import classes.Folder;
import classes.Navigate;
import classes.PathChecker;

/**
 * The CD class is responsible for executing the cd command, which changes the
 * current working directory.
 */
public class CD extends Command {

  /**
   * Changes CWD to given directory d. d can be the full directory, or relative
   * to the CWD.
   *
   * @param d The file system we are working with
   * @param pList A list with one entry- the path of the directory to be
   * changed to
   * @return The output displayed on terminal.
   */
  @Override
  public String runCommand(FileSystem fs, List<String> pList) {
    if (pList.size() != 1) {
      System.out.println("Invalid parameters.");
      return "";
    }
    String dPath = pList.get(0);
    if (!PathChecker.checkPath(fs, dPath)) {
      System.out.println("Invalid directory path.");
      return "";
    } else {
      File newCWD = Navigate.navigateTo(fs, dPath);
      if (newCWD.getType().equals("TEXTFILE")) {
        System.out.println("Cannot change directory to a text file.");
        return "";
      } else {
        fs.setCWD((Folder) newCWD);
        return "";
      }
    }
  }

  public static void main(String[] args) {
    CD cd = new CD();
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

    d.setCWD(d1);

    List<String> pList = new ArrayList<String>();
    pList.add("/d1/d2");

    cd.runCommand(d, pList);
    System.out.println(d.getCWD().getName());
  }
}
