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

import java.util.Arrays;

/**
 * The ErrorChecker class is responsible for detecting errors in the users
 * input.
 */
public class ErrorChecker {

  /**
   * invalidCharacters The list of characters which cannot be in a name.
   */
  public static final String[] invalidCharacters = {"!", "@", "#", "$", "%",
      "^", "&", "*", "(", ")", "{", "}", "~", "|", "<", ">", "?", " "};

  /**
   * Checks if the directory name or file name is valid.
   *
   * @param inputName The name that the user typed in
   * @return True or False depending on whether name is valid
   */
  public static boolean checkNameValidity(String inputName) {
    /*
     * Okay, so this part looks really awkward but I couldn't find a better
     * way to iterate through the characters in a string.
     */

    for (int i = 0; i < inputName.length(); i++) {
      char c = inputName.charAt(i);
      String s = String.valueOf(c); // Need to convert char to string
      if (Arrays.asList(invalidCharacters).contains(s)) {
        return false;
      }
    }
    return true; // At this point we know there's no invalid characters
  }

  /**
   * Checks if the given path is valid.
   *
   * @param path The path given in string representation
   * @return True or False depending on whether the path is valid
   */
  public static boolean checkPathValidity(String path) {
    return (ErrorChecker.checkNameValidity(path) && !path.contains("//"));
  }

  public static void main(String[] args) {
    String a = "aa///b";
    System.out.println(ErrorChecker.checkPathValidity(a));
  }
}
