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
import classes.Command;
import classes.FileSystem;
import classes.Printer;

/**
 * The Man class is responsible for executing the man command, which displays
 * documentation for commands.
 */
public class Man extends Command {

  /**
   * Prints the documentation of the given command to console.
   *
   * @param fs The file system we are working with
   * @param pathList A list with one entry- the name of the command user wants
   * documentation for
   * @return The output displayed on the terminal.
   */
  @Override
  public String runCommand(FileSystem fs, List<String> pathList) {
    if (pathList.size() == 1) {
      String cmd = pathList.get(0);
      switch (cmd) {
        case "cd":
          return "Changes CWD to given directory d, which can "
              + "be the full directory, or relative to the CWD";
        case "ls":
          return 
              "Takes in an array of string" +
                  " representations of paths.\nIn the order that the "
                  + "paths are listed, if the path is a file, it will "
                  + "print the contents to the console.\nIf the path "
                  + "is a folder, it will print out the name of the fo"
                  + "lder, and the names of the files it holds.";

        case "man":
          return "Prints documentation of the given command.";

        case "mkdir":
          return "Create a new folder at every specified path";

        case "popd":
          return "Changes the CWD to the last saved path";

        case "pushd":
          return "Saves the CWD for later use";

        case "pwd":
          return "Prints out the CWD to console.\n\n"
              + "@param fs The file system we are working with" +
              "\n@param cmd An empty list";

        case "exit":
          return "Exits and closes the shell";

        case "cat":
          return "Prints the contents of the given files" + "in order";

        case "echo":
          return "Overwrites/appends to a textfile.";

        default:
          System.out.println("Please type a valid command.");
          return "";
      }
    } else {
      System.out.println("This command takes exactly one argument");
      return "";
    }
  }
}
