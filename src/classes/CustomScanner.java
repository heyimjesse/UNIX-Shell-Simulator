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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * The CustomScanner class is responsible for retrieving the users input and
 * fixing the input into a state usable by the Command subclasses.
 */
public class CustomScanner {

  /**
   * scanner The scanner object is created for further use. inputPrompt The
   * user will see the inputPrompt asking for input.
   */
  Scanner scanner;
  private String inputPrompt = "> ";

  public CustomScanner() {
    // Initialize the default java scanner
    this.scanner = new Scanner(System.in);
  }

  /**
   * Gets user input and parses it by putting each command in an entry of an
   * array.
   *
   * @return An array consisting of the input commands
   */
  public Object[] nextCommand() {
    // Show the prompt
    System.out.print(this.inputPrompt);
    String rawInputString = this.scanner.nextLine();
    // Get user input and split by whitespace
    String[] rawInput = rawInputString.split("\\s+");
    // Convert to arraylist
    ArrayList<String> arr = new ArrayList<String>(Arrays.asList(rawInput));
    // Get the command from arraylist

    // initialize empty return objects
    String command = "";
    List<String> args = new ArrayList<String>();

    if (arr.size() > 0) {
      command = arr.get(0);
      // Get the arguments from the input array
      if (arr.size() > 1) {
        args = arr.subList(1, arr.size());
      }
    }

    // Remove OUTFILE parameters from arguments
    List<String> argsWithoutOutfile = removeOutfileFromArgs(args);

    // Convert the input to a list of object
    Object[] returnArr = {command, argsWithoutOutfile, rawInputString, args};
    return returnArr;
  }


  /**
   * Removes OUTFILE command from arguments (if exists)
   * @param args
   * @return
   */
  private List<String> removeOutfileFromArgs(List<String> args) {
    // Look for >>
    int indexOfOutfile = args.indexOf(">>");
    // If not found, look for >
    if (indexOfOutfile == -1) {
      indexOfOutfile = args.indexOf(">");
    }
    // Otherwise, OUTFILE parameters were not specified
    if (indexOfOutfile == -1) {
      indexOfOutfile = args.size();
    }
    return args.subList(0, indexOfOutfile);
  }
}
