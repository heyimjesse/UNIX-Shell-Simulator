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

import classes.Command;
import classes.DirectoryStack;
import classes.FileSystem;
import classes.Printer;
import java.util.List;

/**
 * The PopD class is responsible for executing the popd command.
 */
public class PopD extends Command {

  /**
   * Changes the CWD to the last saved directory.
   *
   * @param f The file system we are working with
   * @param args An empty list
   * @return The output displayed on terminal.
   */
  @Override
  public String runCommand(FileSystem f, List<String> args) {
    // Change the working directory of FileSystem object to the last saved path
    if (args.size() == 0) {
      DirectoryStack.getDS().pop(f);
    } else {
      Printer.writeError("Command 'popd' has no arguments");
    }
    return "";
  }
}
