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
import classes.FileSystem;
import classes.HistoryList;
import classes.Printer;
import java.util.ArrayList;
import java.util.List;

/**
 * The History class is responsible for executing the history command, which
 * displays user's input history.
 */
public class History extends Command {

  /**
   * Runs the History command given an integer input
   *
   * @param fs Any file system to manipulate
   * @param args Arguments from the user (args[0] is the number of history
   * obj)
   * @return The output displayed on terminal
   */
  public String runCommand(FileSystem fs, List<String> args) {
    String output=""; // store output

    // Get global history list
    HistoryList hs = HistoryList.getHist();

    if (args.size() > 0) {
      try {
        // Get the amount of history objects
        int amount = Integer.parseInt(args.get(0));
        // Amount specified cannot be negative
        if (amount < 1) {
          Printer.writeError("Specified history amount "
              + "must be greater than 0.");
          return "";
        }
        List<String> history = hs.getHistory(amount);
        // Go through each command and print its index and details
        for (String s : history) {
          output += s + "\n";
        }
      } catch (Exception e) {
        // In case the argument is not an integer
        Printer.writeError("History commands requires one "
            + "integer command.");
      }
    } else {
      // Optional argument
      List<String> history = hs.getHistory(-1);
      // Iterate through each item in history
      for (String s : history) {
        output += s + "\n";
      }
    }
    // Remove the last \n
    if (output.length() > 1) {
      output = output.substring(0,output.length()-1);
    }
    return output;
  }

  /**
   * Adds the string to a global historylist
   *
   * @param cmd A string command from the terminal
   */
  public static void add(String cmd) {
    // Add command to the history list
    HistoryList.getHist().add(cmd);
  }

  public static void main(String[] args) {
    History h = new History();
    add("hello");
    add("next");
    add("memes");
    FileSystem fs = FileSystem.getFS();
    List<String> mistakes = new ArrayList<String>();
    mistakes.add("4");
    String s = h.runCommand(fs, mistakes);
    System.out.println(s);
  }
}
