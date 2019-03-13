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

import classes.Printer;
import java.util.List;
import classes.CWDString;
import classes.Command;
import classes.FileSystem;

/**
 * The PWD class is responsible for executing the pwd command.
 */
public class PWD extends Command {

  /**
   * Returns the CWD as a string.
   *
   * @param fs The file system we are working with
   * @param emptyList An empty list which will also function as error check
   * @return The CWD as a string.
   */
  @Override
  public String runCommand(FileSystem fs, List<String> emptyList) {
    if (emptyList.size() != 0) {
      Printer.writeError("PWD command doesn't support parameters");
    }
    String cwdString;
    cwdString = CWDString.getString(fs);
    return cwdString;
  }
}
