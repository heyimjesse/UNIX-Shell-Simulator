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

import java.util.ArrayList;
import java.util.List;

/**
 * The HistoryList class is responsible for keeping a list of users input
 * history to be used by the History class.
 */
public class HistoryList {

  /**
   * hist Global instance of HistoryList. commands The stored user commands.
   */
  private static HistoryList hist = null;
  private ArrayList<String> commands = null;

  /**
   * Constructor
   */
  private HistoryList() {
    this.commands = new ArrayList<String>();
  }

  /**
   * Returns the global history object.
   *
   * @return global history object
   */
  public static HistoryList getHist() {
    if (hist == null) {
      hist = new HistoryList();
    }
    return hist;
  }

  /**
   * Get the history of user commands
   * @returns a list of the user command history
   */
  public ArrayList<String> getCommands() {
    return commands;
  }
  /**
   * Adds the input to the list.
   *
   * @param input The input we are adding.
   */
  public void add(String input) {
    this.commands.add(input);
  }

  /**
   * Gets an array of size amount, in the order of most recent
   *
   * @param amount An integer amount
   * @return The specified items of history.
   */
  public List<String> getHistory(int amount) {
    List<String> arrToReturn = new ArrayList<String>();
    // Find the index of where to start obtaining commands
    int indexPointer = commands.size() - amount;
    // If the pointer is positive, then we want the last x commands

    // If amount is -1, then we want the entire history
    if (amount == -1) {
      indexPointer = 0;
    }

    if (indexPointer >= 0) {
      //arrToReturn = commands.subList(indexPointer, commands.size()-1);
      // iterate through each item in the array starting at indexPointer
      for (int i = indexPointer; i < commands.size(); i++) {
        arrToReturn.add((i + 1) + ". " + commands.get(i));
      }
    } else {
      // Amount is greater than the size of commands, so it should output
      // all the commands.
      for (int i = 0; i < commands.size(); i++) {
        arrToReturn.add((i + 1) + ". " + commands.get(i));
      }
    }
    return arrToReturn;
  }

  public static void main(String[] args) {
    HistoryList hist = HistoryList.getHist();
    hist.add("6hello");
    hist.add("4dkwo kdwok owdkwd ow kwodkw w kokdow kwdkw");
    hist.add("4hello");
    hist.add("3dkwo kdwok owdkwd ow kwodkw w kokdow kwdkw");
    hist.add("2");
    hist.add("latest");

    List<String> history = hist.getHistory(32);
    for (String s : history) {
      System.out.println(s);
    }
  }
}
