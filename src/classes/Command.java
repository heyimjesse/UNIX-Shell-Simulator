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
package classes;

import java.util.List;

/**
 * The Command superclass will set the structure for all of its children
 * commands.
 */
public abstract class Command {

  /**
   * All children will contain a method runCommand which executes the given
   * command.
   *
   * @param f A FileSystem object that we are working with.
   * @param s List of strings representing the parsed parameters.
   */
  public String runCommand(FileSystem f, List<String> s) {
    return "";
  }
  public String runCommand(FileSystem f, List<String> s, String rawInput) {
    return "";
  }
  
  
}

