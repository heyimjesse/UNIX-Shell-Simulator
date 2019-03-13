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

import classes.CWDString;
import classes.Command;
import classes.DirectoryStack;
import classes.FileSystem;
import classes.Folder;
import classes.Navigate;
import classes.PathChecker;
import classes.Printer;
import java.util.ArrayList;
import java.util.List;

/**
 * The PushD class is responsible for executing the pushd command.
 */
public class PushD extends Command {

  /**
   * Runs the PushD command with user inputs
   * 
   * @param f The file system we are working with
   * @param args An empty list
   * @return The output displayed on terminal.
   */
  @Override
  public String runCommand(FileSystem f, List<String> args) {
    if (args.size() == 1) {
      pushTo(f, args.get(0));
      return "";
    } else {
      // This command requires only one command
      Printer.writeError("Please input a directory to change to");
      return "";
    }
  }

  /**
   * Given a DirectoryStack and Shell's FileSystem, pushes the current working
   * directory of FileSystem to the DirectoryStack
   *
   * @param futurePath The path to navigate user to once directory is pushed
   * @param d A file system object
   */
  static void pushTo(FileSystem d, String futurePath) {
    // Get the global DirectoryStack
    DirectoryStack ds = DirectoryStack.getDS();
    // Push the FileSystem to the stack
    ds.push(d);
    // Navigate FileSystem to futurePath
    if (PathChecker.checkPath(d, futurePath)) {
      d.setCWD((Folder) Navigate.navigateTo(d, futurePath));
    } else {
      Printer.writeError("Cannot change directory into the specified path.");
    }
  }

  // TESTS
  public static void main(String[] args) {
    CD cd = new CD();
    DirectoryStack ds = DirectoryStack.getDS();
    FileSystem d = FileSystem.getFS();

    Folder root = d.getRoot();
    Folder one = new Folder("one");
    Folder two = new Folder("two");
    Folder three = new Folder("three");

    root.addFile(one);
    root.addFile(two);
    one.setParent(root);
    two.setParent(root);

    one.addFile(three);
    three.setParent(one);

    // Go to one/three
    PushD.pushTo(d, "");

    ArrayList<String> arr = new ArrayList<String>();
    arr.add("/one/three");

    cd.runCommand(d, arr);
    System.out.println(CWDString.getString(d));
    // add to folder
    PushD.pushTo(d, "");
    System.out.println(ds.toString());
  }
}
