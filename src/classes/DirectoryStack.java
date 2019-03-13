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
import commands.CD;
import java.util.List;

public class DirectoryStack {

  /**
   * stack We will treat this list as a stack. ds We have a static instance of
   * our DirectoryStack to work with.
   */
  private ArrayList<String> stack; // Store the stack
  private static DirectoryStack ds; // an instance of directory stack

  /**
   * Creates a new DirectoryStack object
   */
  private DirectoryStack() {
    this.stack = new ArrayList<String>();
  }

  /**
   * A global instance of DirectoryStack
   *
   * @return the instance of DirectoryStack (singleton)
   */
  public static DirectoryStack getDS() {
    if (ds == null) {
      ds = new DirectoryStack();
    }
    return ds;
  }

  /**
   * Pushes a Folder object
   *
   * @param f A folder to add to stack
   */
  public void push(FileSystem f) {
    String dir = CWDString.getString(f);
    this.stack.add(dir);
  }

  /**
   * Returns the most recently added folder object
   *
   * @param f Current file system
   * @return Most recently added folder
   */
  public void pop(FileSystem f) {
    CD cd = new CD();
    // If stack is empty, return the current working folder
    if (!this.stack.isEmpty()) {
      // Otherwise, return the latest Folder
      String path = this.stack.remove(this.stack.size() - 1);
      // Convert string to a string
      ArrayList<String> tempList = new ArrayList<String>();
      tempList.add(path);
      // Change directory into the path
      cd.runCommand(f, tempList);
    } else {
      Printer.print("Cannot pop directory from stack. It is empty.");
    }
  }

  /**
   * FOR DEVELOPMENT ONLY
   */
  public String toString() {
    String total = "";
    for (String data : this.stack) {
      total += data + ", ";
    }

    return total.substring(0, total.length() - 2);
  }

}
